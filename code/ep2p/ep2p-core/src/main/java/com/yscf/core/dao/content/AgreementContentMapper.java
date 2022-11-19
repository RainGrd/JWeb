package com.yscf.core.dao.content;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.content.AgreementContent;

/**
 * Description：<br> 
 * 协议内容信息数据
 * @author  Lin Xu
 * @date    2015年9月22日
 * @version v1.0.0
 */
@MapperScan("agreementContentMapper")
public interface AgreementContentMapper extends IBaseDao<BaseEntity, String> {
    int deleteByPrimaryKey(String pid);

    int insert(AgreementContent record);

    int insertSelective(AgreementContent record);

    AgreementContent selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(AgreementContent record);

    int updateByPrimaryKey(AgreementContent record);
    
    /**
     * Description：<br> 
     * 通过查询的条件进行查询协议内容信息
     * @author  Lin Xu
     * @date    2015年9月24日
     * @version v1.0.0
     * @param record 查询的对象
     * @param pageinfo 分页信息
     * @return
     */
    PageList<AgreementContent> selectByPrimaryObj(@Param("agccont") AgreementContent record,PageInfo pageinfo);
   
    /**
     * Description：<br> 
     * 通过查询的条件进行查询协议内容信息
     * @author  Lin Xu
     * @date    2015年9月24日
     * @version v1.0.0
     * @param record 查询的对象
     * @param pageinfo 分页信息
     * @return
     */
    PageList<AgreementContent> selectByPrimaryObj(@Param("agccont") AgreementContent record);
    
    /**
	 * Description：<br> 
	 * 批量修改对象信息
	 * @author  Lin Xu
	 * @date    2015年9月28日
	 * @version v1.0.0
	 * @param agccont
	 */
	int updateBatchObj(@Param("list") List<AgreementContent> agccont);
    
}