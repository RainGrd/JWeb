/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 内容管理-合同管理控制层
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月21日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.system.controller.content;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.security.domain.ContextUser;
import com.achievo.framework.util.DateUtil;
import com.achievo.framework.util.StringUtil;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.google.common.collect.Lists;
import com.yscf.common.util.PromptMsgConstants;
import com.yscf.core.model.content.AgreementContent;
import com.yscf.core.model.content.BizBorrowAgreement;
import com.yscf.core.model.pub.PubFile;
import com.yscf.core.service.content.impl.AgreementContextServiceImpl;
import com.yscf.core.service.content.impl.ContractManageServiceImpl;
import com.yscf.core.service.pub.impl.PubFileServiceImpl;
import com.yscf.core.util.WordPOIUtil;
import com.yscf.system.constort.PageMessageConstants;
import com.yscf.system.controller.base.EscfBaseController;
import com.yscf.system.util.FileConfig;
import com.yscf.system.util.FileDownload;

/**
 * Description：<br> 
 * 内容管理-合同管理控制层
 * @author  Lin Xu
 * @date    2015年9月21日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/contractManageController")
public class ContractManageController extends EscfBaseController {
	
	private Logger logger = LoggerFactory.getLogger(ContractManageController.class);

	//保存文件的地址信息
	private static String CONTRACT_FILE_MODEL = "contract";
	private static String CONTRACT_TEMP = "temp";
	private static String CONTRACT_CONTEXT = "context";
	private static String CONTRACT_RESULTLIST = "resultList";
	
	@Autowired
	public ContractManageController(ContractManageServiceImpl service) {
		super(service);
	}
	//文件操作服务类
	@Resource(name="pubFileServiceImpl")
	private PubFileServiceImpl pubFileServiceImpl;
	
	@Resource(name = "fileConfig")
	private FileConfig fileConfig;
	
	@Resource(name = "agreementContextServiceImpl")
	private AgreementContextServiceImpl agreementContextServiceImpl;

	@Override
	public Class<?> getModel() {
		return BizBorrowAgreement.class;
	}

	/**
	 * Description：<br> 
	 * 跳转至合同管理列表页面
	 * @author  Lin Xu
	 * @date    2015年9月21日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping("/toContractMPage")
	public ModelAndView toContractMPage(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		return new ModelAndView("/content/contractManage/contractManageList");
	}
	
	/**
	 * Description：<br> 
	 * 获取合同管理列表信息数据
	 * @author  Lin Xu
	 * @date    2015年9月21日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping("/getContractMList")
	@ResponseBody
	public ModelAndView getContractMList(HttpServletRequest request, HttpServletResponse response,
			BizBorrowAgreement bba) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		ContractManageServiceImpl cmservice = (ContractManageServiceImpl) getService();
	   try {
		   PageInfo info = getPageInfo(request);
		   if(null == bba){
			   bba = new BizBorrowAgreement();
		   }
		   //结果集
		   List<BizBorrowAgreement> bizlist = Lists.newArrayList();
		   PageList<BizBorrowAgreement> bizpagelist = cmservice.selectByPrimaryObj(bba, info);
		   int total = 0;
		   //获取结果集
		   if(null != bba && null != bizpagelist && bizpagelist.size() > 0){
			   bizlist = bizpagelist;
			   total = bizpagelist.getPaginator().getTotalCount() ;
		   }
		   //设置结果集
		   modelView.addObject("rows", bizlist);
 		   modelView.addObject("total", total);
 		   MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * Description：<br> 
	 * 跳转至合同管理的新增页面
	 * @author  Lin Xu
	 * @date    2015年9月29日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping("/toContractAddPage")
	public ModelAndView toContractAddPage(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelv = new ModelAndView("/content/contractManage/contractManage_add");
		return modelv;
	}
	
	/**
	 * Description：<br> 
	 * 增加合同协议信息
	 * @author  Lin Xu
	 * @date    2015年9月29日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @param bba
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/saveContractInfo",method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> saveContractInfo(HttpServletRequest request, HttpServletResponse response,
			BizBorrowAgreement bba,MultipartFile file){
		HashMap<String, Object> remap = new HashMap<String, Object>();
		ContractManageServiceImpl cmservice = (ContractManageServiceImpl) getService();
		try{
		if(null != bba){
			//获取操作人信息
			ContextUser cuser = getContextUser();
			PubFile pubfile = new PubFile();
			boolean bool = false;
			//导入文件结果对象
			HashMap<String, Object> uploadMap = new HashMap<String, Object>();
			uploadMap = saveUploadFileObject(request, CONTRACT_FILE_MODEL);
			bool = (Boolean) uploadMap.get(PromptMsgConstants.COMMON_FLG);
			if (!bool) {
				logger.error("文件上传失败or不支持该文件格式上传!");
				processFail(remap, "保存文件失败");
			}else{
				//判断是否有结果集返回信息
				List<PubFile> rflist = (List<PubFile>) uploadMap.get(CONTRACT_RESULTLIST);
				if(null != rflist && rflist.size() > 0 && rflist.size() == 1){
					//获取返回结果集中的第一个元素
					pubfile = rflist.get(0);
					//获取文件保存路径
					pubfile.setLastUpdateUser(cuser.getUserId());
					pubfile.setLastUpdateTime(DateUtil.format(new Date()));
					pubfile.setVersion("1");
					//插入数据库
					pubFileServiceImpl.insert(pubfile);
					//-----------------------------------------------------
					bba.setFileId(bba.getPK());
					bba.setFileId(pubfile.getPid());
					bba.setCreateUser(cuser.getUserName());
					bba.setCreateTime(DateUtil.format(new Date()));
					bba.setLastUpdateUser(cuser.getUserName());
					bba.setLastUpdateTime(DateUtil.format(new Date()));
					bba.setStatus("1");
					//插入合同信息
					cmservice.insert(bba);
					processSuccess(remap, PageMessageConstants.COMMON_MESSAGE_SUCCESS);
				}else{
					processFail(remap, "保存文件不存在");
				}
			}
		}else{
			processFail(remap, PageMessageConstants.COMMON_MESSAGE_FAIL);
		}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
			processError(remap,e.getMessage());
		}
		return remap;
	}
	
	
	/**
	 * Description：<br> 
	 * 跳转至合同管理的修改页面
	 * @author  Lin Xu
	 * @date    2015年9月29日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping("/toContractEidtPage")
	public ModelAndView toContractEidtPage(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelv = new ModelAndView("/content/contractManage/contractManage_eidt");
		try {
			//获取参数值
			String pid = request.getParameter("pid");
			ContractManageServiceImpl cmservice = (ContractManageServiceImpl) getService();
			BizBorrowAgreement bba = (BizBorrowAgreement) cmservice.selectByPrimaryKey(pid);
			modelv.addObject("bbaO", bba);
		} catch (FrameworkException e) {
			if (logger.isDebugEnabled()) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
		}
		return modelv;
	}
	
	/**
	 * Description：<br> 
	 * 修改合同信息数据
	 * @author  Lin Xu
	 * @date    2015年9月29日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/updateContractInfo",method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> updateContractInfo(HttpServletRequest request, HttpServletResponse response,
			BizBorrowAgreement bba,MultipartFile file){
		HashMap<String, Object> remap = new HashMap<String, Object>();
		ContractManageServiceImpl cmservice = (ContractManageServiceImpl) getService();
		try{
			if(null != bba){
				ContextUser cuser = getContextUser();
				BizBorrowAgreement dbba = (BizBorrowAgreement) cmservice.selectByPrimaryKey(bba.getPid());
				dbba.setBorAgrAnme(bba.getBorAgrAnme());
				dbba.setStatus(bba.getStatus());
				dbba.setAgrConTemId(bba.getAgrConTemId());
				dbba.setLastUpdateUser(cuser.getUserName());
				dbba.setLastUpdateTime(DateUtil.format(new Date()));
				//接下来进行文件修改处理
				if(null != file){
					HashMap<String, Object> filemap = saveUploadFileObject(request, CONTRACT_FILE_MODEL);
					boolean bool = (Boolean) filemap.get(PromptMsgConstants.COMMON_FLG);
					PubFile pubfile = new PubFile();
					List<PubFile> rflist = (List<PubFile>) filemap.get(CONTRACT_RESULTLIST);
					if(bool && null != rflist && rflist.size() > 0 && rflist.size() == 1){
						//获取返回结果集中的第一个元素
						pubfile = rflist.get(0);
						//获取文件保存路径
						pubfile.setLastUpdateUser(cuser.getUserId());
						pubfile.setLastUpdateTime(DateUtil.format(new Date()));
						pubfile.setVersion("1");
						//插入数据库
						pubFileServiceImpl.deleteByPrimaryKey(dbba.getFileId());
						pubFileServiceImpl.insert(pubfile);
						dbba.setFileId(pubfile.getPid());
					}
				}
				//最后进行保存修改数据信息
				cmservice.updateByPrimaryKey(dbba);
				processSuccess(remap, PageMessageConstants.CONTRACT_UPDATE_SUCCESS);
			}else{
				processFail(remap, PageMessageConstants.COMMON_MESSAGE_FAIL);
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
			processError(remap,e.getMessage());
		}
		return remap;
	}
	
	
	/**
	 * Description：<br> 
	 * 启用或禁用合同模板
	 * @author  Lin Xu
	 * @date    2015年9月24日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @param pids
	 * @param status
	 * @return
	 */
	@RequestMapping(value="/updateContractStatus",method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> updateContractStatus(HttpServletRequest request,
			HttpServletResponse response,String pids,String status){
		HashMap<String, Object> remap = new HashMap<String, Object>();
		ContractManageServiceImpl bbaservice = (ContractManageServiceImpl) getService();
		try{
			if(StringUtil.isNotEmpty(pids) && StringUtil.isNotEmpty(status)){
				ContextUser cuser = getContextUser();
				String[] pidsarray = StringUtil.split(pids, ",");
				//进行循环处理状态信息
				for(String pid : pidsarray){
					BizBorrowAgreement vbba = new BizBorrowAgreement();
					vbba.setPid(pid);
					vbba.setStatus(status);
					vbba.setLastUpdateUser(cuser.getUserName());
					vbba.setLastUpdateTime(DateUtil.format(new Date()));
					bbaservice.updateByPrimaryKeySelective(vbba);
				}
				//进行返回结果信息
				if("1".equals(status)){
					processSuccess(remap, PageMessageConstants.CONTRACT_CONTEXT_ACTIVE);
				}
				if("2".equals(status)){
					processSuccess(remap, PageMessageConstants.CONTRACT_CONTEXT_DISABLE);
				}
			}else{
				processFail(remap, PageMessageConstants.COMMON_MESSAGE_FAIL);
			}
		}catch(Exception e){
			if (logger.isDebugEnabled()) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
			processError(remap,e.getMessage());
		}
		return remap;
	}
	
	/**
	 * Description：<br> 
	 * 重新上传文件类型信息
	 * @author  Lin Xu
	 * @date    2015年10月12日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @param pid
	 * @param file
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/updateAgainContractFile")
	@ResponseBody
	public HashMap<String, Object> updateAgainContractFile(HttpServletRequest request,
			HttpServletResponse response,String pid,MultipartFile file){
		HashMap<String, Object> remap = new HashMap<String, Object>();
		ContractManageServiceImpl bbaservice = (ContractManageServiceImpl) getService();
		try{
			if(StringUtil.isNotEmpty(pid) && null != file){
				ContextUser cuser = getContextUser();
				HashMap<String, Object> filemap = saveUploadFileObject(request, CONTRACT_FILE_MODEL);
				boolean bool = (Boolean) filemap.get(PromptMsgConstants.COMMON_FLG);
				if(bool){
					BizBorrowAgreement bbam = (BizBorrowAgreement) bbaservice.selectByPrimaryKey(pid);
					if(null != bbam){
						PubFile pubfile = new PubFile();
						List<PubFile> rflist = (List<PubFile>) filemap.get(CONTRACT_RESULTLIST);
						if(null != rflist && rflist.size() > 0 && rflist.size() == 1){
							//获取返回结果集中的第一个元素
							pubfile = rflist.get(0);
							//获取文件保存路径
							pubfile.setLastUpdateUser(cuser.getUserId());
							pubfile.setLastUpdateTime(DateUtil.format(new Date()));
							pubfile.setVersion("1");
							//修改对应协议信息的文件id
							bbam.setFileId(pubfile.getPid());
							bbam.setLastUpdateUser(cuser.getUserName());
							bbam.setLastUpdateTime(DateUtil.format(new Date()));
							//插入数据库
							pubFileServiceImpl.deleteByPrimaryKey(bbam.getFileId());
							pubFileServiceImpl.insert(pubfile);
							
							bbaservice.updateByPrimaryKey(bbam);
							processSuccess(remap, PageMessageConstants.CONTRACT_UPFILE_SUCCESS);
						}else{
							processFail(remap, PageMessageConstants.CONTRACT_UPFILE_SAVE_FAIL);
						}
					}else{
						processFail(remap,PageMessageConstants.CONTRACT_NOTFOUND_INFO);
					}
				}else{
					processFail(remap, PageMessageConstants.COMMON_MESSAGE_FAIL);
				}
			}else{
				processFail(remap, PageMessageConstants.COMMON_MESSAGE_FAIL);
			}
		}catch(Exception e){
			if (logger.isDebugEnabled()) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
			processError(remap,e.getMessage());
		}
		return remap;
	}
	
	
	/**
	 * Description：<br> 
	 * 下载模板或者下载预览的内容模板信息
	 * @author  Lin Xu
	 * @date    2015年10月12日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @param pid
	 * @param status
	 */
	@RequestMapping("/downloadContractFile")
	public void downloadContractFile(HttpServletRequest request,
			HttpServletResponse response,String pid,String status){
		ContractManageServiceImpl bbaservice = (ContractManageServiceImpl) getService();
		//获取对应的id下载对象信息
		try {
			BizBorrowAgreement bbac = (BizBorrowAgreement) bbaservice.selectByPrimaryKey(pid);
			String fileid = bbac.getFileId();
			//获取对应的文件对象信息
			PubFile pubfile =	(PubFile) pubFileServiceImpl.selectByPrimaryKey(fileid);
			//获取文件存储路径信息
			String filepath = fileConfig.getFileRoot() + "/" + pubfile.getFileUrl();
			//如果是temp就下载原始模板，否则替换内容之后进行下载
			if(status.equals(CONTRACT_TEMP)){
				FileDownload.downloadAbsoluteFile(response, request, filepath, pubfile.getFileName());
			
			}else if(status.equals(CONTRACT_CONTEXT)){
				//查询所需要替换的字段信息
				Map<String, String> conmap = new HashMap<String,String>();
				if(StringUtil.isNotEmpty(bbac.getAgrConTemId())){
					PageList<AgreementContent> aclist = agreementContextServiceImpl.selectByPrimaryObjAll(bbac.getAgrConTemId());
					for(AgreementContent ac : aclist){
						conmap.put(ac.getProtocol(), ac.getAgrContName());
					}
				}
				String exitsfp = fileConfig.getFileRoot() + "/contract/temporary";
				//判断文件存不存在，不存在就创建
	            File ofile = new File(exitsfp);
	            if(!ofile.exists()){
	            	ofile.mkdirs();
	            }
	            exitsfp = exitsfp + "/" + pubfile.getFileName() + "." + pubfile.getFileType();
				//进行文件字符替换操作
				boolean rbool = WordPOIUtil.replaceAndGenerateWord(filepath, exitsfp, conmap);
				if(rbool){
					FileDownload.downloadLocal(response, request, exitsfp);
					//进行删除文件
					File delfile = new File(exitsfp);
					if(delfile.exists()){
						if(delfile.isFile()){
							//删除文件
							delfile.delete();
						}
					}
				}
			}
		} catch (FrameworkException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}


