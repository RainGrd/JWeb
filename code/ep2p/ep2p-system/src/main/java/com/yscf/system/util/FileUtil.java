package com.yscf.system.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.LoggerFactory;



/**
 * 
 * Description：<br> 
 * 上传文件工具类
 * @author  Yu.Zhang
 * @date    2015年9月22日
 * @version v1.0.0
 */
public class FileUtil {
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(FileUtil.class);
	
	/**
	 * 绝对路径
	 */
	private static final String absolute = "absolute";
	
	/**
	 * 相对路径
	 */
	private static final String relative = "relative";
	
	/**
	 * 从文件路径中取出文件名
	 */
	public static String takeOutFileName(String filePath) {
		int pos = filePath.lastIndexOf(".");
		if (pos > 0) {
			String pressix = filePath.substring(pos + 1);
			UUID uuid = UUID.randomUUID();
			return uuid + "." + pressix;
		} else {
			return filePath;
		}
	}

	/**
	 * 从request中取出FileUploadStatus Bean
	 */
	public static FileUploadStatus getStatusBean(HttpServletRequest request) {
		BeanControler beanCtrl = BeanControler.getInstance();
		return beanCtrl.getUploadStatus(request.getRemoteAddr());
	}

	/**
	 * 把FileUploadStatus Bean保存到类控制器BeanControler
	 */
	public static void saveStatusBean(HttpServletRequest request, FileUploadStatus statusBean) {
		statusBean.setUploadAddr(request.getRemoteAddr());
		BeanControler beanCtrl = BeanControler.getInstance();
		beanCtrl.setUploadStatus(statusBean);
	}

	/**
	 * 删除已经上传的文件
	 */
	public static void deleteUploadedFile(HttpServletRequest request, String uploadPath) {
		FileUploadStatus satusBean = getStatusBean(request);
		for (int i = 0; i < satusBean.getUploadFileUrlList().size(); i++) {
			File uploadedFile = new File(uploadPath + File.separator + satusBean.getUploadFileUrlList().get(i));
			uploadedFile.delete();
		}
		satusBean.getUploadFileUrlList().clear();
		satusBean.setStatus("删除已上传的文件");
		saveStatusBean(request, satusBean);
	}

	/**
	 * 上传过程中出错处理
	 */
	public static void uploadExceptionHandle(HttpServletRequest request, String errMsg, String uploadPath) throws ServletException, IOException {
		// 首先删除已经上传的文件
		deleteUploadedFile(request, uploadPath);
		FileUploadStatus satusBean = getStatusBean(request);
		satusBean.setStatus(errMsg);
		saveStatusBean(request, satusBean);
	}

	/**
	 * 初始化文件上传状态Bean
	 */
	public static FileUploadStatus initStatusBean(HttpServletRequest request, String uploadPath) {
		FileUploadStatus satusBean = new FileUploadStatus();
		satusBean.setStatus("正在准备处理");
		satusBean.setUploadTotalSize(request.getContentLength());
		satusBean.setProcessStartTime(System.currentTimeMillis());
		satusBean.setBaseDir(request.getContextPath() + uploadPath);
		return satusBean;
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> fileUpload(HttpServletRequest request, HttpServletResponse response, String type, String modelPath, String rootPath, HashMap<String, Integer> map,String[] filevlitype,String fileRootIs) throws ServletException, IOException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		Map<String, Object> result = new HashMap<String, Object>();
		
		// 设置内存缓冲区，超过后写入临时文件
		factory.setSizeThreshold(10240000);
		String tempPath = null; 	// 临时文件路径
		String uploadPath = null;	// 上传文件路径
		
		// 绝对路径
		if(FileUtil.absolute.equals(fileRootIs)){
			tempPath = rootPath + File.separator + "temp";
			uploadPath = rootPath + File.separator +modelPath;
		}else if(FileUtil.relative.equals(fileRootIs)){ // 相对路径
			tempPath = request.getServletContext().getRealPath("/") + rootPath + File.separator + "temp";
			uploadPath = request.getServletContext().getRealPath("/") + modelPath;
		}
		
		// 设置临时文件存储位置
		File f = new File(tempPath);
		if (!f.exists()) {
			f.mkdirs();
		}
		factory.setRepository(f);
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		// 设置上传目录位置
		File f1 = new File(uploadPath);
		if (!f1.exists()) {
			f1.mkdirs();
		}

		// 设置单个文件的最大上传值
		upload.setFileSizeMax(map.get("maxFileSize"));
		// 设置整个request的最大值
		upload.setSizeMax(map.get("maxReqSize"));
		upload.setProgressListener(new FileUploadListener(request));
		// 保存初始化后的FileUploadStatus Bean
		saveStatusBean(request, initStatusBean(request, uploadPath));
		FileUploadStatus satusBean = getStatusBean(request);
		String forwardURL = "";

		try {
			@SuppressWarnings("rawtypes")
			List items = upload.parseRequest(request);
			result.put("items", items);
			// 获得返回url
			for (int i = 0; i < items.size(); i++) {
				FileItem item = (FileItem) items.get(i);
				if (item.isFormField()) {
					forwardURL = item.getString();
					break;
				}
			}

			// 处理文件上传
			for (int i = 0; i < items.size(); i++) {
				FileItem item = (FileItem) items.get(i);

				// 取消上传
				if (getStatusBean(request).getCancel()) {
					deleteUploadedFile(request, uploadPath);
					break;
				}
				// 保存文件
				else if (!item.isFormField() && item.getName().length() > 0) {

					String fileName = takeOutFileName(item.getName());
					boolean b=vlifiletype(fileName,filevlitype);
					if (!b) {
						result.put("flag", false);
						logger.info("上传文件类型不支持,请检查 config.properties 文件");
						return result;
					}
					File uploadedFile = new File(uploadPath + File.separator + fileName);

					item.write(uploadedFile);
					// 更新上传文件列表
					satusBean.getUploadFileUrlList().add(fileName);
					saveStatusBean(request, satusBean);
					result.put("path", modelPath + "/" + fileName);
					result.put("flag", true);
					// Thread.sleep(500);
				}
			}
		} catch (FileUploadException e) {
			uploadExceptionHandle(request, "上传文件时发生错误:" + e.getMessage(), uploadPath);
			result.put("flag", false);
			result.put("path", "");
			return result;
		} catch (Exception e) {
			uploadExceptionHandle(request, "保存上传文件时发生错误:" + e.getMessage(), uploadPath);
			result.put("flag", false);
			result.put("path", "");
			return result;
		}
		if (forwardURL.length() == 0) {
			forwardURL = "";
		}
		request.setAttribute("msg", "<font size=2><b>文件上传成功!</b></font>");
		return result;
	}
	private static boolean vlifiletype(String fileName, String[] filevlitype) {
		for (int i = 0; i < filevlitype.length; i++) {
			if (fileName.toLowerCase().endsWith(filevlitype[i].toLowerCase())) {
			
				return true;
			
			}
		}
		return false;
	}

	/**
	 * @Description: 上传文件
	 * @param request
	 * @param response
	 * @param type
	 * @param rootPath
	 * @param map
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @author: Chong.Zeng
	 * @date: 2015年1月6日 下午7:43:32
	 */
	public static Map<String, Object> processFileUpload(HttpServletRequest request, HttpServletResponse response, String type, String rootPath, HashMap<String, Integer> map, String formaterDate,String[] filevlitype,String fileRootIs) throws ServletException, IOException {
		String modelPath = type + File.separator + getDateToString(formaterDate);
		return fileUpload(request, response, type, modelPath, rootPath, map,filevlitype,fileRootIs);
	}

	/**
	 * 回应上传状态查询
	 */
	public static void responseStatusQuery(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/xml;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		FileUploadStatus satusBean = getStatusBean(request);
		response.getWriter().write(satusBean.toJSon());
	}

	/**
	 * 处理取消文件上传
	 */
	public static void processCancelFileUpload(HttpServletRequest request, HttpServletResponse response) throws IOException {
		FileUploadStatus satusBean = getStatusBean(request);
		satusBean.setCancel(true);
		saveStatusBean(request, satusBean);
		responseStatusQuery(request, response);
	}

	/**
	 * @Description: 获取String型的时间
	 * @return
	 * @author: Chong.Zeng
	 * @date: 2015年1月7日 下午2:13:25
	 */
	public static String getDateToString(String format) {
		SimpleDateFormat sf = new SimpleDateFormat(format);
		return sf.format(new Date());
	}
}
