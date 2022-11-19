/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 将Office文档转换为PDF. 运行该函数需要用到OpenOffice, OpenOffice下载地址为 
 * http://www.openoffice.org/
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年11月21日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.util.Properties;

import org.springframework.util.StringUtils;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

/**
 * Description：<br> 
 * 将Office文档转换为PDF. 运行该函数需要用到OpenOffice, OpenOffice下载地址为 
 * http://www.openoffice.org/ 
 * 说明：在实用该类的方法的时候一定要在本机安装OpenOffice软件，然后进行配置才可以正常使用工具类
 * @author  Lin Xu
 * @date    2015年11月21日
 * @version v1.0.0
 */
public class OpenOfficePdfUtil {
	//静态变量
	private static final String OPENOFFICE_CONFILE_NAME = "ep2p-openoffice.properties";
	private static final String OS_NAME = "os.name";
	private static final String WINDOW_INSTALLATION_DIRECTORY = "window.installation.directory";
	private static final String LINUX_INSTALLATION_DIRECTORY = "linux.installation.directory";
	private static final String WINDOW_SERVICE_PATH = "window.service.path";
	private static final String LINUX_SERVICE_PATH = "linux.service.path";
	private static final String SERVICE_IP = "service.ip";
	
	/**
	 * Description：<br> 
	 * 读取配置文件中信息
	 * @author  Lin Xu
	 * @date    2015年11月21日
	 * @version v1.0.0
	 * @param key
	 * @return
	 */
	private static String readConfigKeyVal(String key){
		String restr  = "";
		InputStream is =  OpenOfficePdfUtil.class.getClassLoader().getResourceAsStream(OPENOFFICE_CONFILE_NAME);
		Properties properties = new Properties();
		try {
			//加载配置的资源文件
			properties.load(is);
			//读取传入key值的内容信息
			if(null != key && !"".equals(key)){
				restr = properties.getProperty(key);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return restr;
	}

	/**
	 * Description：<br> 
	 * 将Office文档转换为PDF. 运行该函数需要用到OpenOffice, OpenOffice下载地址为
	 * <pre> 
     * 方法示例: 
     * String sourcePath = "F:\\office\\source.doc"; 
     * String destFile = "F:\\pdf\\dest.pdf"; 
     * Converter.office2PDF(sourcePath, destFile); 
     * </pre>
	 * @author  Lin Xu
	 * @date    2015年11月21日
	 * @version v1.0.0
	 * @param sourceFile 源文件, 绝对路径. 可以是Office2003-2007全部格式的文档, Office2010的没测试. 包括.doc, 
     *            .docx, .xls, .xlsx, .ppt, .pptx等. 示例: F:\\office\\source.doc 
	 * @param destFile 目标文件. 绝对路径. 示例: F:\\pdf\\dest.pdf
	 * @return 1 成功  、 其他失败
	 */
	public static int office2PDF(String sourceFile, String destFile) {
		//定义初始化参数
		OpenOfficeConnection connection = null;
		Process pro = null;
		try {  
			// 找不到源文件, 则返回-1 
            File inputFile = new File(sourceFile);  
            if (!inputFile.exists()) {  
                return -1; 
            }  
  
            // 如果目标路径不存在, 则新建该路径  
            File outputFile = new File(destFile);  
            if (!outputFile.getParentFile().exists()) {  
                outputFile.getParentFile().mkdirs();  
            }  
            //获取系统名字
            String sysname = readConfigKeyVal(OS_NAME);
            
            //这里是OpenOffice的安装目录, 在我的项目中,为了便于拓展接口,没有直接写成这个样子,但是这样是绝对没问题的  
            String OpenOffice_HOME = readConfigKeyVal(LINUX_INSTALLATION_DIRECTORY);
            if("Windows".equals(sysname)){
            	OpenOffice_HOME = readConfigKeyVal(WINDOW_INSTALLATION_DIRECTORY);
            }
            if("Linux".equals(sysname)){
            	OpenOffice_HOME = readConfigKeyVal(LINUX_INSTALLATION_DIRECTORY);
            }
            
            // 如果从文件中读取的URL地址最后一个字符不是 '\'，则添加'\'  
            if (StringUtils.hasLength(OpenOffice_HOME) && OpenOffice_HOME.charAt(OpenOffice_HOME.length() - 1) != '\\') {  
                OpenOffice_HOME += "\\";  
            }  
            
            // 启动OpenOffice的服务  
            String command = OpenOffice_HOME + readConfigKeyVal(LINUX_SERVICE_PATH);  
            if("Windows".equals(sysname)){
            	command = OpenOffice_HOME +  readConfigKeyVal(WINDOW_SERVICE_PATH);
            }
            if("Linux".equals(sysname)){
            	command = OpenOffice_HOME +  readConfigKeyVal(LINUX_SERVICE_PATH);
            }
            pro = Runtime.getRuntime().exec(command);  
            
            // connect to an OpenOffice.org instance running on port 8100  
            connection = new SocketOpenOfficeConnection(readConfigKeyVal(SERVICE_IP), 8100);  
            connection.connect();  
  
            // convert  
            DocumentConverter converter = new OpenOfficeDocumentConverter(connection);  
            converter.convert(inputFile, outputFile);  
    
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
            return -1;  
        } catch (ConnectException e) {  
            e.printStackTrace();  
            return -1;  
        } catch (IOException e) {  
            e.printStackTrace();  
            return -1;  
        } catch (Exception e) {
        	   e.printStackTrace();  
               return -1;  
               //不管指没有执行都需要关闭服务链接
		} finally{
        	if(null != connection){
        		// close the connection  
                connection.disconnect(); 
        	}
        	if(null != pro){
        		 // 关闭OpenOffice服务的进程  
                pro.destroy(); 
        	}
        }
        return 1;  
	}
	
	/**
	 * Description：<br> 
	 * 返回执行过的文件
	 * @author  Lin Xu
	 * @date    2015年11月21日
	 * @version v1.0.0
	 * @param sourceFile 资源文件路径
	 * @param destFile 生成文件路径
	 * @return
	 */
	public static File office2PDFs(String sourceFile, String destFile){
		File rfile = new File("");
		if(null != destFile && !"".equals(destFile)){
			int ri = office2PDF(sourceFile, destFile);
			if(ri == 1){
				rfile = new File(destFile);
			}
		}
		return rfile;
	}
	
	
	/*public static void main(String[] args) {
		String soruceFile = "E:/大展项目组入场指引.ppt";
		String mideFile = "E:/Test.pdf";
		System.out.println("转换产生的值信息：" + OpenOfficePdfUtil.office2PDF(soruceFile, mideFile));
		System.out.println("转换产生的文件信息：" + OpenOfficePdfUtil.office2PDFs(soruceFile, mideFile).getPath());
		//System.out.println(OpenOfficePdfUtil.readConfigKeyVal("window.installation.directory"));
	}*/
	
}


