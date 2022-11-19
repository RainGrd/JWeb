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
 * 2015年10月27日     JingYu.Dai		Initial Version.
 *************************************************************************
 */
package com.yscf.system.controller.export;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.achievo.framework.entity.BaseEntity;


/**
 * Description：Execl 导出 控制器
 * @author JingYu.Dai
 * @date 2015年10月27日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/exportController")
public class ExportController {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		download("E://a.xls", response);
	}
	
	/**
	 * Description：导出Execl 文件
	 * @author  JingYu.Dai
	 * @date    2015年10月27日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @throws ClassNotFoundException 
	 */
	@RequestMapping("/exportExcel")
	@ResponseBody
	public void exportExcel(String title, String headerStr, String className,
			ArrayList<BaseEntity> list, HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException {
		//ExportExcel<Object> e = new ExportExcel<Object>();
		// Class对象描述了某个特定的类对象
		Class<?> classType = Class.forName(className);
		classType.cast(className);
		//List<?> dataset = new ArrayList<BaseEntity>();
		/*
		 * dataset.a String[] headers = headerStr.split(",");
		 * e.exportExcel(title,headers, new ArrayList<SysUser>(),);
		 */
	}

	private void download(String path, HttpServletResponse response) {
		try {
			// path是指欲下载的文件的路径。
			File file = new File(path);
			// 取得文件名。
			String filename = file.getName();
			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename="
					+ new String(filename.getBytes()));
			response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(
					response.getOutputStream());
			response.setContentType("application/vnd.ms-excel;charset=gb2312");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	

}
