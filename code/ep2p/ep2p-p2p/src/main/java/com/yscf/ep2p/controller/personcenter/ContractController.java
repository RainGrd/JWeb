/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * TODO
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年11月10日     JunJie.Liu		Initial Version.
 *************************************************************************
 */
package com.yscf.ep2p.controller.personcenter;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.achievo.framework.util.DateUtil;
import com.yscf.common.Constant.Constant;
import com.yscf.common.Constant.SystemParamKeyConstant;
import com.yscf.common.util.ArithmeticUtil;
import com.yscf.core.model.business.BizBorrow;
import com.yscf.core.model.business.BizReceiptPlan;
import com.yscf.core.model.business.BizReceiptTransfer;
import com.yscf.core.model.business.BizRepaymentPlan;
import com.yscf.core.model.content.BizBorrowAgreement;
import com.yscf.core.model.pub.Contract;
import com.yscf.core.model.pub.PubFile;
import com.yscf.core.model.reflect.BorrowContract;
import com.yscf.core.model.reflect.BorrowUser;
import com.yscf.core.model.reflect.InvestUser;
import com.yscf.core.model.reflect.TransferList;
import com.yscf.core.model.reflect.TransferReflect;
import com.yscf.core.model.system.SysParams;
import com.yscf.core.model.user.CusTomer;
import com.yscf.core.service.business.IBizBorrowService;
import com.yscf.core.service.business.IBizReceiptPlanService;
import com.yscf.core.service.business.IBizReceiptTransferService;
import com.yscf.core.service.business.IBizRepaymentPlanService;
import com.yscf.core.service.business.impl.BizReceiptTransferServiceImpl;
import com.yscf.core.service.content.IContractManageService;
import com.yscf.core.service.pub.IContractService;
import com.yscf.core.service.pub.IPubFileService;
import com.yscf.core.service.system.ISysCreateCodeRuleService;
import com.yscf.core.service.system.ISysParamsService;
import com.yscf.core.service.user.ICusTomerService;
import com.yscf.core.util.NumberToCNUtil;
import com.yscf.core.util.WordPOIUtil;
import com.yscf.ep2p.controller.base.EscfBaseWebController;
import com.yscf.ep2p.vo.system.FileConfig;

/**
 * Description：<br>
 * 合同Controller
 * 
 * @author JunJie.Liu
 * @date 2015年11月10日
 * @version v1.0.0
 */
@Controller
@RequestMapping("mybids/contractController")
public class ContractController extends EscfBaseWebController {

	@Autowired
	public ContractController(BizReceiptTransferServiceImpl service) {
		super(service);

	}

	@Override	
	public Class<?> getModel() {

		return BizBorrow.class;
	}

	private Logger logger = LoggerFactory.getLogger(ContractController.class);

	@Resource
	private IContractService contractService;
	
	@Resource
	private ICusTomerService customerService;
	
	@Resource
	public ISysParamsService sysParamsService;
	
	@Resource
	public IBizReceiptPlanService bizReceiptPlanService;
	
	@Resource
	public IBizRepaymentPlanService bizRepaymentPlanService;
	
	@Resource
	public IContractManageService contractManageService;
	
	@Resource
	public IPubFileService pubFileService;
	
	@Resource
	public FileConfig fileConfig;
	
	@Resource
	public IBizBorrowService borrowService;
	
	@Resource
	public IBizReceiptTransferService bizReceiptTransferService;
	
	@Resource(name = "sysCreateCodeRuleService")
	private ISysCreateCodeRuleService sysCreateCodeRuleService;
	/**
     * 个人中心借款协议
     */
    @RequestMapping("borrow/contract")
    public ModelAndView getContract(String borrowId,HttpServletRequest request,
                        HttpServletResponse response) throws IOException {
    	ModelAndView modelAndView = new ModelAndView("temp.contract.transfer");
    	String investUserName = "";
    	try {
    		String fileName;
	    	if(StringUtils.hasLength(borrowId)){
	    		BizBorrow borrow = borrowService.getBizBorrowById(borrowId);
	    		String userId = getUserId();
	    		
	    		String type = Constant.CONTRACT_TYPE_1;
	    		if(borrow.getCustomerId().equals(userId)){
	    			type = Constant.CONTRACT_TYPE_2;
	    		}
	    		Contract c = contractService.getByCustIdAndBusinessId(userId,borrowId,type);
	    		if(c!=null){
	    			fileName = c.getAgreementFileUrl();
	    			fileName = fileName.replaceAll("\\\\", "/");
			        modelAndView.addObject("fileName", fileName);
			        return modelAndView;
	    		}
	    		// 借款人
	    		CusTomer ct = customerService.getByUserId(borrow.getCustomerId());
	    		Map<String,List<Object>> maps = new HashMap<String,List<Object>>();
	    		List<Object> list = new ArrayList<Object>();
	    		List<InvestUser> users = new ArrayList<InvestUser>();
	    		if(Constant.CONTRACT_TYPE_1.equals(type)){
	    			// 投资人
	        		CusTomer ct2 = customerService.getByUserId(userId);
	        		investUserName = StringUtils.hasLength(ct2.getCustomerName())?ct2.getCustomerName():ct2.getPhoneNo();
	        		
	        		// 只获取投资人的列表
	        		users = bizReceiptPlanService.findInvestList(borrowId,userId);
	        	
	    		}else{
	    			// 借款人
	    			users = bizReceiptPlanService.findInvestList(borrowId,null);
	    			
	    		}
	    		for(int i = 0;i < users.size();i++){
	    			InvestUser iu = users.get(i);
	    			if(users.size()>1){
	    				iu.setName(iu.getName()+" "+(i+1));
	    			}
	    			iu.setCapital(formatMoney(iu.getCapital()));
	    			iu.setInterest(formatMoney(iu.getInterest()));
	    			iu.setMoney(formatMoney(iu.getMoney()));
	    			iu.setTotalMoney(formatMoney(iu.getTotalMoney()));
	    			iu.setYearRate(formatYearRate(iu.getYearRate()));
	    			
	    			list.add(iu);
	    		}
	    
	    		
	     		maps.put("1", list);
	     		
	     		// 获取借款人的还款列表
	     		List<BizRepaymentPlan> brps = bizRepaymentPlanService.findListByBorrowId(borrow.getPid());
	     		List<Object> list2 = new ArrayList<Object>();
	     		if(brps!=null){
		     		for(int i=0;i<brps.size();i++){
		     			BizRepaymentPlan brp = brps.get(i);
		     			BorrowUser bu = new BorrowUser();
		     			
		     			bu.setCapital(formatMoney(brp.getCapital()));
		     			bu.setInterest(formatMoney(brp.getInterest()));
		     			bu.setTotalMoney(formatMoney(brp.getCapital().add(brp.getInterest())));
		     			
		     			list2.add(bu);
		     		}
	     		}
	     		maps.put("2", list2);
	     		
	    		
	    		String borrowAgreementId =borrow.getBorAgrId();
	    		
	    		BizBorrowAgreement bargr = contractManageService.getById(borrowAgreementId);
	    		
	    		PubFile pubFile = pubFileService.getById(bargr.getFileId());
	    		
	    		String fileRoot = fileConfig.getFileRoot();
	    		
	    		String docUrl = fileRoot + "/" + pubFile.getFileUrl();
	    		// 临时doc文件
	    		String tempDocUrl = fileRoot +"/" + Constant.CONTRACT_BORROW_FILE_URL + DateUtil.format(new Date(), "yyyy/MM/dd");
	    		// 最终要产生的pdf路径
	    		String tempDocUrl2 = tempDocUrl + "/";
	    		String turl = tempDocUrl2+userId+System.currentTimeMillis();
	    		tempDocUrl = turl +".docx";
	    		String pdfUrl = turl+".pdf";
	    		
	    		BorrowContract bc = new BorrowContract();
	    		SysParams s1 = sysParamsService.getParamsByParamKey(SystemParamKeyConstant.GUARANTEE_TYPE);
	    		String guaranteeType = s1!=null? s1.getParamValue() : "";
	    		SysParams s2 = sysParamsService.getParamsByParamKey(SystemParamKeyConstant.PLAT_FORM);
	    		String platform = s2!=null? s2.getParamValue() : "";
	    		SysParams s3 = sysParamsService.getParamsByParamKey(SystemParamKeyConstant.PLAT_FORM_ADDRESS);
	    		String platfromAddress = s3!=null? s3.getParamValue() : "";
	    		
	    		bc.setAmount("￥"+ArithmeticUtil.round(borrow.getBorrowMoney(), 2));
	    		bc.setAmountInWords(NumberToCNUtil.number2CNMontrayUnit(borrow.getBorrowMoney()));
	    		bc.setBorrowCode(borrow.getBorrowCode());
	    		bc.setBorrowName(borrow.getBorrowName());
	    		bc.setBondsman(borrow.getGuaOrgName());
	    		bc.setBorrowUserName(StringUtils.hasLength(ct.getCustomerName())?ct.getCustomerName():ct.getPhoneNo());
	    		bc.setInvestUserName(investUserName);
	    		String dunit = "月";
	    		if(Constant.BORROW_TYPE_4.equals(borrow.getBorrowType())){
	    			dunit = "天";
	    		}
	    		
	    		bc.setStartDate(DateUtil.format(borrow.getBorFullTime(), "yyyy/MM/dd"));
	    		Calendar calendar = Calendar.getInstance();
	    		calendar.setTime(borrow.getBorFullTime());
	    	
	    		if(Constant.BORROW_TYPE_4.equals(borrow.getBorrowType())){
	    			calendar.add(Calendar.DATE, Integer.parseInt(borrow.getBorDeadline()));
	    		}else{
	    			calendar.add(Calendar.MONTH, Integer.parseInt(borrow.getBorDeadline()));
	    		}
	    		bc.setEndDate(DateUtil.format(calendar.getTime(), "yyyy/MM/dd"));
	    		
	    		
	    		bc.setBd(borrow.getBorDeadline()+dunit);
	    		
	    		bc.setGuaranteeType(guaranteeType);
	    		bc.setPlatform(platform);
	    		bc.setPlatfromAddress(platfromAddress);
	    		bc.setRepaymentTypeName(borrow.getRepaymentTypeName());
	    		
	    		String x = getByType(borrow.getBorrowType());
	    		String code = sysCreateCodeRuleService.generatNoRule(x, "8",4,getUserId());
	    		
				bc.setCode(format(code));
				
	    		File f = WordPOIUtil.doc2pdf(docUrl, tempDocUrl, pdfUrl, bc, maps);
	    		fileName = f.getPath();
		    	fileName = fileName.substring(fileName.indexOf("upload"), fileName.length());
		    	fileName = fileName.replaceAll("\\\\", "/");
		    	
		    	Contract contract = new Contract();
		    	contract.setPid(contract.getPK());
		    	contract.setAgreementFileUrl(fileName);
		    	contract.setAgreementModelId(borrowAgreementId);
		    	contract.setAgreementNumber(code);
		    	contract.setBusinessId(borrowId);
		    	contract.setCustomerId(userId);
		    	contract.setType(type);
		    	
		    	contractService.insertSelective(contract);
		    	
		        modelAndView.addObject("fileName", fileName);
		        
		        
		        
	    	}
		} catch (Exception e) {
			e.printStackTrace();
			if(logger.isDebugEnabled()){
				logger.error(e.getMessage());
			}
		}
        return  modelAndView;
    }
    
    /**
     * 个人中心已转让的原始借款协议
     */
    @RequestMapping("srcborrow/contract")
    public ModelAndView getSrcContract(String borrowId,String transferId,HttpServletRequest request,
                        HttpServletResponse response) throws IOException {
    	ModelAndView modelAndView = new ModelAndView("temp.contract.transfer");
    	String investUserName = "";
    	try {
    		String fileName;
	    	if(StringUtils.hasLength(borrowId)){
	    		BizBorrow borrow = borrowService.getBizBorrowById(borrowId);
	    		// 根据债权，获取最原始的createUser，即初始人
	    		BizReceiptTransfer tf= new BizReceiptTransfer();
	    		tf.setParentTransferId(transferId);
	    		do{
	    			// 获取最原始的债权，取得第一手转让人
	    			tf = bizReceiptTransferService.getBizReceiptTransferById(tf.getParentTransferId());
	    			if(tf == null){
	    				break;
	    			}
	    		}while(StringUtils.hasLength(tf.getParentTransferId()));
	    		
	    		String userId = tf.getCreateUser();
	    		
	    		String type = Constant.CONTRACT_TYPE_1;
	    		if(borrow.getCustomerId().equals(userId)){
	    			type = Constant.CONTRACT_TYPE_2;
	    		}
	    		Contract c = contractService.getByCustIdAndBusinessId(userId,borrowId,type);
	    		if(c!=null){
	    			fileName = c.getAgreementFileUrl();
	    			fileName = fileName.replaceAll("\\\\", "/");
			        modelAndView.addObject("fileName", fileName);
			        return modelAndView;
	    		}
	    		
	    		// 借款人
	    		CusTomer ct = customerService.getByUserId(borrow.getCustomerId());
	    		Map<String,List<Object>> maps = new HashMap<String,List<Object>>();
	    		List<Object> list = new ArrayList<Object>();
	    		List<InvestUser> users = new ArrayList<InvestUser>();
	    		if(Constant.CONTRACT_TYPE_1.equals(type)){
	    			// 投资人
	        		CusTomer ct2 = customerService.getByUserId(userId);
	        		investUserName = StringUtils.hasLength(ct2.getCustomerName())?ct2.getCustomerName():ct2.getPhoneNo();
	        		
	        		// 只获取投资人的列表
	        		users = bizReceiptPlanService.findInvestList(borrowId,userId);
	        	
	    		}else{
	    			// 借款人
	    			users = bizReceiptPlanService.findInvestList(borrowId,null);
	    			
	    		}
	    		for(int i = 0;i < users.size();i++){
	    			InvestUser iu = users.get(i);
	    			if(users.size()>1){
	    				iu.setName(iu.getName()+" "+(i+1));
	    			}
	    			iu.setCapital(formatMoney(iu.getCapital()));
	    			iu.setInterest(formatMoney(iu.getInterest()));
	    			iu.setMoney(formatMoney(iu.getMoney()));
	    			iu.setTotalMoney(formatMoney(iu.getTotalMoney()));
	    			iu.setYearRate(formatYearRate(iu.getYearRate()));
	    			
	    			list.add(iu);
	    		}
	    
	    		
	     		maps.put("1", list);
	     		
	     		// 获取借款人的还款列表
	     		List<BizRepaymentPlan> brps = bizRepaymentPlanService.findListByBorrowId(borrow.getPid());
	     		List<Object> list2 = new ArrayList<Object>();
	     		if(brps!=null){
		     		for(int i=0;i<brps.size();i++){
		     			BizRepaymentPlan brp = brps.get(i);
		     			BorrowUser bu = new BorrowUser();
		     			
		     			bu.setCapital(formatMoney(brp.getCapital()));
		     			bu.setInterest(formatMoney(brp.getInterest()));
		     			bu.setTotalMoney(formatMoney(brp.getCapital().add(brp.getInterest())));
		     			
		     			list2.add(bu);
		     		}
	     		}
	     		maps.put("2", list2);
	     		
	    		
	    		String borrowAgreementId =borrow.getBorAgrId();
	    		
	    		BizBorrowAgreement bargr = contractManageService.getById(borrowAgreementId);
	    		
	    		PubFile pubFile = pubFileService.getById(bargr.getFileId());
	    		
	    		String fileRoot = fileConfig.getFileRoot();
	    		
	    		String docUrl = fileRoot + "/" + pubFile.getFileUrl();
	    		String tempDocUrl = fileRoot +"/" +  Constant.CONTRACT_BORROW_FILE_URL + DateUtil.format(new Date(), "yyyy/MM/dd");
	    		// 最终要产生的pdf路径
	    		String tempDocUrl2 = tempDocUrl + "/";
	    		String turl = tempDocUrl2+userId+System.currentTimeMillis();
	    		tempDocUrl = turl +".docx";
	    		String pdfUrl = turl+".pdf";
	    		
	    		BorrowContract bc = new BorrowContract();
	    		SysParams s1 = sysParamsService.getParamsByParamKey(SystemParamKeyConstant.GUARANTEE_TYPE);
	    		String guaranteeType = s1!=null? s1.getParamValue() : "";
	    		SysParams s2 = sysParamsService.getParamsByParamKey(SystemParamKeyConstant.PLAT_FORM);
	    		String platform = s2!=null? s2.getParamValue() : "";
	    		SysParams s3 = sysParamsService.getParamsByParamKey(SystemParamKeyConstant.PLAT_FORM_ADDRESS);
	    		String platfromAddress = s3!=null? s3.getParamValue() : "";
	    		
	    		bc.setAmount("￥"+ArithmeticUtil.round(borrow.getBorrowMoney(), 2));
	    		bc.setAmountInWords(NumberToCNUtil.number2CNMontrayUnit(borrow.getBorrowMoney()));
	    		bc.setBorrowCode(borrow.getBorrowCode());
	    		bc.setBorrowName(borrow.getBorrowName());
	    		bc.setBondsman(borrow.getGuaOrgName());
	    		bc.setBorrowUserName(StringUtils.hasLength(ct.getCustomerName())?ct.getCustomerName():ct.getPhoneNo());
	    		bc.setInvestUserName(investUserName);
	    		String dunit = "月";
	    		if(Constant.BORROW_TYPE_4.equals(borrow.getBorrowType())){
	    			dunit = "天";
	    		}
	    		
	    		bc.setStartDate(DateUtil.format(borrow.getBorFullTime(), "yyyy/MM/dd"));
	    		Calendar calendar = Calendar.getInstance();
	    		calendar.setTime(borrow.getBorFullTime());
	    	
	    		if(Constant.BORROW_TYPE_4.equals(borrow.getBorrowType())){
	    			calendar.add(Calendar.DATE, Integer.parseInt(borrow.getBorDeadline()));
	    		}else{
	    			calendar.add(Calendar.MONTH, Integer.parseInt(borrow.getBorDeadline()));
	    		}
	    		bc.setEndDate(DateUtil.format(calendar.getTime(), "yyyy/MM/dd"));
	    		
	    		
	    		bc.setBd(borrow.getBorDeadline()+dunit);
	    		
	    		bc.setGuaranteeType(guaranteeType);
	    		bc.setPlatform(platform);
	    		bc.setPlatfromAddress(platfromAddress);
	    		bc.setRepaymentTypeName(borrow.getRepaymentTypeName());
	    		
	    		String x = getByType(borrow.getBorrowType());
	    		String code = sysCreateCodeRuleService.generatNoRule(x, "8",4,getUserId());
	    		bc.setCode(format(code));
	    		File f = WordPOIUtil.doc2pdf(docUrl, tempDocUrl, pdfUrl, bc, maps);
	    		fileName = f.getPath();
		    	fileName = fileName.substring(fileName.indexOf("upload"), fileName.length());
		    	fileName = fileName.replaceAll("\\\\", "/");
		    	
		    	Contract contract = new Contract();
		    	contract.setPid(contract.getPK());
		    	contract.setAgreementFileUrl(fileName);
		    	contract.setAgreementModelId(borrowAgreementId);
		    	contract.setAgreementNumber(code);
		    	contract.setBusinessId(borrowId);
		    	contract.setCustomerId(userId);
		    	contract.setType(type);
		    	
		    	contractService.insertSelective(contract);
		    	
		        modelAndView.addObject("fileName", fileName);
		        
		        
		        
	    	}
		} catch (Exception e) {
			e.printStackTrace();
			if(logger.isDebugEnabled()){
				logger.error(e.getMessage());
			}
		}
        return  modelAndView;
    }
    

	/**
     * 通过url请求返回债权转让预览协议
     */
    @RequestMapping("transfer/contract")
    public ModelAndView getTransferContract(String transferId,HttpServletRequest request,
                        HttpServletResponse response) throws IOException {
    	ModelAndView modelAndView = new ModelAndView("temp.contract.transfer");
    	try {
    		String fileName;
    		String userId = getUserId();
	    	if(StringUtils.hasLength(transferId)){
	    		
	    		Contract c = contractService.getByCustIdAndBusinessId(userId,transferId,Constant.CONTRACT_TYPE_3);
	    		if(c!=null){
	    			fileName = c.getAgreementFileUrl();
	    			fileName = fileName.replaceAll("\\\\", "/");
			        modelAndView.addObject("fileName", fileName);
			        return modelAndView;
	    		}
	    		
	    		BizReceiptTransfer bTransfer = bizReceiptTransferService.getBizReceiptTransferById(transferId);
	    		
	    		BizBorrow borrow = borrowService.getBizBorrowById(bTransfer.getBorrowId());
	    		
	    		CusTomer ct1 = customerService.getByUserId(bTransfer.getCustomerId());
	    		CusTomer ct2 = customerService.getByUserId(bTransfer.getCreateUser());
	    		CusTomer ct3 = customerService.getByUserId(borrow.getCustomerId());
	    		
	    		// 预览
	    		SysParams sp = sysParamsService.getParamsByParamKey(SystemParamKeyConstant.BOR_AGR_TRANSFER);
	    		
	    		BizBorrowAgreement bargr = contractManageService.getByName(sp.getParamValue());
	    		
	    		PubFile pubFile = pubFileService.getById(bargr.getFileId());
	    		
	    		String fileRoot = fileConfig.getFileRoot();
	    		
	    		String docUrl = fileRoot + "/" + pubFile.getFileUrl();
	    		String tempDocUrl = fileRoot + "/" +  Constant.CONTRACT_TRANSFER_FILE_URL + DateUtil.format(new Date(), "yyyy/MM/dd");
	    		// 最终要产生的pdf路径
	    		String tempDocUrl2 = tempDocUrl + "/";
	    		String turl = tempDocUrl2+userId+System.currentTimeMillis();
	    		tempDocUrl = turl +".docx";
	    		String pdfUrl = turl+".pdf";
	    		
	    		
	    		SysParams s1 = sysParamsService.getParamsByParamKey(SystemParamKeyConstant.GUARANTEE_TYPE);
	    		String guaranteeType = s1!=null? s1.getParamValue() : "";
	    		SysParams s2 = sysParamsService.getParamsByParamKey(SystemParamKeyConstant.PLAT_FORM);
	    		String platform = s2!=null? s2.getParamValue() : "";
	    		
	    		
	    		
	    		TransferReflect tr = new TransferReflect();
	    		tr.setBondsman(borrow.getGuaOrgName());
	    		tr.setBorrowCode(borrow.getBorrowCode());
	    		tr.setBorrowName(borrow.getBorrowName());
	    		tr.setBorrowUserName(StringUtils.hasLength(ct3.getCustomerName())?ct3.getCustomerName():ct3.getPhoneNo());
	    		tr.setDeadline(bTransfer.getTimeRemaining()+"/"+borrow.getDeadline());
	    		Calendar calendar = Calendar.getInstance();
	    		calendar.setTime(borrow.getBorFullTime());
	    		if(Constant.BORROW_TYPE_4.equals(borrow.getBorrowType())){
	    			calendar.add(Calendar.DATE, Integer.parseInt(borrow.getBorDeadline()));
	    		}else{
	    			calendar.add(Calendar.MONTH, Integer.parseInt(borrow.getBorDeadline()));
	    		}
	    		tr.setEndDate(DateUtil.format(calendar.getTime(), "yyyy/MM/dd"));
	    		tr.setGuaranteeType(guaranteeType);
	    		tr.setInterest(formatMoney(bTransfer.getProjectValue().subtract(bTransfer.getResidualPrincipal())));
	    		tr.setPlatform(platform);
	    		tr.setTransferAmount(formatMoney(bTransfer.getResidualPrincipal()));
	    		tr.setRepaymentTypeName(borrow.getRepaymentTypeName());
	    		tr.setSuccessAmount(formatMoney(bTransfer.getSuccessAmount()));
	    		tr.setTime(DateUtil.format(bTransfer.getSuccessTime(),"yyyy/MM/dd"));
	    		tr.setTotalAmount(formatMoney(bTransfer.getProjectValue()));
	    		tr.setUserA(StringUtils.hasLength(ct1.getCustomerName())?ct1.getCustomerName():ct1.getPhoneNo());
	    		tr.setUserB(StringUtils.hasLength(ct2.getCustomerName())?ct2.getCustomerName():ct2.getPhoneNo());
	    		tr.setYearRate(formatYearRate(borrow.getBorrowRate().toString()));
	    		
	    		Map<String,List<Object>> maps = new HashMap<String,List<Object>>();
	    		BizReceiptTransfer bizReceiptTransfer = bizReceiptTransferService.getBizReceiptTransferById(transferId);
				List<BizReceiptPlan> list = new ArrayList<BizReceiptPlan>();
				List<Object> list2 = new ArrayList<Object>();
				if(bizReceiptTransfer!=null){
						
					if(StringUtils.hasLength(bizReceiptTransfer.getParentTransferId())){
						// 否则是债权再次转让
						list = bizReceiptPlanService.findListByTransfer(bizReceiptTransfer.getParentTransferId(),Constant.INVEST_SRC_TYPE_2);
					}else{
						// 如果父id为null，表示原标转让
						list = bizReceiptPlanService.findListByTransfer(bizReceiptTransfer.getPid(),Constant.INVEST_SRC_TYPE_1);
					}
					
				}
				if(list!=null){
					for(BizReceiptPlan bp:list){
						String benjin = formatMoney(bp.getCapital());
						String benxi = formatMoney(bp.getInterest());
						String lixi = formatMoney(bp.getCapital().add(bp.getInterest()));
						
						TransferList tf = new TransferList();
						tf.setBenjin(benjin);
						tf.setBenxi(benxi);
						tf.setLixi(lixi);
						
						list2.add(tf);
					}
				}
				maps.put("1", list2);
	    		
				String code = sysCreateCodeRuleService.generatNoRule("ZQ", "8",4,getUserId());
				tr.setCode(format(code));
				
	    		File f = WordPOIUtil.doc2pdf(docUrl, tempDocUrl, pdfUrl, tr, maps);
	    		fileName = f.getPath();
		    	fileName = fileName.substring(fileName.indexOf("upload"), fileName.length());
		    	fileName = fileName.replaceAll("\\\\", "/");
		    	
		    	Contract contract = new Contract();
		    	contract.setPid(contract.getPK());
		    	contract.setAgreementFileUrl(fileName);
		    	contract.setAgreementModelId(bargr.getPid());
		    	contract.setAgreementNumber(code);
		    	contract.setBusinessId(transferId);
		    	contract.setCustomerId(userId);
		    	contract.setType(Constant.CONTRACT_TYPE_3);
		    	
		    	contractService.insertSelective(contract);
		    	
		        modelAndView.addObject("fileName", fileName);
		        
		        
		        
	    	}
		} catch (Exception e) {
			e.printStackTrace();
			if(logger.isDebugEnabled()){
				logger.error(e.getMessage());
			}
		}
        return  modelAndView;
    }
    
    /**
     * 个人中心借款协议
     */
    @RequestMapping("borrow/tempcontract")
    public ModelAndView tempcontract(HttpServletRequest request,
                        HttpServletResponse response) throws IOException {
    	ModelAndView modelAndView = new ModelAndView("temp.contract.transfer");
    	try {
    			String fileName;
	    
    			SysParams sp = sysParamsService.getParamsByParamKey(SystemParamKeyConstant.BOR_AGR_BORROW);
	    		
	    		BizBorrowAgreement bargr = contractManageService.getByName(sp.getParamValue());
	    		
	    		PubFile pubFile = pubFileService.getById(bargr.getFileId());
	    		
	    		String docUrl = pubFile.getFileUrl();
	    		fileName = docUrl.substring(0, docUrl.lastIndexOf(".")) + ".pdf";;
    			fileName = fileName.replaceAll("\\\\", "/");
    			
		        modelAndView.addObject("fileName", fileName);
		        
		        return modelAndView;
		        
		} catch (Exception e) {
			e.printStackTrace();
			if(logger.isDebugEnabled()){
				logger.error(e.getMessage());
			}
		}
        return  modelAndView;
    }
    
    /**
     * 个人中心借款协议
     */
    @RequestMapping("transfer/tempcontract")
    public ModelAndView transferTempcontract(HttpServletRequest request,
    		HttpServletResponse response) throws IOException {
    	ModelAndView modelAndView = new ModelAndView("temp.contract.transfer");
    	try {
    		String fileName;
    		
    		SysParams sp = sysParamsService.getParamsByParamKey(SystemParamKeyConstant.BOR_AGR_TRANSFER);
    		
    		BizBorrowAgreement bargr = contractManageService.getByName(sp.getParamValue());
    		
    		PubFile pubFile = pubFileService.getById(bargr.getFileId());
    		String docUrl = pubFile.getFileUrl();
    		fileName = docUrl.substring(0, docUrl.lastIndexOf("."))+".pdf";
    		fileName = fileName.replaceAll("\\\\", "/");
    		
    		modelAndView.addObject("fileName", fileName);
    		
    		return modelAndView;
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    		if(logger.isDebugEnabled()){
    			logger.error(e.getMessage());
    		}
    	}
    	return  modelAndView;
    }
    
    
    
    private String formatMoney(BigDecimal money){
    	if(money!=null){
    		money = ArithmeticUtil.round(money, 2);
    	}
    	return money.toString() + "元";
    }
    private String formatMoney(String money){
    	if(StringUtils.hasLength(money)){
    		BigDecimal b = new BigDecimal(money);
    		b = ArithmeticUtil.round(b, 2);
    		money = b.toString()+"元";
    	}
    	return money;
    }
    private String formatYearRate(String yearRate){
    	if(StringUtils.hasLength(yearRate)){
    		BigDecimal b = new BigDecimal(yearRate);
    		b = b.multiply(new BigDecimal(100));
    		b = ArithmeticUtil.round(b, 2);
    		yearRate = b.toString()+"%";
    	}
    	return yearRate;
    }
    
    private String getByType(String type){
    	if(Constant.BORROW_TYPE_1.equals(type)){
    		return "DY";
    	}else if(Constant.BORROW_TYPE_2.equals(type)){
    		return "SF";
    	}else if(Constant.BORROW_TYPE_3.equals(type)){
    		return "E";
    	}else if(Constant.BORROW_TYPE_4.equals(type)){
    		return "X";
    	}else{
    		return "";
    	}
    }
    
    private String format(String code){
    	if(code.startsWith("E") || code.startsWith("X")){
    		return code.substring(0, 9)+"-"+code.substring(9, code.length());
    		
    	}else if(code.startsWith("DY") || code.startsWith("SF") || code.startsWith("ZQ")){
    		return code.substring(0, 10)+"-"+code.substring(10, code.length());
    	}
    	return code;
    }
    
    
}
