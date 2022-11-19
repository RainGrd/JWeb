package com.yscf.db.dao.escf2.user;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.yscf.db.model.escf2.user.CaffContactPerson;

/**
 * Description：<br> 
 * 联系人表（CaffContactPerson）  数据据访问层接口
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public interface CaffContactPersonMapper extends IBaseDao<BaseEntity, String> {
    int deleteByPrimaryKey(Long caffcontactpersonid);

    int insert(CaffContactPerson record);

    int insertSelective(CaffContactPerson record);

    CaffContactPerson selectByPrimaryKey(Long caffcontactpersonid);

    int updateByPrimaryKeySelective(CaffContactPerson record);

    int updateByPrimaryKey(CaffContactPerson record);
}