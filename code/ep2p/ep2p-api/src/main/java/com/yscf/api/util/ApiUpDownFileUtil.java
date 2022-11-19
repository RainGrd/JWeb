/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 实现客户端上传下载文件工具类
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2016年1月18日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.api.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import com.achievo.framework.util.StringUtil;
import com.yscf.api.vo.system.ApiFileObject;

/**
 * Description：<br> 
 * 实现客户端上传下载文件工具类
 * @author  Lin Xu
 * @date    2016年1月18日
 * @version v1.0.0
 */
public class ApiUpDownFileUtil {
	
	private final static Logger logger = Logger.getLogger(ApiUpDownFileUtil.class);
	//返回文件上传对象
	public final static String FILE_UPOBJECT = "fileUpObject";
	
	
	/**
	 * Description：<br> 
	 * 读取上传的文件信息
	 * @author  Lin Xu
	 * @date    2016年1月18日
	 * @version v1.0.0
	 * @param request 请求对象
	 * @param response 请求内容对象
	 * @param basePath 基础配置路径
	 * @param savePath 保存文件的路径
	 * @param rflname 保存的文件名称（包括后缀名）
	 * @return
	 */
	public static HashMap<String, Object> readUPloadFile(HttpServletRequest request, HttpServletResponse response,
			String basePath,String savePath,String rflname,String rftype){
		HashMap<String, Object> rmap = new HashMap<String, Object>();
		rmap.put("status", true);
		response.setContentType("text/html;charset=UTF-8");  
		FileItemFactory fitemfactory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(fitemfactory); 
		upload.setSizeMax(60485760);
		upload.setFileSizeMax(10485760);
		File directory = null;    
		List<FileItem> items = new ArrayList<FileItem>(); 
		InputStream is = null;
		FileOutputStream fos = null;
		try {  
			List<ApiFileObject> apifileList = new ArrayList<ApiFileObject>();
            // 得到所有的文件  
			items = upload.parseRequest(request); 
            Iterator<FileItem> it = items.iterator();
            while (it.hasNext()) {  
                FileItem fItem = (FileItem) it.next();  
                //属性名
                String fName = "";  
                //对象值
                InputStream fValue = null;  
                //普通文本框的值
                if (fItem.isFormField()) {   
                    fName = fItem.getFieldName();  
                    String pvalue = fItem.getString("UTF-8");  
                    rmap.put(fName, pvalue);
                 // 获取上传文件的值  
                } else {
                	ApiFileObject apiFileObj =  new ApiFileObject();
                	//获取对应属性名
                    fName = fItem.getFieldName(); 
                    apiFileObj.setPropname(fName);
                    //获取输入流文件对象
                    fValue = fItem.getInputStream(); 
                    apiFileObj.setFileObject(fValue);
                    //读取对应的文件流存入对象的文件夹
                    String name = fItem.getName();  
                    if(name != null && !("".equals(name))) {
                    	apiFileObj.setFilename(name);
                    	int pointIndex = name.lastIndexOf(".");
                    	//上传的文件后缀名
                        name = name.substring(pointIndex + 1,name.length());
                        //进行判断文件类型，如果文件类型为空，则不判断，不为空则判断其是否一致
                        if(StringUtil.isNotEmpty(rftype) && !rftype.equals(name)){
                        	apiFileObj.setIstypeRight(false);
                        }else{
                        	apiFileObj.setFiletype(name);
                            //构建目录
                            directory = new File(basePath + savePath);
                            if(!directory.exists()){
                            	directory.mkdirs();
                            }
                            //保存文件信息
                            String endFile = rflname.substring(0,rflname.lastIndexOf(".") - 1) 
		                            		+ System.currentTimeMillis() + "." 
		                            		+ rflname.substring(rflname.lastIndexOf(".") + 1,rflname.length());
                            String rsavePathes = basePath + savePath + "/" + endFile;
                            is = fItem.getInputStream();  
                            fos = new FileOutputStream(rsavePathes);  
                            byte[] buffer = new byte[1024];  
                            while (is.read(buffer) > 0) {  
                                fos.write(buffer, 0, buffer.length);  
                            }
                            //返回的绝对路径信息
                            apiFileObj.setAbsolutePath(rsavePathes);
                            //返回相对路径信息
                            apiFileObj.setRelativepath( savePath + "/" + endFile);
                        }
                    }
                  //添加到上传文件集合中
                  apifileList.add(apiFileObj);
                }  
            }  
            //返回相对路径信息
            rmap.put(FILE_UPOBJECT, apifileList);
            rmap.put("status", true);
        	rmap.put("rmsg", "解析上传文件成功");
        } catch (Exception e) {  
        	rmap.put("status", false);
        	rmap.put("rmsg", "解析上传文件出现异常");
        	if(logger.isDebugEnabled()){
        		logger.debug("解析上传文件："+e.getMessage());
			}
        	e.printStackTrace();
        }finally{
        	if(null != is){
        		try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        	if(null != fos){
        		try {
					fos.flush();
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        }
		return rmap;
	}

	/**
	 * Description：<br> 
	 * 保存上传的图片信息
	 * @author  Lin Xu
	 * @date    2016年1月20日
	 * @version v1.0.0
	 * @param bi 图片流
	 * @param basePath 保存的基本路径
	 * @param savePath 保存头像的文件夹
	 * @param rflname 头像文件名
	 * @return
	 */
	public static String saveUploadImage(BufferedImage bi,String basePath,String savePath,String rflname){
		String imageURL = "";
		try {
			if(null != bi && StringUtil.isNotEmpty(basePath) && 
					StringUtil.isNotEmpty(savePath) && StringUtil.isNotEmpty(rflname)){
				//保存绝对路径的图片信息
				String subSavePath = savePath.substring(0, savePath.length() - 1);
				File jfile = new File(basePath + subSavePath);
				if (!jfile.exists()) {
					jfile.mkdirs();
				}
				String imagePath = basePath + savePath + rflname;
				ImageIO.write(bi, "jpg", new File(imagePath));
				imageURL = savePath + rflname;
			}else{
				logger.debug("上传头像参数出现错误");
				imageURL = "";
			}
		}catch(Exception e){
			imageURL = "";
			if (logger.isDebugEnabled()) {
				logger.debug(e.getMessage());
			}
			e.printStackTrace();
		}
		return imageURL;
	}
	
}


