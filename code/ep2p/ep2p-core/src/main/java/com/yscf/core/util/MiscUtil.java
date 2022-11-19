/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 混合工具类
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年12月15日     Jie.Zou		Initial Version.
 *************************************************************************
 */
package com.yscf.core.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.util.StringUtils;

import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.util.DateUtil;
import com.yscf.core.model.business.BizBorrow;
import com.yscf.core.model.business.BizBorrowDetail;
import com.yscf.core.model.business.BizBorrowInfoVO;
import com.yscf.core.model.business.CalculationDto;
import com.yscf.core.service.calculation.CalculationFactory;
import com.yscf.core.service.calculation.ICalculation;
import com.yscf.core.service.system.impl.SysParamsServiceImpl;

/**
 * Description：<br> 
 * 混合工具类
 * @author  Jie.Zou
 * @date    2015年12月15日
 * @version v1.0.0
 */
public class MiscUtil {
	
	@Resource(name = "sysParamsService")
	private static SysParamsServiceImpl paramsServiceImpl;
	/**
	 * 
	 * Description：<br> 
	 * 获取保持两位小数（金额惯例）的BigDecimal,
	 * 按照四舍五入的进行小数点截取
	 * @author  Jie.Zou
	 * @date    2015年12月15日
	 * @version v1.0.0
	 * @param amount 原始数值
	 * @return
	 */
	public static BigDecimal getBigDecimalMoney(BigDecimal amount){
		if(null!=amount){
			return new BigDecimal(amount.toString()).setScale(2, RoundingMode.HALF_UP);
		}
		return null;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 格式化借款的借款利率、借款完成进度
	 * @author  Jie.Zou
	 * @date    2016年1月16日
	 * @version v1.0.0
	 * @param bizBorrow
	 * @return
	 */
	public static BizBorrow formatBorrow(BizBorrow bizBorrow) {
		if(null!=bizBorrow){
			if(null!=bizBorrow.getBorrowRate())
				bizBorrow.setBorrowRate(bizBorrow.getBorrowRate().multiply(new BigDecimal("100")).setScale(2,RoundingMode.HALF_UP));
			if(null!=bizBorrow.getBorrowProgress())
				bizBorrow.setBorrowProgress(bizBorrow.getBorrowProgress().multiply(new BigDecimal("100")).setScale(2,RoundingMode.HALF_UP));
			else
				bizBorrow.setBorrowProgress(BigDecimal.ZERO);
		}
		return bizBorrow;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 格式化借款VO的借款利率、借款完成进度
	 * @author  Jie.Zou
	 * @date    2016年1月16日
	 * @version v1.0.0
	 * @param bizBorrow
	 * @return
	 */
	public static BizBorrowInfoVO formatBorrow(BizBorrowInfoVO bizBorrow) {
		if(null!=bizBorrow){
			if(null!=bizBorrow.getBorrowRate())
				bizBorrow.setBorrowRate(bizBorrow.getBorrowRate().multiply(new BigDecimal("100")).setScale(2,RoundingMode.HALF_UP));
			
			if(null!=bizBorrow.getInvestRewardScale())
				bizBorrow.setInvestRewardScale(bizBorrow.getInvestRewardScale().multiply(new BigDecimal("100")).setScale(2,RoundingMode.HALF_UP));
			else
				bizBorrow.setInvestRewardScale(BigDecimal.ZERO);
			
			if(null!=bizBorrow.getBorrowProgress())
				bizBorrow.setBorrowProgress(bizBorrow.getBorrowProgress().multiply(new BigDecimal("100")).setScale(2,RoundingMode.HALF_UP));
			else
				bizBorrow.setBorrowProgress(BigDecimal.ZERO);
		}
		return bizBorrow;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 批量格式化借款VO的借款利率、借款完成进度
	 * @author  Jie.Zou
	 * @date    2016年1月16日
	 * @version v1.0.0
	 * @param bizBorrows
	 * @return
	 */
	public static PageList<BizBorrowInfoVO> formatBorrows(PageList<BizBorrowInfoVO> bizBorrows){
		if(null!=bizBorrows){
			for (BizBorrowInfoVO bizBorrowInfoVO : bizBorrows) {
				if(null!=bizBorrowInfoVO){
					if(null!=bizBorrowInfoVO.getBorrowRate())
						bizBorrowInfoVO.setBorrowRate(bizBorrowInfoVO.getBorrowRate().multiply(new BigDecimal("100")).setScale(2,RoundingMode.HALF_UP));
					
					if(null!=bizBorrowInfoVO.getInvestRewardScale())
						bizBorrowInfoVO.setInvestRewardScale(bizBorrowInfoVO.getInvestRewardScale().multiply(new BigDecimal("100")).setScale(2,RoundingMode.HALF_UP));
					else
						bizBorrowInfoVO.setInvestRewardScale(BigDecimal.ZERO);
					
					if(null!=bizBorrowInfoVO.getBorrowProgress())
						bizBorrowInfoVO.setBorrowProgress(bizBorrowInfoVO.getBorrowProgress().multiply(new BigDecimal("100")).setScale(2,RoundingMode.HALF_UP));
					else
						bizBorrowInfoVO.setBorrowProgress(BigDecimal.ZERO);
				}
			}
		}
		return bizBorrows;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 批量计算投标利息
	 * @author  Jie.Zou
	 * @date    2016年1月18日
	 * @version v1.0.0
	 * @param borrowDetails 投标记录
	 * @return
	 */
	public static PageList<BizBorrowDetail> calculationInterest(PageList<BizBorrowDetail> borrowDetails,BizBorrowInfoVO bizBorrowInfoVO){
		if(null!=bizBorrowInfoVO){
			CalculationDto dto = new CalculationDto();
			//设置还款方式
			if(StringUtils.hasText(bizBorrowInfoVO.getRepaymentType()))
				dto.setRepaymentType(Integer.parseInt(bizBorrowInfoVO.getRepaymentType()));
			//设置管理费率
			dto.setManagementRate(new BigDecimal(0));
			//设置借款利率
			if(bizBorrowInfoVO.getBorrowRate().compareTo(BigDecimal.ZERO)>0)
				dto.setRepaymentRate(bizBorrowInfoVO.getBorrowRate().multiply(new BigDecimal(100)));
			//设置利息管理费
			dto.setInterestRate(new BigDecimal(0));
			//设置加息卷
			dto.setAddInterest(new BigDecimal(0));
			//设置加息卷管理费率
			dto.setAddInterestRate(new BigDecimal(0));
			//设置借款期限
			if(StringUtils.hasText(bizBorrowInfoVO.getBorDeadline()))
				dto.setRepaymentDeadline(Integer.parseInt(bizBorrowInfoVO.getBorDeadline()));
			//设置投标奖励
			dto.setRewardRate(new BigDecimal(0));
			if(borrowDetails!=null){
				for (BizBorrowDetail borrowDetail : borrowDetails) {
					//判断投标金额，借款利率，期限是否正常
					if((borrowDetail.getInvestmentAmount().compareTo(BigDecimal.ZERO)>0)){
						//计息时间
						dto.setInterestDate(DateUtil.format(DateUtil.getSystemDate()));
						//投标金额
						dto.setRepaymentAmt(borrowDetail.getInvestmentAmount());
						ICalculation cal = CalculationFactory.getCalculation(dto);
						//计算投标利息
						borrowDetail.setInterest(cal.getSumInterest());			
					}
				}
			}
		}
		return borrowDetails;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 根据投标金额预算预期利息
	 * @author  Jie.Zou
	 * @date    2016年1月21日
	 * @version v1.0.0
	 * @param amount
	 * @param bizBorrow
	 * @return
	 */
	public static BigDecimal calculationInterestByAmount(BigDecimal amount,BizBorrow bizBorrow){
		if(amount.compareTo(BigDecimal.ZERO)>0){
			if(null!=bizBorrow){
				CalculationDto dto = new CalculationDto();
				//设置还款方式
				if(StringUtils.hasText(bizBorrow .getRepaymentType()))
					dto.setRepaymentType(Integer.parseInt(bizBorrow.getRepaymentType()));
				//设置管理费率
				dto.setManagementRate(new BigDecimal(0));
				//设置借款利率
				if(bizBorrow.getBorrowRate().compareTo(BigDecimal.ZERO)>0)
					dto.setRepaymentRate(bizBorrow.getBorrowRate().multiply(new BigDecimal(100)));
				//设置利息管理费
				dto.setInterestRate(new BigDecimal(0));
				//设置加息卷
				dto.setAddInterest(new BigDecimal(0));
				//设置加息卷管理费率
				dto.setAddInterestRate(new BigDecimal(0));
				//设置借款期限
				if(StringUtils.hasText(bizBorrow.getBorDeadline()))
					dto.setRepaymentDeadline(Integer.parseInt(bizBorrow.getBorDeadline()));
				//设置投标奖励
				dto.setRewardRate(new BigDecimal(0));
				//计息时间
				dto.setInterestDate(DateUtil.format(DateUtil.getSystemDate()));
				//投标金额
				dto.setRepaymentAmt(amount);
				ICalculation cal = CalculationFactory.getCalculation(dto);
				//计算投标利息
				return cal.getSumInterest();
			}
		}
		return BigDecimal.ZERO;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 通过两个时间得到相差多少天
	 * @author  Jie.Zou
	 * @date    2016年1月27日
	 * @version v1.0.0
	 * @param smdate 小时间
	 * @param bdate  大时间
	 * @return
	 */
	public static String daysBetween(String smdate,String bdate){
		try {
			Date minDate = DateUtil.format(smdate);
			Date maxDate = DateUtil.format(bdate);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			minDate = sdf.parse(sdf.format(minDate));
			maxDate = sdf.parse(sdf.format(maxDate));
			Calendar cal = Calendar.getInstance(); 
			cal.setTime(minDate);
			long time1 = cal.getTimeInMillis();
			cal.setTime(maxDate);
			long time2 = cal.getTimeInMillis();
			long days = (time2-time1)/(1000*3600*24); 
			return String.valueOf(days);  
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 借款转换成map<parentId,bizBorrow>
	 * @author  Jie.Zou
	 * @date    2016年2月18日
	 * @version v1.0.0
	 * @param bizBorrows
	 * @return
	 */
	public static Map<String, Object> toMap(List<BizBorrow> bizBorrows){
		Map<String, Object> map = new HashMap<String, Object>();
		for (BizBorrow bizBorrow : bizBorrows) {
			map.put(bizBorrow.getParentId(), bizBorrow);
		}
		return map;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 通过两个时间得到两个时间的相差多少天
	 * @author  Jie.Zou
	 * @date    2016年3月3日
	 * @version v1.0.0
	 * @param minDate
	 * @param maxDate
	 * @return
	 */
	public static Integer daysByTwoDate(Date minDate,Date maxDate){
		//保留年月日
		if(null!=minDate&&null!=maxDate){
			minDate = DateUtil.format(DateUtil.format(minDate, "yyyy-MM-dd"),"yyyy-MM-dd");
			maxDate = DateUtil.format(DateUtil.format(maxDate, "yyyy-MM-dd"),"yyyy-MM-dd");
			return (int)((maxDate.getTime()-minDate.getTime())/(1000*3600*24));
		}
		return null;
	}
}


