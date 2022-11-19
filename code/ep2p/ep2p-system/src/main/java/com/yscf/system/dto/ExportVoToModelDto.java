/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 导出excel的vo转model工具类
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年10月23日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.system.dto;

import com.achievo.framework.util.StringUtil;
import com.yscf.common.util.SpecialChartConstant;
import com.yscf.core.model.comm.ExportObjectModel;
import com.yscf.system.vo.ExportObjectVo;

/**
 * Description：<br> 
 * 导出excel的vo转model工具类
 * @author  Lin Xu
 * @date    2015年10月23日
 * @version v1.0.0
 */
public class ExportVoToModelDto {
	
	/**
	 * Description：<br> 
	 * 转换
	 * @author  Lin Xu
	 * @date    2015年10月23日
	 * @version v1.0.0
	 * @param eov
	 * @return
	 */
	public static ExportObjectModel voToModel(ExportObjectVo expvo){
		String exportSet = expvo.getExportSet();
		ExportObjectModel eom = new ExportObjectModel();
		if("1".equals(exportSet) && StringUtil.isNotEmpty(expvo.getCheckrows())){
			String ckrows = expvo.getCheckrows();
			//替换特殊字符
			ckrows = ckrows.replace(SpecialChartConstant.CHART_ADD, ",");
			String[] pids = ckrows.split(",");
			eom.setCheckrows(pids);
		}
		if("2".equals(exportSet) && StringUtil.isNotEmpty(expvo.getCheckpage()) 
				&& StringUtil.isNotEmpty(expvo.getCheckpageE()) && null != expvo.getRows()){
			Integer startsize = Integer.parseInt(expvo.getCheckpage()) <= 0 ? 0 : Integer.parseInt(expvo.getCheckpage()) - 1;
			Integer esize = Integer.parseInt(expvo.getCheckpageE()) - Integer.parseInt(expvo.getCheckpage()) + 1;
			Integer endpsize = esize * expvo.getRows() <= 0 ? expvo.getRows() : esize * expvo.getRows();
			startsize = startsize * expvo.getRows();
			eom.setStartCheckpage(startsize);
			eom.setEndCheckpage(endpsize);
		}
		if("3".equals(exportSet) && StringUtil.isNotEmpty(expvo.getCheckrecords()) 
				&& StringUtil.isNotEmpty(expvo.getCheckrecordsE()) ){
			eom.setStartCheckrecords(Integer.parseInt(expvo.getCheckrecords()));
			eom.setEndCheckrecords(Integer.parseInt(expvo.getCheckrecordsE()));
		}
		eom.setExportSet(Integer.parseInt(exportSet));
		return eom;
	}
	
	/**
	 * Description：<br> 
	 * 转换 使用limit进行截取行数信息
	 * @author  Lin Xu
	 * @date    2015年10月23日
	 * @version v1.0.0
	 * @param eov
	 * @return
	 */
	public static ExportObjectModel voToLimitModel(ExportObjectVo expvo){
		String exportSet = expvo.getExportSet();
		ExportObjectModel eom = new ExportObjectModel();
		if("1".equals(exportSet) && StringUtil.isNotEmpty(expvo.getCheckrows())){
			String ckrows = expvo.getCheckrows();
			//替换特殊字符
			ckrows = ckrows.replace(SpecialChartConstant.CHART_ADD, ",");
			String[] pids = ckrows.split(",");
			eom.setCheckrows(pids);
		}
		if("2".equals(exportSet) && StringUtil.isNotEmpty(expvo.getCheckpage()) 
				&& StringUtil.isNotEmpty(expvo.getCheckpageE()) && null != expvo.getRows()){
			Integer startsize = Integer.parseInt(expvo.getCheckpage()) <= 0 ? 0 : Integer.parseInt(expvo.getCheckpage()) - 1;
			Integer esize = Integer.parseInt(expvo.getCheckpageE()) - Integer.parseInt(expvo.getCheckpage()) + 1;
			Integer endpsize = esize * expvo.getRows() <= 0 ? expvo.getRows() : esize * expvo.getRows();
			startsize = startsize * expvo.getRows();
			eom.setStartCheckpage(startsize);
			eom.setEndCheckpage(endpsize);
		}
		if("3".equals(exportSet) && StringUtil.isNotEmpty(expvo.getCheckrecords()) 
				&& StringUtil.isNotEmpty(expvo.getCheckrecordsE()) ){
			Integer ss = Integer.parseInt(expvo.getCheckrecords());
			Integer ee = Integer.parseInt(expvo.getCheckrecordsE());
			Integer start = ss - 1 <= 0 ? 0 : (ss-1);
			Integer ends = (ee - ss) < 1 ? 0 : ((ee - ss) + 1);
			eom.setStartCheckrecords(start);
			eom.setEndCheckrecords(ends);
		}
		eom.setExportSet(Integer.parseInt(exportSet));
		return eom;
	}

}


