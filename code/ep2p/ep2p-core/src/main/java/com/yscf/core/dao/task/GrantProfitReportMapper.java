package com.yscf.core.dao.task;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.yscf.core.model.task.GrantProfitReport;

public interface GrantProfitReportMapper extends IBaseDao<BaseEntity, String> {
    int insert(GrantProfitReport record);

    int insertSelective(GrantProfitReport record);
}