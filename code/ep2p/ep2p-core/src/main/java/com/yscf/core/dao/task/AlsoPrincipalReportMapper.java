package com.yscf.core.dao.task;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.yscf.core.model.task.AlsoPrincipalReport;

public interface AlsoPrincipalReportMapper extends IBaseDao<BaseEntity, String> {
    int insert(AlsoPrincipalReport record);

    int insertSelective(AlsoPrincipalReport record);
}