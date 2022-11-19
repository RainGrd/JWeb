package com.yscf.core.dao.content;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.content.BizBorrowAgreement;

/**
 * Description：<br> 
 * 借款合同管理信息
 * @author  Lin Xu
 * @date    2015年9月22日
 * @version v1.0.0
 */
@MapperScan("bizBorrowAgreementMapper")
public interface BizBorrowAgreementMapper extends IBaseDao<BaseEntity, String> {
	
    int deleteByPrimaryKey(String pid);

    int insert(BizBorrowAgreement record);

    int insertSelective(BizBorrowAgreement record);

    BizBorrowAgreement selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(BizBorrowAgreement record);

    int updateByPrimaryKey(BizBorrowAgreement record);
    
    /**
     * Description：<br> 
     * 分页查询借款合同数据信息
     * @author  Lin Xu
     * @date    2015年9月28日
     * @version v1.0.0
     * @param bba
     * @param info
     * @return
     */
    PageList<BizBorrowAgreement> selectByPrimaryObj(@Param("bbam") BizBorrowAgreement bba,PageInfo info);

    /**
     * 
     * Description：<br> 
     * 根据名称，获取协议
     * @author  JunJie.Liu
     * @date    2016年2月23日
     * @version v1.0.0
     * @param paramValue
     * @return
     */
	BizBorrowAgreement getByName(@Param("name") String paramValue);
    
}