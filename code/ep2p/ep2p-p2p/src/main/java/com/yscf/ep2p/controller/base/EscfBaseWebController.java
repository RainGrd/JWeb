/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 前端基础控制层
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年11月12日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.ep2p.controller.base;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.achievo.framework.constant.FrameworkConstants;
import com.achievo.framework.exception.ExceptionCode;
import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.service.IBaseService;
import com.achievo.framework.util.DateUtil;
import com.achievo.framework.util.StringUtil;
import com.achievo.framework.web.controller.BaseController;
import com.achievo.framework.web.message.HttpMessage;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.yscf.common.Constant.Constant;
import com.yscf.common.Constant.LogConstant;
import com.yscf.common.util.PromptMsgConstants;
import com.yscf.core.model.pub.PubFile;
import com.yscf.core.model.system.SysLog;
import com.yscf.core.send.FtlAnalysis;
import com.yscf.core.send.SendMail;
import com.yscf.core.send.SendMailThread;
import com.yscf.core.service.system.impl.SysLogServiceImpl;
import com.yscf.core.util.LogRecordThread;
import com.yscf.ep2p.constort.PtpConstants;
import com.yscf.ep2p.util.FileDownload;
import com.yscf.ep2p.vo.system.CustomerVo;
import com.yscf.ep2p.vo.system.FileConfig;

/**
 * Description：<br> 
 * 前端基础控制层
 * @author  Lin Xu
 * @date    2015年11月12日
 * @version v1.0.0
 */
public abstract class EscfBaseWebController extends BaseController {
	
	private Logger logger = LoggerFactory.getLogger(EscfBaseWebController.class);
	
	public static ExecutorService pool = Executors.newFixedThreadPool(5);
	
	@Resource(name = "fileConfig")
	private FileConfig fileConfig;

	@Resource(name="sysLogService")
	SysLogServiceImpl sysLogServiceImpl;
	
	@Resource(name="sendMail")
	SendMail sendMail;
	
	@SuppressWarnings("rawtypes")
	public EscfBaseWebController( IBaseService service) {
		super(service);
	}
	
	/**
	 * Description：<br> 
	 * 发送邮件
	 * @author  JingYu.Dai
	 * @date    2015年12月19日
	 * @version v1.0.0
	 * @param mailNo 发送人
	 * @param mailTitle 发送标题
	 * @param tempPath  模版名称加后缀 例如：eamil.ftl
	 * @param tempMap   模版需要替换的值
	 */
	public void sendMail(String mailNo,String mailTitle,String tempPath,Map<String, Object> tempMap){
		FtlAnalysis analysis = new FtlAnalysis();
		//获取邮件内容
		String mailContent = analysis.templateParsing(tempMap, tempPath);
		SendMailThread thread = new SendMailThread(mailNo,mailTitle,mailContent,sendMail);
		pool.submit(thread);
	}
	
	/**
	 * Description：<br> 
	 * 添加日志
	 * @author  JingYu.Dai
	 * @date    2015年12月16日
	 * @version v1.0.0
	 * @param request HttpServletRequest对象
	 * @param operType 日志类型
	 * @param operContent 日志内容
	 */
	public void addLog(HttpServletRequest request,String operType,String operContent){
		CustomerVo cv = getContextUser();
		SysLog sysLog = new SysLog();
		sysLog.setPid(sysLog.getPK());
		sysLog.setName(cv.getSname());
		sysLog.setAccountNo(cv.getCustomerName());
		sysLog.setCreateDate(DateUtil.format(new Date()));
		sysLog.setCreateTime(new Date());
		sysLog.setCreateUser(cv.getCustomerName());
		sysLog.setOperContent(operContent);
		sysLog.setOperType(operType);
		sysLog.setOperandType(LogConstant.LOG_OPERAND_TYPE_2);
		sysLog.setSystemType(LogConstant.LOG_SYSTEM_TYPE_1);
		sysLog.setLoginIp(getIpAddr(request));
		LogRecordThread thread = new LogRecordThread(sysLog,sysLogServiceImpl);
		pool.submit(thread);
	}
	
	/**
	 * Description：<br> 
	 * 获取当前用户客户端ID
	 * @author  JingYu.Dai
	 * @date    2015年12月16日
	 * @version v1.0.0
	 * @param request
	 * @return
	 */
    public String getIpAddr(HttpServletRequest request){  
        String ipAddress = request.getHeader("x-forwarded-for");  
            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
                ipAddress = request.getHeader("Proxy-Client-IP");  
            }  
            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
                ipAddress = request.getHeader("WL-Proxy-Client-IP");  
            }  
            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
                ipAddress = request.getRemoteAddr();  
                if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){  
                    //根据网卡取本机配置的IP  
                    InetAddress inet=null;  
                    try {  
                        inet = InetAddress.getLocalHost();  
                    } catch (UnknownHostException e) {  
                        e.printStackTrace();  
                    }  
                    ipAddress= inet.getHostAddress();  
                }  
            }  
            //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割  
            if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15  
                if(ipAddress.indexOf(",")>0){  
                    ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));  
                }  
            }  
            return ipAddress;   
    } 
	
	/**
	 * Description：<br> 
	 * 获取request对象
	 * @author  Lin Xu
	 * @date    2015年11月13日
	 * @version v1.0.0
	 * @return
	 */
	protected HttpServletRequest getRequest(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}
	
	/**
	 * Description：<br> 
	 * 得到session对象
	 * @author  Lin Xu
	 * @date    2015年11月13日
	 * @version v1.0.0
	 * @return
	 */
	protected HttpSession getSession() {
		HttpSession session = getRequest().getSession();
		return session;
	}
	
	/**
	 * Description：<br> 
	 * 获取开始条数
	 * @author  Lin Xu
	 * @date    2015年11月30日
	 * @version v1.0.0
	 * @param page 当前页
	 * @param rows 每页显示行数
	 * @return
	 */
	protected Integer getStartNumbers(Integer page,Integer rows) {
		if(null != page && null != rows){
			if(page <= 0){
				return 0;
			}else{
				return ((page * rows) - 1);
			}
		}else{
			return 0;
		}
	}

	/**
	 * Description：获取当前登录用户
	 * @author  JingYu.Dai
	 * @date    2015年9月24日
	 * @version v1.0.0
	 * @return ContextUser 当前登录用户
	 */
	public CustomerVo getContextUser(){
		return (CustomerVo) getSession().getAttribute(PtpConstants.CONSUMER);
	}
	
	
	/**
	 * 
	 * Description：<br> 
	 * 获取当前登录用户Id
	 * @author  Yu.Zhang
	 * @date    2015年11月25日
	 * @version v1.0.0
	 * @return
	 */
	public String getUserId(){
		CustomerVo vo = getContextUser();
		if(null == vo){
			return null;
		}
		return vo.getPid();
	}
	
	/**
	 * Description：<br> 
	 * 重写父类 getPageInfo 
	 * @author  Yu.Zhang
	 * @date    2015年9月15日
	 * @version v1.0.0
	 */
	protected PageInfo getPageInfo(HttpServletRequest request) throws HttpRequestException {
	    String pageStr = request.getParameter(FrameworkConstants.PAGE);
	    if (pageStr == null) {
	    	throw new HttpRequestException(ExceptionCode.E_CORE_00006);
	    }
	    Gson gson = new Gson();
	    PageInfo page = new PageInfo();
	    boolean ext = false;
	    try {
	      if ((!StringUtils.isEmpty(pageStr)) && (Integer.parseInt(StringUtils.trimToEmpty(pageStr)) > 0))
	        ext = true;
	    }
	    catch (Throwable localThrowable) {
	    }
	    if (ext) {
	      String limit = request.getParameter(Constant.ROWS);
	      String start = request.getParameter(Constant.PAGE);
	      page.setLimit(StringUtils.isEmpty(limit) ? 15 : Integer.parseInt(limit));
	      page.setPage(StringUtils.isEmpty(start) ? 0 : Integer.parseInt(start));
	    } else {
	      page = (PageInfo)gson.fromJson(pageStr, PageInfo.class);
	    }
	    return page;
	}

	
	/**
	 * 输出JSON信息
	 * 
	 * @param jsonObj
	 */
	protected void outputJson(Object jsonObj, HttpServletResponse response) {
		// 兼容IE浏览器
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		try {
			PrintWriter pw = response.getWriter();
			// 将Java对象转换为JSON字符串
			String gsonStr = new ObjectMapper().writeValueAsString(jsonObj);
			// 输出JSON字符串
			pw.print(gsonStr);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			System.out.println("输出GSON出错：" + e);
		}
	}

	protected void outputJson(Map<String, Object> returnMap, HttpServletResponse response) {
		try {
			String retrunJson = JSON.toJSONString(returnMap);
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(retrunJson);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Description：<br> 
	 * 设置返回成功消息对象信息
	 * @author  Lin Xu
	 * @date    2015年9月24日
	 * @version v1.0.0
	 * @param map
	 * @param msg
	 */
	protected void processSuccess(Map<String, Object> map,String msg){
		map.put("code", HttpMessage.SUCCESS_CODE);
		map.put("message", msg == null ? HttpMessage.SUCCESS_MSG : msg);
	}
	
	/**
	 * Description：<br> 
	 * 设置返回异常消息对象信息
	 * @author  Lin Xu
	 * @date    2015年9月24日
	 * @version v1.0.0
	 * @param remap
	 * @param msg
	 */
	protected void processError(Map<String, Object> remap,String msg){
		remap.put("code", HttpMessage.ERROR_CODE);
		remap.put("message", msg == null ? HttpMessage.ERROR_MSG : msg);
	}
	
	/**
	 * Description：<br> 
	 * 设置返回失败消息对象信息
	 * @author  Lin Xu
	 * @date    2015年9月24日
	 * @version v1.0.0
	 * @param remap
	 * @param msg
	 */
	protected void processFail(HashMap<String, Object> remap,String msg){
		processError(remap,msg);
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 通过解析器进行上传文件
	 * @author  Yu.Zhang
	 * @date    2015年10月8日
	 * @version v1.0.0
	 * @param request
	 * @return
	 */
	public HashMap<String, Object> saveUploadFileObject(HttpServletRequest request,String module ) {
		HashMap<String, Object> remap = new HashMap<String, Object>();
		List<PubFile> resultList = new ArrayList<PubFile>();
		//返回信息
		String msg = "";
		boolean bool = false;
		String reImagePath = "";
		// 记录上传过程起始时的时间，用来计算上传时间
		int pre = (int) System.currentTimeMillis();
		// 创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		//-------------------------------------
		// 获取服务器地址
		String webBasePath = request.getServletContext().getRealPath("");
		// 系统配置文件
		Date curDate = new Date();
		String dateStr = new SimpleDateFormat("yyyy/MM/dd").format(curDate);
		// 获取配置的文件保存根路径
		String rootPath = fileConfig.getFileRoot();
		//-------------------------------------
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			PubFile pubFile = null; // 文件对象
			while (iter.hasNext()) {
				// 取得上传文件
				MultipartFile file = multiRequest.getFile(iter.next());
				if (file != null) {
					String filesnames = file.getOriginalFilename();
					String myfileTypes = filesnames.substring(filesnames.indexOf(".")+1, filesnames.length());
					// 取得当前上传文件的文件名称
//					String myFileName = KeyGenerator.genKey() + myfileTypes;
					String myFileName = takeOutFileName(filesnames);
					// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
					if (myFileName.trim() != "") {
						//logger.info("上传文件名：" + myFileName);
						// 重命名上传后的文件名
						if(!StringUtil.isEmpty(rootPath)){
							String imagePath = myFileName;
							// 如果文件夹是否存在
							if (Constant.ABSOLUTE.equals(fileConfig.getFileRootIs())) {
								// 说明是绝对路径
								String juploadPath = rootPath + "/upload/" + module + "/" + dateStr;
								File jfile = new File(juploadPath);
								if (!jfile.exists()) {
									jfile.mkdirs();
								}
								imagePath = juploadPath + "/" + imagePath;
								//开始保存文件
								if (!file.isEmpty()) {
									File localFile = new File(imagePath);
									try {
										file.transferTo(localFile);
									} catch (IllegalStateException e) {
										e.printStackTrace();
									} catch (IOException e) {
										e.printStackTrace();
									}
									reImagePath = "upload/"  + module + "/" + dateStr + "/" + myFileName;
									msg = PromptMsgConstants.FILEUPLOAD_COMMON_UPSUCCESS;
									bool = PromptMsgConstants.COMMON_SUCCESS_FLG;
									PromptMsgConstants.addReMsgAll(remap, bool, msg);
								}else{
									msg = PromptMsgConstants.FILEUPLOAD_COMMON_FILETYPE;
									bool = PromptMsgConstants.COMMON_FAIL_FLG;
									PromptMsgConstants.addReMsgAll(remap, bool, msg);
									break;
								}
								
							} else {
								String xuploadPath = webBasePath + rootPath + "/" + module + "/" + dateStr;
								File xfile = new File(xuploadPath);
								if (!xfile.exists()) {
									xfile.mkdirs();
								}
								imagePath = xuploadPath + "/" + imagePath;
								//开始保存文件
								if (!file.isEmpty()) {
									File localFile = new File(imagePath);
									try {
										file.transferTo(localFile);
									} catch (IllegalStateException e) {
										e.printStackTrace();
									} catch (IOException e) {
										e.printStackTrace();
									}
									reImagePath = rootPath + "/" + module + "/" + dateStr + "/" + myFileName;
									msg = PromptMsgConstants.FILEUPLOAD_COMMON_UPSUCCESS;
									bool = PromptMsgConstants.COMMON_SUCCESS_FLG;
									PromptMsgConstants.addReMsgAll(remap, bool, msg);
								}else{
									msg = PromptMsgConstants.FILEUPLOAD_COMMON_FILETYPE;
									bool = PromptMsgConstants.COMMON_FAIL_FLG;
									PromptMsgConstants.addReMsgAll(remap, bool, msg);
									break;
								}
							}
							
							// 设置上传文件信息
							pubFile = new PubFile();
							pubFile.setPid(pubFile.getPK());
							pubFile.setFileUrl(reImagePath);
							pubFile.setFileName(filesnames.substring(0,filesnames.indexOf(".")));
							pubFile.setFileType(myfileTypes);
							pubFile.setFileSize(file.getSize());
							pubFile.setStatus(Constant.ACTIVATE);
							pubFile.setCreateTime(DateUtil.format(DateUtil.getToday()));
							pubFile.setCreateUser(getUserId());
							resultList.add(pubFile);
							
						}else{
							msg = PromptMsgConstants.FILEUPLOAD_COMMON_BPATHERRO;
							bool = PromptMsgConstants.COMMON_FAIL_FLG;
							PromptMsgConstants.addReMsgAll(remap, bool, msg);
							break;
						}
					}
				}
			}
		}
		remap.put("resultList", resultList);
		
		// 记录上传该文件后的时间
		int finaltime = (int) System.currentTimeMillis();
		logger.info("上传执行时间：" + (finaltime - pre) );
		return remap;
	}
	
	/**
	 * 从文件路径中取出文件名
	 */
	public static String takeOutFileName(String filePath) {
		int pos = filePath.lastIndexOf(".");
		if (pos > 0) {
			String pressix = filePath.substring(pos + 1);
			UUID uuid = UUID.randomUUID();
			return uuid + "." + pressix;
		} else {
			return filePath;
		}
	}
	
	
	/**
	 * 
	 * @Description: 下载文件
	 * @param response
	 * @param request
	 * @param path
	 * @author: Chong.Zeng
	 * @date: 2015年1月8日 下午4:04:21
	 */
	@RequestMapping(value = "download")
	protected void download(HttpServletResponse response, HttpServletRequest request, @RequestParam("path") String path) {
		try {
			path = new String(path.getBytes("iso-8859-1"), "utf-8");
			// 绝对路径
			if("absolute".equals(fileConfig.getFileRootIs())){
				path=fileConfig.getFileRoot()+File.separator +path;
			}else{
				// 相对路径
				path = request.getServletContext().getRealPath("/") + path;
			}
			FileDownload.downloadLocal(response, request, path);
		} catch (IOException e) {
			logger.error("下载文件异常" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * 手机密码加密
	 * Description：<br> 
	 * TODO
	 * @author  Yu.Zhang
	 * @date    2015年12月10日
	 * @version v1.0.0
	 * @param phoneNo
	 * @return
	 */
	protected String phoneNoEncryption(String phoneNo) {
		StringBuffer sb = new StringBuffer();
		try {
			String phoneFirst = phoneNo.substring(0,3);
			String phoneLast = phoneNo.substring(phoneNo.length()-4,phoneNo.length());
			sb.append(phoneFirst).append("****").append(phoneLast).toString();
		} catch (Exception e) {
			logger.error("下载文件异常" + e.getMessage());
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	/**
	 * email加密
	 * Description：<br> 
	 * TODO
	 * @author  Yu.Zhang
	 * @date    2015年12月10日
	 * @version v1.0.0
	 * @param phoneNo
	 * @return
	 */
	protected String emailEncryption(String email) {
		StringBuffer sb = new StringBuffer();
		try {
			String emailFirst = email.substring(0,5);
			String emailLast = email.split("@")[1];
			sb.append(emailFirst).append("****").append(emailLast).toString();
		} catch (Exception e) {
			logger.error("email加密异常" + e.getMessage());
			e.printStackTrace();
		}
		return sb.toString();
	}

}


