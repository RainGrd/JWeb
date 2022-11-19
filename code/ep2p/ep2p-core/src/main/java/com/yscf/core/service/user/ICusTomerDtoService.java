package com.yscf.core.service.user;

import com.yscf.core.model.user.CustomerDto;

/**
 * Description：<br>
 * 客户管理服务接口
 * 
 * @author heng.wang
 * @date 2015年9月14日
 * @version v1.0.0
 */
public interface ICusTomerDtoService {

	public CustomerDto getCustomerDtoByPid(String pid);
}
