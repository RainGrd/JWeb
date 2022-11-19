package com.yscf.core.dao.task;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.yscf.core.model.task.CumulativeIinvestReport;

public interface CumulativeIinvestReportMapper extends IBaseDao<BaseEntity, String> {
    int insert(CumulativeIinvestReport record);

    int insertSelective(CumulativeIinvestReport record);
}