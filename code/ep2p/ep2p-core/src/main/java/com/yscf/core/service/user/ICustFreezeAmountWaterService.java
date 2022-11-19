package com.yscf.core.service.user;

import java.util.List;

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.user.CustFreezeWater;
import com.yscf.core.model.user.CustPoinWater;

/**
 * Description：<br> 
 * 客户冻结金额明细服务接口
 * @author  heng.wang
 * @date    2015年10月9日
 * @version v1.0.0
 */
public interface ICustFreezeAmountWaterService {
	/**
	 * Description：查询客户冻结金额明细列表
	 * @author  heng.wang
	 * @date    2015年10月9日
	 * @version v1.0.0
	 * @param userName 系统用户
	 * @return boolean
	 * @throws FrameworkException
	 */
	List<CustFreezeWater> selectAllPage(CustFreezeWater custFreezeWater, PageInfo pageInfo);
}
