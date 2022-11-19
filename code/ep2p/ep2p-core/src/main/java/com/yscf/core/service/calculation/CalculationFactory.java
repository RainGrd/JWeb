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
 * 2015年11月11日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.calculation;

import com.yscf.core.model.business.CalculationDto;
import com.yscf.core.service.calculation.impl.AverageInterestCalc;
import com.yscf.core.service.calculation.impl.DayInterrestCalc;
import com.yscf.core.service.calculation.impl.LumpReapyemtCalc;
import com.yscf.core.service.calculation.impl.MonthInterestRepaymentCalc;

/**
 * Description：<br> 
 * TODO
 * @author  Yu.Zhang
 * @date    2015年11月11日
 * @version v1.0.0
 */
public class CalculationFactory {
	
	/**
	 * 
	 * Description：<br> 
	 * 根据还款方式获取计算类
	 * @author  Yu.Zhang
	 * @date    2015年11月11日
	 * @version v1.0.0
	 * @param type 1 按月等额本息 2 按月付息，到期还本 3 到期一次性还本息
	 * @return
	 */
	public static ICalculation getCalculation(CalculationDto dto){
		ICalculation calculation = null;
		if(1 == dto.getRepaymentType()){
			calculation = new AverageInterestCalc(dto);
		}else if(2 == dto.getRepaymentType()){
			calculation = new MonthInterestRepaymentCalc(dto);
		}else if(3 == dto.getRepaymentType()){
			calculation = new LumpReapyemtCalc(dto);
		}else if(4 == dto.getRepaymentType()){
			calculation = new DayInterrestCalc(dto);
		}
		return calculation;
	}

}


