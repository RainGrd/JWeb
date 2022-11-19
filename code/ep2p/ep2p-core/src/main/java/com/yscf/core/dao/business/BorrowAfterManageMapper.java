/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 借后管理Mapper
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年10月13日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.dao.business;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.business.BizBorrowAfter;

/**
 * Description：<br> 
 * 借后管理Mapper
 * @author  Lin Xu
 * @date    2015年10月13日
 * @version v1.0.0
 */
@MapperScan("borrowAfterManageMapper")
public interface BorrowAfterManageMapper extends IBaseDao<BaseEntity, String> {

	/**
	 * Description：<br> 
	 * 通过条件查询所有的信息数据
	 * @author  Lin Xu
	 * @date    2015年10月19日
	 * @version v1.0.0
	 * @param bbaft
	 * @param pageinfo
	 * @return
	 */
	PageList<BizBorrowAfter> selectBorrowAfterAll(@Param("bba")BizBorrowAfter bba,PageInfo pageinfo);
	
	/**
	 * Description：<br> 
	 * 通过导入的条件进行查询数据信息
	 * @author  Lin Xu
	 * @date    2015年10月21日
	 * @version v1.0.0
	 * @param bba
	 * @param expm
	 * @return
	 */
	List<BizBorrowAfter> selectBorrowAfter(HashMap<String, Object> map);
	
	/**
	 * Description：<br> 
	 * 做统计信息
	 * @author  Lin Xu
	 * @date    2015年10月23日
	 * @version v1.0.0
	 * @param map
	 * @return
	 */
	HashMap<String, BigDecimal> selectBorrowSumAfter(HashMap<String, Object> map);
	
	/**
	 * Description：<br> 
	 * 统计待还款的记录数
	 * @author  Lin Xu
	 * @date    2015年11月3日
	 * @version v1.0.0
	 * @param map
	 * @return
	 */
	public Integer selectTotalReplayNumbers(HashMap<String, Object> map);
	
}


