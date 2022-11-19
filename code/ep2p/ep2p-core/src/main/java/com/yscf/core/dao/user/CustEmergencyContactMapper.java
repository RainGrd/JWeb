package com.yscf.core.dao.user;

import org.apache.ibatis.annotations.Param;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.user.CustEmergencyContact;

public interface CustEmergencyContactMapper extends IBaseDao<BaseEntity, String> {
    int insert(CustEmergencyContact record);

    int insertSelective(CustEmergencyContact record);
    /**
   	 * Description：根据客户id修改客户用户名
   	 * @author  heng.wang
   	 * @date    2015年11月13日
   	 * @version v1.0.0
   	 * @param userName 系统客户
   	 * @return boolean
   	 * @throws FrameworkException
   	 */
    public int updateCusNameByCusPid(@Param("record") CustEmergencyContact record);
    /**
   	 * Description：根据客户id查询旧密码
   	 * @author  heng.wang
   	 * @date    2015年11月13日
   	 * @version v1.0.0
   	 * @param userName 系统客户
   	 * @return boolean
   	 * @throws FrameworkException
   	 */
    public PageList<CustEmergencyContact> selectOriginalByCusPid(String custPid);
    /**
   	 * Description：修改重置后的登录密码
   	 * @author  heng.wang
   	 * @date    2015年11月13日
   	 * @version v1.0.0
   	 * @param userName 系统客户
   	 * @return boolean
   	 * @throws FrameworkException
   	 */
    public int saveNewPassWord(@Param("record") CustEmergencyContact record);
    /**
   	 * Description：修改重置后的交易密码
   	 * @author  heng.wang
   	 * @date    2015年11月13日
   	 * @version v1.0.0
   	 * @param userName 系统客户
   	 * @return boolean
   	 * @throws FrameworkException
   	 */
    public int saveNewTradePassWord(@Param("record") CustEmergencyContact record);
    /**
   	 * Description：根据客户查登录系统时间
   	 * @author  heng.wang
   	 * @date    2015年11月16日
   	 * @version v1.0.0
   	 * @param userName 系统客户
   	 * @return boolean
   	 * @throws FrameworkException
   	 */
    public PageList<CustEmergencyContact> selectLoginTimeByAccount(String account); 
    /**
   	 * Description：修改紧急联系人信息
   	 * @author  heng.wang
   	 * @date    2015年12月8日
   	 * @version v1.0.0
   	 * @param userName 系统客户
   	 * @return boolean
   	 * @throws FrameworkException
   	 */
    public int updateEmergencyContact(@Param("contact") CustEmergencyContact contact);
    
    
}