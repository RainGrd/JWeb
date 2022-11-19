/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * TODO
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2016年1月18日     JingYu.Dai		Initial Version.
 *************************************************************************
 */
package com.yscf.db.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * Description：<br>
 * 操作配置文件类 读 写 修改 删除等操作
 * @author JingYu.Dai
 * @date 2016年1月18日
 * @version v1.0.0
 */
@SuppressWarnings("rawtypes")
public class ParseDSConfig {

	/**
	 * Description：<br> 
	 * 读取xml配置文件
	 * @author  JingYu.Dai
	 * @date    2016年1月18日
	 * @version v1.0.0
	 * @param path
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Vector readConfigInfo(String path) {
		/*String rpath = this.getClass().getResource("").getPath().substring(1)
				+ path;*/
		String rpath = path;//"F:/escf/trunk/code/ep2p/ep2p-data-migration/src/main/resources/ds.config.xml";
		Vector dsConfig = null;
		FileInputStream fi = null;
		try {
			fi = new FileInputStream(rpath);// 读取路径文件
			dsConfig = new Vector();
			SAXBuilder sb = new SAXBuilder();
			Document doc = sb.build(fi);
			Element root = doc.getRootElement();
			List pools = root.getChildren();
			Element pool = null;
			Iterator allPool = pools.iterator();
			while (allPool.hasNext()) {
				pool = (Element) allPool.next();
				DSConfigBean dscBean = new DSConfigBean();
				dscBean.setType(pool.getChild("type").getText());
				dscBean.setName(pool.getChild("name").getText());
				System.out.println(dscBean.getName());
				dscBean.setDriver(pool.getChild("driver").getText());
				dscBean.setUrl(pool.getChild("url").getText());
				dscBean.setUsername(pool.getChild("username").getText());
				dscBean.setPassword(pool.getChild("password").getText());
				dscBean.setMaxconn(Integer.parseInt(pool.getChild("maxconn")
						.getText()));
				dsConfig.add(dscBean);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				fi.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return dsConfig;
	}

	/**
	 * Description：<br> 
	 * 增加配置文件
	 * @author  JingYu.Dai
	 * @date    2016年1月18日
	 * @version v1.0.0
	 * @param path
	 * @param dsb
	 */
	@SuppressWarnings("unchecked")
	public void addConfigInfo(String path, DSConfigBean dsb) {
		String rpath = this.getClass().getResource("").getPath().substring(1)
				+ path;
		FileInputStream fi = null;
		FileOutputStream fo = null;
		try {
			fi = new FileInputStream(rpath);// 读取xml流

			SAXBuilder sb = new SAXBuilder();

			Document doc = sb.build(fi); // 得到xml
			Element root = doc.getRootElement();
			List pools = root.getChildren();// 得到xml子树

			Element newpool = new Element("pool"); // 创建新连接池

			Element pooltype = new Element("type"); // 设置连接池类型
			pooltype.setText(dsb.getType());
			newpool.addContent(pooltype);

			Element poolname = new Element("name");// 设置连接池名字
			poolname.setText(dsb.getName());
			newpool.addContent(poolname);

			Element pooldriver = new Element("driver"); // 设置连接池驱动
			pooldriver.addContent(dsb.getDriver());
			newpool.addContent(pooldriver);

			Element poolurl = new Element("url");// 设置连接池url
			poolurl.setText(dsb.getUrl());
			newpool.addContent(poolurl);

			Element poolusername = new Element("username");// 设置连接池用户名
			poolusername.setText(dsb.getUsername());
			newpool.addContent(poolusername);

			Element poolpassword = new Element("password");// 设置连接池密码
			poolpassword.setText(dsb.getPassword());
			newpool.addContent(poolpassword);

			Element poolmaxconn = new Element("maxconn");// 设置连接池最大连接
			poolmaxconn.setText(String.valueOf(dsb.getMaxconn()));
			newpool.addContent(poolmaxconn);
			pools.add(newpool);// 将child添加到root
			Format format = Format.getPrettyFormat();
			format.setIndent("");
			format.setEncoding("utf-8");
			XMLOutputter outp = new XMLOutputter(format);
			fo = new FileOutputStream(rpath);
			outp.output(doc, fo);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}
	}

	/**
	 * Description：<br> 
	 * 删除配置文件
	 * @author  JingYu.Dai
	 * @date    2016年1月18日
	 * @version v1.0.0
	 * @param path
	 * @param name
	 */
	public void delConfigInfo(String path, String name) {
		String rpath = this.getClass().getResource("").getPath().substring(1)
				+ path;
		FileInputStream fi = null;
		FileOutputStream fo = null;
		try {
			fi = new FileInputStream(rpath);// 读取路径文件
			SAXBuilder sb = new SAXBuilder();
			Document doc = sb.build(fi);
			Element root = doc.getRootElement();
			List pools = root.getChildren();
			Element pool = null;
			Iterator allPool = pools.iterator();
			while (allPool.hasNext()) {
				pool = (Element) allPool.next();
				if (pool.getChild("name").getText().equals(name)) {
					pools.remove(pool);
					break;
				}
			}
			Format format = Format.getPrettyFormat();
			format.setIndent("");
			format.setEncoding("utf-8");
			XMLOutputter outp = new XMLOutputter(format);
			fo = new FileOutputStream(rpath);
			outp.output(doc, fo);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		finally {
			try {
				fi.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		ParseDSConfig pd = new ParseDSConfig();
		String path = "ds.config.xml";
		pd.readConfigInfo(path);
		DSConfigBean dsb = new DSConfigBean();
		dsb.setType("oracle");
		dsb.setName("yyy004");
		dsb.setDriver("org.oracle.jdbc");
		dsb.setUrl("jdbc:oracle://localhost");
		dsb.setUsername("sa");
		dsb.setPassword("");
		dsb.setMaxconn(1000);
		pd.addConfigInfo(path, dsb);
		pd.delConfigInfo(path, "yyy001");
	}
}
