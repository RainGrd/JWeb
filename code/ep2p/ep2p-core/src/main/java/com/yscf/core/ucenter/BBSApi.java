package com.yscf.core.ucenter;

import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BBSApi {
	
	private static Logger logger = LoggerFactory.getLogger(BBSApi.class);
	
	private static final int isuid = 0; //:使用用户 ID获取 0:(默认值) 使用用户名获取
	
	@SuppressWarnings("unused")
	public static String login(String userName, String pwd) {
		Client e = new Client();
		String result = e.uc_user_login(userName, pwd);
		String ucsynlogin = "";

		LinkedList<String> rs = XMLHelper.uc_unserialize(result);
		if (rs.size() > 0) {
			int uid = Integer.parseInt(rs.get(0));
			String username = rs.get(1);
			String password = rs.get(2);
			String email = rs.get(3);
			if (uid > 0) {
				ucsynlogin = e.uc_user_synlogin(uid);
			} else if (uid == -1) {
				logger.info("用户不存在,或者被删除");
			} else if (uid == -2) {
				logger.info("密码错");
			} else {
				logger.info("未定义");
			}
		} else {
			logger.info("Login failed");
		}
		return ucsynlogin;
	}

	/**
	 * 
	 * Description：<br>
	 * 退出
	 * 
	 * @author Yu.Zhang
	 * @date 2015年10月26日
	 * @version v1.0.0
	 * @return
	 */
	public static String logout() {
		Client uc = new Client();
		String ucsynlogout = uc.uc_user_synlogout();
		logger.info("退出成功" + ucsynlogout);
		return ucsynlogout;
	}

	/**
	 * 
	 * Description：<br>
	 * 用户注册
	 * 
	 * @author Yu.Zhang
	 * @date 2015年10月26日
	 * @version v1.0.0
	 * @param userName
	 * @param password
	 * @param email
	 */
	public static void reg(String userName, String password, String email) {
		Client uc = new Client();
		String returns = uc.uc_user_register(userName, password, email);
		int uid = Integer.parseInt(returns);
		if (uid <= 0) {
			if (uid == -1) {
				logger.info("用户名不合法");
			} else if (uid == -2) {
				logger.info("包含要允许注册的词语");
			} else if (uid == -3) {
				logger.info("用户名已经存在");
			} else if (uid == -4) {
				logger.info("Email 格式有误");
			} else if (uid == -5) {
				logger.info("Email 不允许注册");
			} else if (uid == -6) {
				logger.info("该 Email 已经被注册");
			} else {
				logger.info("未定义");
			}
		} else {
			logger.info("论坛同步注册用户OK:------------------------" + returns);
		}
	}

	/**
	 * 
	 * Description：<br>
	 * 更新密码
	 * 
	 * @author Yu.Zhang
	 * @date 2015年10月26日
	 * @version v1.0.0
	 * @param userName
	 * @param oldPwd
	 * @param newPwd
	 */
	public static void modifyPwd(String userName, String oldPwd, String newPwd) {
		Client uc = new Client();
		uc.uc_user_edit(userName, oldPwd, newPwd, "", 1, "", "");
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 获取论坛用户信息array	integer [0]	用户 ID string [1]	用户名 string [2]	Email 
	 * 
	 * 本接口函数用于获取用户在 UCenter 的基本数据，如用户不存在，返回值为 integer 的数值 0。
	 * @author  Yu.Zhang
	 * @date    2015年10月26日
	 * @version v1.0.0
	 * @param userName
	 * @return
	 */
	public static String  getUserInfo(String userName) {
		Client uc = new Client();
		return uc.uc_get_user(userName, isuid);
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 删除用户
	 * @author  Yu.Zhang
	 * @date    2015年10月26日
	 * @version v1.0.0
	 * @param userName
	 * @return
	 */
	public static String  deleteUser(String uid) {
		Client uc = new Client();
		return uc.uc_user_delete(uid);
	}

	public static void main(String[] args) {
		
		String bbsLoginResult = null;
		String bbsResult = "<script type='text/javascript' src='http://bbs.linuxsec.cn/api/uc.php?time=1457128706&code=0cb0Y4dKamXzBjo4FMB%2F%2BMVEq0xEu4Qjp5LjR1UoNtTMmkCVbO47vqIMaq6Ajv95yInyIUmqoy0exdArB2F%2FDs6GVizoVgak41Xsmsvbx5KylzJ6utu%2FsjmTtawPZQaB7em2X2V%2FvO8Kz75cqXSrgzbzdEHJ7UdNQnNORIoPZEJ3pa0' reload='1'></script><script type='text/javascript' src='http://192.168.1.21:8089/ep2p/api/uc.php?time=1457128706&code=acc8mw0nXRUBVs37MeQOpwzIPwkRPcAZX6R2LAwZ3fvbDieUjNjOee5yzCvU9x2GJ9bR6L6sTQbrVydOYUgCXDlOt1j%2BqmkM2NkbMnso87xUloSuMOFXseB6JWmlVgAYVlRz1C%2BrzNG2XVb%2FLalidpIyWjae%2Bd9YbRtWbyZK0ookCMg' reload='1'></script><script type='text/javascript' src='http://192.168.1.222:8081/api/api/uc.php?time=1457128706&code=f8a067TKQPVq0SBqD%2BNuU7BrEhxVMCnBkF5OUSwq0jy9kcbP4c4%2Fv1njJhIhVUhNwI3JdW6lf%2Fz0lEpvt8LrTL1aXkJzSDwJMtbv20UxDfLG8afAQsvwme40hszpWjOom7kqMuPmEVR1UXptw0xWmSXjJXBEU%2BAexKpasX6rc5Z4v34' reload='1'></script>";
		String [] results =  bbsResult.split("src=");
		for(int i = 0 ;i < results.length ; i ++){
			if(results[i].contains("http://bbs.linuxsec.cn")){
				bbsLoginResult = results[i].split("reload")[0];
				break;
			}
		}
		System.out.println(bbsLoginResult.replaceAll("'", ""));
		
//		String userName = "张雨123";
//		String pwd = "123456";
//		 BBSApi.reg(userName, pwd, "test01@163.com");

//		String rs = BBSApi.login(userName, pwd);
//		System.out.println(rs);
	}
}
