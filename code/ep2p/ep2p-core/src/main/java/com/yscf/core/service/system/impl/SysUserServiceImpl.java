/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 用户管理服务实现
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月6日     Simon.Hoo		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.system.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.core.dao.system.SysUserMapper;
import com.yscf.core.model.system.SysUser;
import com.yscf.core.service.system.ISysUserService;

/**
 * Description：<br> 
 * 用户管理服务实现
 * @author  Simon.Hoo
 * @date    2015年9月6日
 * @version v1.0.0
 */
@Service("sysUserService")
public class SysUserServiceImpl extends BaseService<BaseEntity, String> implements
		ISysUserService {

	@Autowired
	public SysUserServiceImpl(SysUserMapper dao) {
		super(dao);
	}
	
	@Resource(name="sysParamsService")
	SysParamsServiceImpl paramsDao;

	@Override
	public int insert(SysUser entity) throws FrameworkException {
		return super.insert(entity);
	}

	@Override
	public boolean login(SysUser sysUser) throws FrameworkException {
		SysUserMapper sysUserMapper = (SysUserMapper) getDao();
		return sysUserMapper.selectSelectiveCount(sysUser) > 0 ? true : false;
	}

	@Override
	public boolean passwordVerify(String password) throws FrameworkException {
		SysUserMapper sysUserMapper = (SysUserMapper) getDao();
		return sysUserMapper.getSysUserByPassword(password) > 0 ? true : false;
	}
	
	@Override
	public boolean userNameVerify(String userName) throws FrameworkException {
		SysUserMapper sysUserMapper = (SysUserMapper) getDao();
		return sysUserMapper.getSysUserByUserNameCount(userName) > 0 ? true : false;
	}
	@Override
	public List<SysUser> selectAll(SysUser sysUser, PageInfo pageInfo) {
		SysUserMapper mapper = (SysUserMapper) getDao();
		return mapper.selectAll(sysUser);
	}
	@Override
	public PageList<SysUser> selectAllPage(SysUser sysUser, PageInfo info) {
		SysUserMapper mapper = (SysUserMapper) getDao();
		
		return mapper.selectAllPage(sysUser,info);
	}
	
	@Override
	public int deleteByPrimaryKey(String pids){
		SysUserMapper mapper = (SysUserMapper) getDao();
		/*String [] arr = pids.split(",");
		List list = Lists.newArrayList();
		for(int i=0;i<arr.length;i++){
			list.add(arr[i]);
		}*/
		return mapper.deleteByPrimaryKey(pids);
		
	}

	public SysUser getSysUserByUserName(String userName)
			throws FrameworkException {
		SysUserMapper sysUserMapper = (SysUserMapper) getDao();
		List<SysUser> users = sysUserMapper.getSysUserByUserName(userName);
		if(null != users && users.size()>0){
			return users.get(0);
		}
		return null;
	}

	@Override
	public int deleteBtach(String pids) {
		SysUserMapper mapper = (SysUserMapper) getDao();
		String [] arr = pids.split(",");
		/*List list = Lists.newArrayList();
		for(int i=0;i<arr.length;i++){
			list.add(arr[i]);
		}*/
		List<String> list = Arrays.asList(arr);
		return mapper.deleteBtach(list);
	}

	@Override
	public int updateByPrimaryKeySelective(SysUser sysUser) {
		SysUserMapper sysUserMapper = (SysUserMapper) getDao();
		return sysUserMapper.updateByPrimaryKeySelective(sysUser);
	}

	@Override
	public PageList<SysUser> queryUsersByRoleId(String roleId,String queryStr,PageInfo info)
			throws FrameworkException {
		SysUserMapper dao = (SysUserMapper) getDao();
		
		Map<String , String> map = new HashMap<String,String>();
		map.put("roleId", roleId);
		map.put("queryStr", queryStr);
		return dao.queryUsersByRoleId(map,info);
	}

	@Override
	public PageList<SysUser> queryNoUsersByRoleId(String roleId,String queryStr,PageInfo info)
			throws FrameworkException {
		SysUserMapper dao = (SysUserMapper) getDao();
		
		Map<String , String> map = new HashMap<String,String>();
		map.put("roleId", roleId);
		map.put("queryStr", queryStr);
		return dao.queryNoUsersByRoleId(map,info);
	}

	@Override
	public SysUser getSysUserByAccountNo(String accountNo)
			throws FrameworkException {
		SysUserMapper sysUserMapper = (SysUserMapper) getDao();
		return sysUserMapper.getSysUserByAccountNo(accountNo);
	}
	
	/**
	 * Description：<br>
	 * 生成随机密码
	 * @author JingYu.Dai
	 * @date 2015年10月29日
	 * @version v1.0.0
	 * @param pwdLength 生成的密码的总长度
	 * @return 密码的字符串
	 */
	public String genRandomPassword(int pwdLength) {
		// 35是因为数组是从0开始的，26个字母+10个数字
		final int maxNum = 52;
		//26个字母的长度
		final int l = 52;
		int i,j; // 生成的随机数
		int count = 0; // 生成的密码的长度
		char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
				'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
				'x', 'y', 'z','A','B','C','D','E','F','G','H','I','J','K','L',
				'M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		// 生成的数最大为26-1
		j = Math.abs(r.nextInt(l));
		//确保第一位是字母
		pwd.append(str[j]);
		while (count < (pwdLength-1)) {
			// 生成随机数，取绝对值，防止生成负数，
			i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1
			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}
		return pwd.toString();
	}

	@Override
	public boolean passwordResetBtach(String pids) throws FrameworkException {
		SysUserMapper dao = (SysUserMapper) getDao();
		String [] arr = pids.split(",");
		List<String> ids = Arrays.asList(arr);
		//根据PID集合 查询用户列表
		List<SysUser> users = dao.getSysUserByPids(ids);
		//获取系统配置的密码长度
		//SysParams params = paramsDao.getParamsByParamKey(Constant.PASSWORD_LENGTH);
		
		for (int i = 0; i < users.size(); i++) {
			//获取随机生成的密码
			//String pwd = genRandomPassword(new Integer(params.getParamValue()));
			//加密之后的密码   待完成
			String encryptPwd = "";
			//调用发短信 邮件的接口  待完成
			
			//更改密码
			users.get(i).setPassword(encryptPwd);
		}
		//批量重置密码
		int res = dao.passwordResetBtach(users);
		
		return res > 0 ? true:false;
	}

	@Override
	public int validateUserAccount(String userAccount)
			throws FrameworkException {
		SysUserMapper dao = (SysUserMapper) getDao();
		return dao.validateUserAccount(userAccount);
	}

	@Override
	public int resetPassword(SysUser sysUser) throws FrameworkException {
		SysUserMapper dao = (SysUserMapper) getDao();
		return dao.resetPassword(sysUser);
	}

	@Override
	public List<SysUser> queryUserByRoleCode(Integer roleCode)
			throws FrameworkException {
		SysUserMapper dao = (SysUserMapper) getDao();
		return dao.queryUserByRoleCode(roleCode);
	}
}


