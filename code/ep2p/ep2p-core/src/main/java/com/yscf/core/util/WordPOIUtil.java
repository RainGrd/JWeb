/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 操作Doc中的文字信息
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年10月12日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.util;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import com.sun.star.uno.RuntimeException;

/**
 * Description：<br>
 * 操作Doc中的文字信息
 * 
 * @author Lin Xu
 * @date 2015年10月12日
 * @version v1.0.0
 */
public class WordPOIUtil {

	// 返回Docx中需要替换的特殊字符，没有重复项
	// 推荐传入正则表达式参数"\\$\\{[^{}]+\\}"
	public static ArrayList<String> getReplaceElementsInWord(String filePath,
			String regex) {
		String[] p = filePath.split("\\.");
		if (p.length > 0) {// 判断文件有无扩展名
			// 比较文件扩展名
			if (p[p.length - 1].equalsIgnoreCase("doc")) {
				ArrayList<String> al = new ArrayList<String>();
				File file = new File(filePath);
				HWPFDocument document = null;
				try {
					InputStream is = new FileInputStream(file);
					document = new HWPFDocument(is);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				Range range = document.getRange();
				String rangeText = range.text();
				CharSequence cs = rangeText.subSequence(0, rangeText.length());
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(cs);
				int startPosition = 0;
				while (matcher.find(startPosition)) {
					if (!al.contains(matcher.group())) {
						al.add(matcher.group());
					}
					startPosition = matcher.end();
				}
				return al;
			} else if (p[p.length - 1].equalsIgnoreCase("docx")) {
				ArrayList<String> al = new ArrayList<String>();
				XWPFDocument document = null;
				try {
					document = new XWPFDocument(
							POIXMLDocument.openPackage(filePath));
				} catch (IOException e) {
					e.printStackTrace();
				}
				// 遍历段落
				Iterator<XWPFParagraph> itPara = document
						.getParagraphsIterator();
				while (itPara.hasNext()) {
					XWPFParagraph paragraph = (XWPFParagraph) itPara.next();
					String paragraphString = paragraph.getText();
					CharSequence cs = paragraphString.subSequence(0,
							paragraphString.length());
					Pattern pattern = Pattern.compile(regex);
					Matcher matcher = pattern.matcher(cs);
					int startPosition = 0;
					while (matcher.find(startPosition)) {
						if (!al.contains(matcher.group())) {
							al.add(matcher.group());
						}
						startPosition = matcher.end();
					}
				}
				// 遍历表
				Iterator<XWPFTable> itTable = document.getTablesIterator();
				while (itTable.hasNext()) {
					XWPFTable table = (XWPFTable) itTable.next();
					int rcount = table.getNumberOfRows();
					for (int i = 0; i < rcount; i++) {
						XWPFTableRow row = table.getRow(i);
						List<XWPFTableCell> cells = row.getTableCells();
						for (XWPFTableCell cell : cells) {
							String cellText = "";
							cellText = cell.getText();
							CharSequence cs = cellText.subSequence(0,
									cellText.length());
							Pattern pattern = Pattern.compile(regex);
							Matcher matcher = pattern.matcher(cs);
							int startPosition = 0;
							while (matcher.find(startPosition)) {
								if (!al.contains(matcher.group())) {
									al.add(matcher.group());
								}
								startPosition = matcher.end();
							}
						}
					}
				}
				return al;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * Description：<br>
	 * 替换word需要替换的字符的信息数据
	 * 
	 * @author Lin Xu
	 * @date 2015年10月12日
	 * @version v1.0.0
	 * @param srcPath
	 * @param destPath
	 * @param map
	 * @return
	 */
	public static boolean replaceAndGenerateWord(String srcPath,
			String destPath, Map<String, String> map,Map<String, List<Object>> tables) {
		
		String[] sp = srcPath.split("\\.");
		String[] dp = destPath.split("\\.");
		// 判断文件有无扩展名
		if ((sp.length > 0) && (dp.length > 0)) {
			// 比较文件扩展名
			if (sp[sp.length - 1].equalsIgnoreCase("docx")) {
				try {
					XWPFDocument document = new XWPFDocument(POIXMLDocument.openPackage(srcPath));
					List<XWPFTable> tbs = document.getTables();
					if(tbs!=null && tables!=null){
						for (Entry<String,List<Object>> entry:tables.entrySet()) {
							String key = entry.getKey();
							List<Object> values = entry.getValue();
							
							Integer k = Integer.parseInt(key);
							// 根据map穿的key位置，获取到模板对应的表格
							XWPFTable xt = tbs.get(k);
							//获取到现在表格行数大小
							int s = xt.getRows().size();
							// 判断是否行数大于1，第一行为表头，第二行为表格的替换符
							if(s>1){
								// 获取到替换符的行
								XWPFTableRow xtr = xt.getRow(s-1);
								List<XWPFTableCell> xtcs = xtr.getTableCells();
								Map<Integer,String> m = new HashMap<Integer, String>(); 
								// 将替换符的位置通过Map方式记录下来
								for(int z=0;z<xtcs.size();z++){
									m.put(z,xtcs.get(z).getText());
								}
								// 循环数据源
								for(int j = 0;j<values.size();j++){
									int rowIndex = xt.getRows().size();
									// 再列表最后，新增一行
									xt.insertNewTableRow(rowIndex);
									// 通过反射方式，获取数据源，以map方式存入，key为替换符，value为属性值
									Object o = values.get(j);
									Map<String,String> maps = getMapsByObejct(o);
									// 新建对应的单元格
									for(int i=0;i<m.size();i++)
									{	// 按替换符的位置，取得替换符，再通过数据源maps取得属性值
										xt.getRow(rowIndex).createCell().setText(maps.get(m.get(i)));
									}	
								}
								// 最后，移除掉模板替换符，那一行
								xt.removeRow(s-1);
							}
						}
							
					}
					
					
					 // 替换段落中的指定文字  
		            List<XWPFParagraph> paragraphs = document.getParagraphs();  
		            Set<String> keys  = map.keySet();
		            for (XWPFParagraph tmp : paragraphs) {  
		                List<XWPFRun> runs = tmp.getRuns();  
		                for (XWPFRun aa : runs) {  
		                    String s = aa.getText(aa.getTextPosition());
		                    for(String k : keys){
		                    	s = s.replace(k, map.get(k));
		                    }
		                    aa.setText(s,0);
		                }  
		            }  
		  
					// 替换表格中的指定文字
					Iterator<XWPFTable> itTable = document.getTablesIterator();
										while (itTable.hasNext()) {
						XWPFTable table = (XWPFTable) itTable.next();
					
						int rcount = table.getNumberOfRows();
						for (int i = 0; i < rcount; i++) {
							XWPFTableRow row = table.getRow(i);
							List<XWPFTableCell> cells = row.getTableCells();
							for (XWPFTableCell cell : cells) {
								String cellTextString = cell.getText();
								boolean f = false;
								for (Entry<String, String> e : map.entrySet()) {
									if (cellTextString.contains(e.getKey())){
										cellTextString = cellTextString
										.replace(e.getKey(),
												e.getValue());
										f = true;
									}
								}
								if(f){
									cell.removeParagraph(0);
									cell.setText(cellTextString);
								}
							}
						}
					}
					FileOutputStream outStream = null;
					File file = new File(destPath);
					if(!file.getParentFile().exists()){
						file.getParentFile().mkdirs();
					}
					file.createNewFile();
					outStream = new FileOutputStream(file);
					document.write(outStream);
					outStream.close();
					return true;
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
		}

		return false;
	}
	/**
	 * Description：<br>
	 * 替换word需要替换的字符的信息数据
	 * 
	 * @author Lin Xu
	 * @date 2015年10月12日
	 * @version v1.0.0
	 * @param srcPath
	 * @param destPath
	 * @param map
	 * @return
	 */
	public static boolean replaceAndGenerateWord(String srcPath,
			String destPath, Map<String, String> map) {
		String[] sp = srcPath.split("\\.");
		String[] dp = destPath.split("\\.");
		// 判断文件有无扩展名
		if ((sp.length > 0) && (dp.length > 0)) {
			// 比较文件扩展名
			if (sp[sp.length - 1].equalsIgnoreCase("docx")) {
				try {
					XWPFDocument document = new XWPFDocument(
							POIXMLDocument.openPackage(srcPath));
					// 替换段落中的指定文字
					Iterator<XWPFParagraph> itPara = document
							.getParagraphsIterator();
					while (itPara.hasNext()) {
						XWPFParagraph paragraph = (XWPFParagraph) itPara.next();
						List<XWPFRun> runs = paragraph.getRuns();
						for (int i = 0; i < runs.size(); i++) {
							String oneparaString = runs.get(i).getText(
									runs.get(i).getTextPosition());
							for (Map.Entry<String, String> entry : map
									.entrySet()) {
								oneparaString = oneparaString.replace(
										entry.getKey(), entry.getValue());
							}
							runs.get(i).setText(oneparaString, 0);
						}
					}
					
					// 替换表格中的指定文字
					Iterator<XWPFTable> itTable = document.getTablesIterator();
					while (itTable.hasNext()) {
						XWPFTable table = (XWPFTable) itTable.next();
						int rcount = table.getNumberOfRows();
						for (int i = 0; i < rcount; i++) {
							XWPFTableRow row = table.getRow(i);
							List<XWPFTableCell> cells = row.getTableCells();
							for (XWPFTableCell cell : cells) {
								String cellTextString = cell.getText();
								for (Entry<String, String> e : map.entrySet()) {
									if (cellTextString.contains(e.getKey()))
										cellTextString = cellTextString
										.replace(e.getKey(),
												e.getValue());
								}
								cell.removeParagraph(0);
								cell.setText(cellTextString);
							}
						}
					}
					FileOutputStream outStream = null;
					outStream = new FileOutputStream(destPath);
					document.write(outStream);
					outStream.close();
					return true;
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
				
			} else
				// doc只能生成doc，如果生成docx会出错
				if ((sp[sp.length - 1].equalsIgnoreCase("doc"))
						&& (dp[dp.length - 1].equalsIgnoreCase("doc"))) {
					HWPFDocument document = null;
					try {
						document = new HWPFDocument(new FileInputStream(srcPath));
						Range range = document.getRange();
						for (Map.Entry<String, String> entry : map.entrySet()) {
							range.replaceText(entry.getKey(), entry.getValue());
						}
						FileOutputStream outStream = null;
						outStream = new FileOutputStream(destPath);
						document.write(outStream);
						outStream.close();
						return true;
					} catch (FileNotFoundException e) {
						e.printStackTrace();
						return false;
					} catch (IOException e) {
						e.printStackTrace();
						return false;
					}
				} else {
					return false;
				}
		} else {
			return false;
		}
	}

	/**
	 * 
	 * Description：<br> 
	 * 通过反射获取替换的map集合
	 * @author  JunJie.Liu
	 * @date    2016年2月19日
	 * @version v1.0.0
	 * @param source
	 * @param srcPath
	 * @param destPath
	 * @param tables
	 * @return
	 */
	public static boolean replaceAllString(Object source,
			String srcPath, String destPath, Map<String, List<Object>> tables) {
		if (source == null) {
			throw new RuntimeException("Class is null");
		}

		Map<String, String> map = getMapsByObejct(source);

		return replaceAndGenerateWord(srcPath, destPath, map,tables);
	}
	
	/**
	 * 
	 * Description：<br> 
	 * doc替换成pdf
	 * @author  JunJie.Liu
	 * @date    2016年2月19日
	 * @version v1.0.0
	 * @param docUrl
	 * 			模板doc的路径 
	 * @param tempDocUrl
	 * 			字符替换后产生的路径
	 * @param pdfUrl
	 * 			pdf产生的路径
	 * @param obj
	 * 			替换的数据源
	 * @param tables
	 * 			需要替换的表格，以键值对形式存在 
	 * @return
	 */
	public static File doc2pdf(String docUrl,String tempDocUrl,String pdfUrl,Object obj,Map<String, List<Object>> tables){
		File f = null;
		if (replaceAllString(obj, docUrl,
				tempDocUrl, tables)) { 
			try{
			// 替换成功，将替换的文件转成pdf后，进行删除
				f = OpenOfficePdfUtil.office2PDFs(tempDocUrl, pdfUrl);
			}finally{
				// 转换完，则删除临时 doc 文件
				File file = new File(tempDocUrl);
				if(file.exists() && file.isFile()){
					file.delete();
				}
			}
		}
		return f;
	}
	/**
	 * 
	 * Description：<br> 
	 * doc替换成pdf
	 * @author  JunJie.Liu
	 * @date    2016年2月19日
	 * @version v1.0.0
	 * @param docUrl
	 * 			模板doc的路径 
	 * @param tempDocUrl
	 * 			字符替换后产生的路径
	 * @param pdfUrl
	 * 			pdf产生的路径
	 * @param map
	 * 			替换的数据源
	 * @return
	 */
	public static File doc2pdf(String docUrl,String tempDocUrl,String pdfUrl,Map<String,String> map){
		File f = null;
		if (replaceAndGenerateWord(docUrl, tempDocUrl, map,null)) { 
			try{
				// 替换成功，将替换的文件转成pdf后，进行删除
				f = OpenOfficePdfUtil.office2PDFs(tempDocUrl, pdfUrl);
			}finally{
				// 转换完，则删除临时 doc 文件
				File file = new File(tempDocUrl);
				if(file.exists() && file.isFile()){
					file.delete();
				}
			}
		}
		return f;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 将一个对象通过反射，将"@变量名@"作为key,value=属性值
	 * @author  JunJie.Liu
	 * @date    2016年3月3日
	 * @version v1.0.0
	 * @param o
	 * @return
	 */
	public static Map<String, String> getMapsByObejct(Object o){
		Map<String, String> map = new HashMap<String, String>();

		try {
			Field[] fields = o.getClass().getDeclaredFields();

			for (int i = 0; i < fields.length; i++) {
				Field f = fields[i];
				PropertyDescriptor pd = new PropertyDescriptor(f.getName(),
						o.getClass());
				Method getMethod = pd.getReadMethod();// 获得get方法
				Object b = getMethod.invoke(o);// 执行get方法返回一个Object
				map.put("@" + f.getName() + "@", b != null ? b.toString()
						: "");

			}
		} catch (Exception e) {
			// 无效
			e.printStackTrace();
		}
		return map;
	}
	
	
}
