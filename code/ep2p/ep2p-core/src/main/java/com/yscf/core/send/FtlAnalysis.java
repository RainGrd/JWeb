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
 * 2015年12月19日     JingYu.Dai		Initial Version.
 *************************************************************************
 */
package com.yscf.core.send;

import java.io.File;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

/**
 * Description：<br> 
 * 文件模板解析
 * @author  JingYu.Dai
 * @date    2015年12月19日
 * @version v1.0.0
 */
public class FtlAnalysis {
	
	private Logger logger = LoggerFactory.getLogger(FtlAnalysis.class);

	/**
	 * Description：<br> 
	 * 解析模版
	 * @author  JingYu.Dai
	 * @date    2015年12月19日
	 * @version v1.0.0
	 * @param map 需要替换的值
	 * @param tempPath 模版名称加后缀 例如：eamil.ftl
	 * @return String 模板字符串
	 */
	public String templateParsing(Map<String, Object> map, String tempPath) {
		/* 创建一configuration */
		Configuration cfg = new Configuration();
		try {
			// 这里我设置模版的根目录
			cfg.setDirectoryForTemplateLoading(new File(getClass().getResource("/ftl").getPath()));
			cfg.setObjectWrapper(new DefaultObjectWrapper());
			/* 而以下代码你通常会在一个应用生命周期中执行多次 */
			/* 获取或创建一个模版 */
			Template temp = cfg.getTemplate(tempPath);
			/* 合并数据模型和模版 */
			Writer out = new StringWriter(2048);
			temp.process(map, out);
			String str = out.toString();
			out.close();
			return str;
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info(e.getMessage());
			}
		}
		return "邮件类容内容未加载到。请联系、系统管理员！";
	}
}


