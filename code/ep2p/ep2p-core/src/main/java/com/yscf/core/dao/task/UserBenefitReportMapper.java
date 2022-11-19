package com.yscf.core.dao.task;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.yscf.core.model.task.UserBenefitReport;

public interface UserBenefitReportMapper extends IBaseDao<BaseEntity, String> {
    int insert(UserBenefitReport record);

    int insertSelective(UserBenefitReport record);
}