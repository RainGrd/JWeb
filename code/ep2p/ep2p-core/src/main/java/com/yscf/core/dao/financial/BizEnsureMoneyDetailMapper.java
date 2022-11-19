package com.yscf.core.dao.financial;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.financial.BizEnsureMoneyDetail;

/**
 * Description：<br> 
 * 备付金详情
 * @author  jenkin.yu
 * @date    2015年10月22日
 * @version v1.0.0
 */
@MapperScan("bizEnsureMoneyDetailMapper")
public interface BizEnsureMoneyDetailMapper extends IBaseDao<BaseEntity, String> {
    int deleteByPrimaryKey(String pid);

    int insert(BizEnsureMoneyDetail record);

    int insertSelective(BizEnsureMoneyDetail record);

    BizEnsureMoneyDetail selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(BizEnsureMoneyDetail record);

    int updateByPrimaryKey(BizEnsureMoneyDetail record);
    
    /**
     * 
     * Description：<br> 
     * 列表查询带分页
     * @author  Yu.Zhang
     * @date    2015年10月29日
     * @version v1.0.0
     * @param record
     * @param info
     * @return
     */
    PageList<BizEnsureMoneyDetail> selectAllPage(@Param("parasMap")BizEnsureMoneyDetail record ,PageInfo info);
    
    /**
     * 
     * Description：<br> 
     * 收入支出 总计
     * @author  Yu.Zhang
     * @date    2015年10月29日
     * @version v1.0.0
     * @param record
     * @return
     */
    List<BizEnsureMoneyDetail> selectSumHappenValue(BizEnsureMoneyDetail record);

    /**
     * 
     * Description：<br> 
     * 数据查询
     * @author  Yu.Zhang
     * @date    2015年10月30日
     * @version v1.0.0
     * @param bizEnsureMoneyDetail
     * @return
     */
	List<BizEnsureMoneyDetail> selectAll(BizEnsureMoneyDetail bizEnsureMoneyDetail);

	/**
	 * 
	 * Description：<br> 
	 * 导出数据查询
	 * @author  Yu.Zhang
	 * @date    2015年10月30日
	 * @version v1.0.0
	 * @param map
	 * @return
	 */
	List<BizEnsureMoneyDetail> selectEnsureMoneyDetail(HashMap<String, Object> map);
}