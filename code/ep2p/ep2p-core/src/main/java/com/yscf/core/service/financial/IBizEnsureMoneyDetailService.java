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
 * 2015年10月22日     jenkin.yu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.financial;

import java.math.BigDecimal;
import java.util.List;

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.comm.ExportObjectModel;
import com.yscf.core.model.financial.BizEnsureMoneyDetail;

/**
 * Description：<br> 
 * TODO
 * @author  jenkin.yu
 * @date    2015年10月22日
 * @version v1.0.0
 */
public interface IBizEnsureMoneyDetailService {
	/**
	 * 
	 * Description：<br> 
	 * 列表查询
	 * @author  Yu.Zhang
	 * @date    2015年10月29日
	 * @version v1.0.0
	 * @param detail
	 * @param info
	 * @return
	 * @throws FrameworkException
	 */
	PageList<BizEnsureMoneyDetail> selectBizEnsureDetailPage(BizEnsureMoneyDetail detail,PageInfo info) throws FrameworkException;
	
	/**
	 * 
	 * Description：<br> 
	 * 收入支出总计
	 * @author  Yu.Zhang
	 * @date    2015年10月29日
	 * @version v1.0.0
	 * @param detail
	 * @return
	 */
	BigDecimal selectSumHappenValue(BizEnsureMoneyDetail detail);

	/**
	 * 
	 * Description：<br> 
	 * 查询导出数据
	 * @author  Yu.Zhang
	 * @date    2015年10月30日
	 * @version v1.0.0
	 * @param bizEnsureMoneyDetail
	 * @param eom
	 * @return
	 */
	List<BizEnsureMoneyDetail> selectEnsureMoneyDetail(BizEnsureMoneyDetail bizEnsureMoneyDetail, ExportObjectModel eom);
	
	/**
	 * 
	 * Description：<br> 
	 * 明细数据查询，不带分页
	 * @author  Yu.Zhang
	 * @date    2015年10月30日
	 * @version v1.0.0
	 * @param bizEnsureMoneyDetail
	 * @return
	 */
	List<BizEnsureMoneyDetail> selectAll(BizEnsureMoneyDetail bizEnsureMoneyDetail);
}


