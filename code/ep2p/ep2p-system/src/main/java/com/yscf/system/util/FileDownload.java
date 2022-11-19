package com.yscf.system.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: FileDownload
 * @Description: 下载本地文件
 * @author: Chong.Zeng
 * @date: 2015年1月5日 下午7:47:41
 */
public class FileDownload {

	/**
	 * 下载文件
	 * 
	 * @param response
	 * @param request
	 * @param path
	 *            要下载的文件
	 * @throws IOException
	 */
	public static void downloadLocal(HttpServletResponse response, HttpServletRequest request, String path) throws IOException {
		File file = new File(path);// path是根据日志路径和文件名拼接出来的
		String filename = request.getParameter("fileName");
		filename = (filename == null? file.getName():filename);// 获取日志文件名称
		filename = new String(filename.getBytes("iso-8859-1"), "utf-8");
		InputStream fis = new BufferedInputStream(new FileInputStream(path));
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		response.reset();
		// 先去掉文件名称中的空格,然后转换编码格式为utf-8,保证不出现乱码,这个文件名称用于浏览器的下载框中自动显示的文件名
		response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.replaceAll(" ", "").getBytes("iso8859-1"), "utf-8"));
		response.addHeader("Content-Length", "" + file.length());
		OutputStream os = new BufferedOutputStream(response.getOutputStream());
		response.setContentType("application/octet-stream");
		os.write(buffer);// 输出文件
		os.flush();
		os.close();
	}

	/**
	 * Description：<br> 
	 * 通过相对路径进行下载，但须要配置tomcat
	 * @author  Lin Xu
	 * @date    2015年10月12日
	 * @version v1.0.0
	 * @param response
	 * @param request
	 * @param path
	 * @param fileName
	 * @throws IOException
	 */
	public static void downloadLocalFile(HttpServletResponse response, HttpServletRequest request, String path,String fileName) throws IOException {
		path = request.getServletContext().getRealPath("/") + path;
		File file = new File(path);// path是根据日志路径和文件名拼接出来的
		String filename = file.getName();// 获取日志文件名称
		if(fileName!=null || ! "".equals(fileName)){
			filename = fileName+"."+filename.substring(filename.lastIndexOf(".")+1);
		}
		InputStream fis = new BufferedInputStream(new FileInputStream(path));
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		response.reset();

		// 先去掉文件名称中的空格,然后转换编码格式为utf-8,保证不出现乱码,这个文件名称用于浏览器的下载框中自动显示的文件名
		/*response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.replaceAll(" ", "").getBytes("utf-8"), "iso8859-1"));*/
		response.addHeader("Content-Disposition", "attachment;filename=" +  java.net.URLEncoder.encode(filename.replaceAll(" ", ""), "UTF-8"));
		response.addHeader("Content-Length", "" + file.length());
		OutputStream os = new BufferedOutputStream(response.getOutputStream());
		response.setContentType("application/octet-stream");
		os.write(buffer);// 输出文件
		os.flush();
		os.close();
	}
	
	/**
	 * Description：<br> 
	 * 通过绝对路径进行下载文件
	 * @author  Lin Xu
	 * @date    2015年10月12日
	 * @version v1.0.0
	 * @param response
	 * @param request
	 * @param path
	 * @param fileName
	 * @throws IOException
	 */
	public static void downloadAbsoluteFile(HttpServletResponse response, HttpServletRequest request, String path,String fileName) throws IOException {
		File file = new File(path);// path是根据日志路径和文件名拼接出来的
		String filename = file.getName();// 获取日志文件名称
		if(fileName!=null || ! "".equals(fileName)){
			filename = fileName+"."+filename.substring(filename.lastIndexOf(".")+1);
		}
		InputStream fis = new BufferedInputStream(new FileInputStream(path));
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		response.reset();

		// 先去掉文件名称中的空格,然后转换编码格式为utf-8,保证不出现乱码,这个文件名称用于浏览器的下载框中自动显示的文件名
		/*response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.replaceAll(" ", "").getBytes("utf-8"), "iso8859-1"));*/
		response.addHeader("Content-Disposition", "attachment;filename=" +  java.net.URLEncoder.encode(filename.replaceAll(" ", ""), "UTF-8"));
		response.addHeader("Content-Length", "" + file.length());
		OutputStream os = new BufferedOutputStream(response.getOutputStream());
		response.setContentType("application/octet-stream");
		os.write(buffer);// 输出文件
		os.flush();
		os.close();
	}
	
	/**
	 * Description：<br> 
	 * 通过传入文件进行下载文件信息
	 * @author  Lin Xu
	 * @date    2015年10月21日
	 * @version v1.0.0
	 * @param response
	 * @param request
	 * @param file
	 * @param isdel
	 * @throws IOException
	 */
	public static void downloadAbsoluteFile(HttpServletResponse response, HttpServletRequest request, File file,boolean isdel) throws IOException {
		String filename = file.getName();// 获取日志文件名称
		/* 
		if(filename!=null || ! "".equals(filename)){
			filename = filename+"."+filename.substring(filename.lastIndexOf(".")+1);
		}
		*/
		InputStream fis = new BufferedInputStream(new FileInputStream(file));
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		response.reset();
		response.addHeader("Content-Disposition", "attachment;filename=" +  java.net.URLEncoder.encode(filename.replaceAll(" ", ""), "UTF-8"));
		response.addHeader("Content-Length", "" + file.length());
		OutputStream os = new BufferedOutputStream(response.getOutputStream());
		response.setContentType("application/octet-stream");
		os.write(buffer);// 输出文件
		os.flush();
		os.close();
		//进行删除文件
		if(isdel){
			if(file.exists()){
				if(file.isFile()){
					//删除文件
					file.delete();
				}
			}
		}
	}
	
	
	
	
}
