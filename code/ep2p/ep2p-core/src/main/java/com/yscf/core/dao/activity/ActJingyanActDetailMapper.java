package com.yscf.core.dao.activity;

import org.apache.ibatis.annotations.Param;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.activity.ActJingyanActDetail;

public interface ActJingyanActDetailMapper extends IBaseDao<BaseEntity, String> {
    int deleteByPrimaryKey(String pid);

    int insert(ActJingyanActDetail record);

    int insertSelective(ActJingyanActDetail record);

    ActJingyanActDetail selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(ActJingyanActDetail record);

    int updateByPrimaryKey(ActJingyanActDetail record);
    /**
	 * Description：根据pid查询客户经验明细,带分页功能的
	 * @author  heng.wang
	 * @date    2015年10月9日
	 * @version v1.0.0
	 * @param userName 系统客户
	 * @return boolean
	 * @throws FrameworkException
	 */
	public PageList<ActJingyanActDetail> selectJingyanDetailsById(@Param("actJingyanActDetail") ActJingyanActDetail actJingyanActDetail, PageInfo info);
}