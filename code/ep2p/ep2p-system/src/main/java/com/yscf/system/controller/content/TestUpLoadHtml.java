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
 * 2016年3月3日     shiliang.feng		Initial Version.
 *************************************************************************
 */
package com.yscf.system.controller.content;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.yscf.common.Constant.Constant;

/**
 * Description：<br>
 * TODO
 * 
 * @author shiliang.feng
 * @date 2016年3月3日
 * @version v1.0.0
 */
public class TestUpLoadHtml {

	public static void main(String[] args) {

		// 系统配置文件
		Date curDate = new Date();
		String dateStr = new SimpleDateFormat("yyyy/MM/dd").format(curDate);

		// 设置文件名
		String lujing = "d:/escf-file/upload/" + Constant.CONTENT + "/"
				+ dateStr + "/" + UUID.randomUUID() + ".html";

		File file = new File(lujing);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("ssss");
			System.out.println(file.length());
			bw.flush();
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
