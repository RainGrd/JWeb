/*
 ********************************************************************************
 * 版权声明：
 * 本软件为深圳高速工程顾问有限公司开发研制。未经本公司正式书面同意，其它任何
 * 个人、团体不得使用、复制、修改和发布本软件。
 ********************************************************************************
 * 程序描述：
 * TODO
 *
 ********************************************************************************
 * 修改历史：
 * Date：                           by：                                       Reason：
 * 2015年6月5日                          Lin Xu                                    Initial Version
 * 
 ********************************************************************************
 */
package com.yscf.common.util;

import java.util.HashMap;

/**
 * Description: <br>
 * 提示语汇总信息
 * @author  Lin Xu
 * @date 	 2015年6月5日
 * @version v1.0.0
 * @since 	 1.6
 */
public class PromptMsgConstants {
	
	/**  公共信息状态或提示   **/
	public static final String COMMON_MSG = "message";
	public static final String COMMON_FLG = "success";
	public static final boolean COMMON_SUCCESS_FLG = true;
	public static final boolean COMMON_FAIL_FLG = false;
	
	
	/**  基础数据图片提示语  **/
	public static final String FILEUPLOAD_COMMON_BPATHERRO = "系统没有配置保存文件的路径信息";
	public static final String FILEUPLOAD_COMMON_FILETYPE = "上传文件类型错误";
	public static final String FILEUPLOAD_COMMON_UPFAIL = "上传文件失败";
	public static final String FILEUPLOAD_COMMON_UPSUCCESS = "上传文件成功";
	public static final String FILEUPLOAD_COMMON_NOFFID = "没有接受到父级id";
	public static final String IMAGEINFO_COMMON_NOFFID = "请求参数错误";
	public static final String IMAGEINFO_COMMON_DELSUC = "图片删除成功";
	public static final String IMAGEINFO_COMMON_DELFAIL = "图片删除失败";
	public static final String FILEUPLOAD_TEMP_ERROR = "解析上传文件错误或没有数据，请检查导入文件！";
	
	
	/*********************  华丽的分割线  *****************************/
	/**
	 * Description: <br>
	 * 设置成功返回的信息
	 * @author  Lin Xu
	 * @date 	 2015年6月6日
	 * @version v1.0.0
	 * @param remap
	 * @param msg
	 */
	public static void addReMsgSuccess(HashMap<String, Object> remap,String msg){
		remap.put(COMMON_FLG, COMMON_SUCCESS_FLG);
		remap.put(COMMON_MSG, msg);
	}
	
	/**
	 * Description: <br>
	 * 设置失败的返回信息
	 * @author  Lin Xu
	 * @date 	 2015年6月6日
	 * @version v1.0.0
	 * @param remap
	 * @param msg
	 */
	public static void addReMsgFail(HashMap<String, Object> remap,String msg){
		remap.put(COMMON_FLG, COMMON_FAIL_FLG);
		remap.put(COMMON_MSG, msg);
	}
	
	/**
	 * Description: <br>
	 * 根据传入参数进行赋值
	 * @author  Lin Xu
	 * @date 	 2015年6月6日
	 * @version v1.0.0
	 * @param remap
	 * @param bool
	 * @param msg
	 */
	public static void addReMsgAll(HashMap<String, Object> remap,boolean bool,String msg){
		if(bool){
			remap.put(COMMON_FLG, COMMON_SUCCESS_FLG);
		}else{
			remap.put(COMMON_FLG, COMMON_FAIL_FLG);
		}
		remap.put(COMMON_MSG, msg);
	}
}
