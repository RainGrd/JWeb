package com.yscf.core.dao.content;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.content.AgreementContTemp;

/**
 * Description：<br> 
 * 协议内容模板Mapper
 * @author  Lin Xu
 * @date    2015年9月22日
 * @version v1.0.0
 */
@MapperScan("agreementContTempMapper")
public interface AgreementContTempMapper extends IBaseDao<BaseEntity, String> {
	
    int deleteByPrimaryKey(String pid);

    int insert(AgreementContTemp record);

    int insertSelective(AgreementContTemp record);

    AgreementContTemp selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(AgreementContTemp record);

    int updateByPrimaryKey(AgreementContTemp record);
    
    /**
     * Description：<br> 
     * 通过查询的条件进行查询协议模板信息
     * @author  Lin Xu
     * @date    2015年9月24日
     * @version v1.0.0
     * @param record 查询的对象
     * @param pageinfo 分页信息
     * @return
     */
    PageList<AgreementContTemp> selectByPrimaryObj(@Param("agctemp") AgreementContTemp record,PageInfo pageinfo);
    
    /**
     * Description：<br> 
     * 查询所有的协议模板信息
     * @author  Lin Xu
     * @date    2015年9月29日
     * @version v1.0.0
     * @param record
     * @return
     */
    List<AgreementContTemp> selectByAllObj(AgreementContTemp record);
}