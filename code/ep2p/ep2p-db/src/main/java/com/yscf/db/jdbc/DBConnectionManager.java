/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2016年1月18日     JingYu.Dai		Initial Version.
 *************************************************************************
 */
package com.yscf.db.jdbc;

import java.sql.Connection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

/**
 * Description：<br>
 * 线程池管理
 * @author JingYu.Dai
 * @date 2016年1月18日
 * @version v1.0.0
 */
public class DBConnectionManager {
	static private DBConnectionManager instance;// 唯一数据库连接池管理实例类
	private Vector<Object> drivers = new Vector<Object>();// 驱动信息
	private Hashtable<String, DBConnectionPool> pools = new Hashtable<String, DBConnectionPool>();// 连接池

	/**
	 * 实例化管理类
	 */
	public DBConnectionManager() {
		this.init();
	}

	/**
	 * Description：<br>
	 * 得到唯一实例管理类
	 * @author JingYu.Dai
	 * @date 2016年1月18日
	 * @version v1.0.0
	 * @return
	 */
	static synchronized public DBConnectionManager getInstance() {
		if (instance == null) {
			instance = new DBConnectionManager();
		}
		return instance;

	}

	/**
	 * Description：<br>
	 * 释放连接
	 * @author JingYu.Dai
	 * @date 2016年1月18日
	 * @version v1.0.0
	 * @param name  链接池名字
	 * @param con  连接对象
	 */
	public void freeConnection(String name, Connection con) {
		DBConnectionPool pool = pools.get(name);// 根据关键名字得到连接池
		if (pool != null)
			pool.freeConnection(con);// 释放连接
	}

	/**
	 * Description：<br>
	 * 得到一个连接 根据连接池的名字name
	 * @author JingYu.Dai
	 * @date 2016年1月18日
	 * @version v1.0.0
	 * @param name
	 * @return Connection
	 */
	public Connection getConnection(String name) {
		DBConnectionPool pool = null;
		Connection con = null;
		pool = pools.get(name);// 从名字中获取连接池
		con = pool.getConnection();// 从选定的连接池中获得连接
		if (con != null)
			System.out.println("得到连接。。。");
		return con;
	}

	/**
	 * Description：<br>
	 * 得到一个连接，根据连接池的名字和等待时间
	 * @author JingYu.Dai
	 * @date 2016年1月18日
	 * @version v1.0.0
	 * @param name
	 * @param timeout
	 * @return
	 */
	public Connection getConnection(String name, long timeout) {
		DBConnectionPool pool = null;
		Connection con = null;
		pool = pools.get(name);// 从名字中获取连接池
		con = pool.getConnection(timeout);// 从选定的连接池中获得连接
		System.out.println("得到连接。。。");
		return con;
	}

	/**
	 * Description：<br> 
	 * 释放所有连接
	 * @author  JingYu.Dai
	 * @date    2016年3月1日
	 * @version v1.0.0
	 */
	public synchronized void release() {
		Enumeration<DBConnectionPool> allpools = pools.elements();
		while (allpools.hasMoreElements()) {
			DBConnectionPool pool = allpools.nextElement();
			if (pool != null)
				pool.release();
		}
		pools.clear();
	}

	/**
	 * Description：<br> 
	 * 创建连接池
	 * @author  JingYu.Dai
	 * @date    2016年3月1日
	 * @version v1.0.0
	 * @param dsb
	 */
	private void createPools(DSConfigBean dsb) {
		DBConnectionPool dbpool = new DBConnectionPool();
		dbpool.setName(dsb.getName());
		dbpool.setDriver(dsb.getDriver());
		dbpool.setUrl(dsb.getUrl());
		dbpool.setUser(dsb.getUsername());
		dbpool.setPassword(dsb.getPassword());
		dbpool.setMaxConn(dsb.getMaxconn());
		System.out.println("ioio:" + dsb.getMaxconn());
		pools.put(dsb.getName(), dbpool);
	}

	/**
	 * Description：<br> 
	 * 初始化连接池的参数
	 * @author  JingYu.Dai
	 * @date    2016年3月1日
	 * @version v1.0.0
	 */
	private void init() {
		// 加载驱动程序
		this.loadDrivers();
		// 创建连接池
		Iterator<?> alldriver = drivers.iterator();
		while (alldriver.hasNext()) {
			this.createPools((DSConfigBean) alldriver.next());
			System.out.println("创建连接池。。。");
		}
		System.out.println("创建连接池完毕。。。");
	}

	/**
	 * Description：<br>
	 * 加载驱动程序
	 * @author JingYu.Dai
	 * @date 2016年1月18日
	 * @version v1.0.0
	 */
	@SuppressWarnings("unchecked")
	private void loadDrivers() {
		ParseDSConfig pd = new ParseDSConfig();
		// 读取数据库配置文件
		drivers = pd.readConfigInfo(this.getClass().getResource("/ds.config.xml").getPath().substring(1));
		System.out.println("加载驱动程序。。。");
	}

	/*public static void main(String[] args) {
		DBConnectionManager db = DBConnectionManager.getInstance();
		Connection conn = db.getConnection("escf3");
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM t_sys_user";
			ResultSet rs = stmt.executeQuery(sql);
			int i = 0;
			while (rs.next()) {
				String name = rs.getString("name");
				System.out.println(name);
				i++;
			}
			System.out.println(i);
			db.freeConnection("escf3", conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}*/

}
