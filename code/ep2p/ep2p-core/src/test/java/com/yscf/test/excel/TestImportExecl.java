/*
 **************************************************************************
 * 版权声明：
 * 本软件为大展信息科技（深圳）有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 测试导入excel文档
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月23日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.test.excel;

import java.io.File;
import java.util.List;
import org.junit.Test;
import com.achievo.framework.constant.ComExcelConstants;
import com.achievo.framework.excel.check.CheckChain;
import com.achievo.framework.excel.check.CheckOutChain;
import com.achievo.framework.excel.check.Checker;
import com.achievo.framework.excel.check.FileImpPropertyChecker;
import com.achievo.framework.excel.check.FileImpXmlChecker;
import com.achievo.framework.excel.check.FileOutPropertyChecker;
import com.achievo.framework.excel.check.FileOutXmlChecker;
import com.achievo.framework.excel.check.FileSuffixChecker;
import com.achievo.framework.excel.check.FileTypeChecker;
import com.achievo.framework.excel.check.OutChecker;
import com.achievo.framework.excel.domain.CheckResult;
import com.achievo.framework.excel.domain.ExportFile;
import com.achievo.framework.excel.domain.ImportFile;
import com.achievo.framework.excel.exporter.ExcelExporter;
import com.achievo.framework.excel.importer.ExcelImporter;
import com.google.common.collect.Lists;

/**
 * Description：<br> 
 * 测试导入excel文档
 * @author  Lin Xu
 * @date    2015年9月23日
 * @version v1.0.0
 */
public class TestImportExecl {
	
	private static String IMPL_EXCEL_2007_PATH = "E:\\LinXuProj\\code\\ep2p\\ep2p-core\\src\\test\\resources\\exceltemp\\StudentIm2007.xlsx";
	private static String IMPL_EXCEL_2003_PATH = "E:\\LinXuProj\\code\\ep2p\\ep2p-core\\src\\test\\resources\\exceltemp\\StudentIm2003.xls";
	private static String XML_TEMP_PATH = "E:\\LinXuProj\\code\\ep2p\\ep2p-core\\src\\test\\resources\\excelconfig\\textexceldoc.xml";
	private List<Student> preslut2007 = null;
	private List<Student> preslut2003 = null;
	private List<Student> jreslut2003 = null;
	
	private static String OMPL_EXCEL_2007_POI_TEMP = "E:\\LinXuText\\tempxls\\omplexcel2007poitemp.xlsx";
	private static String OMPL_EXCEL_2003_POI_TEMP = "E:\\LinXuText\\tempxls\\omplexcel2003poitemp.xls";
	private static String OMPL_EXCEL_2003_JXL_TEMP = "E:\\LinXuText\\tempxls\\omplexcel2003jxltemp.xls";
	private static String OMPL_EXCEL_2007_POI_RESULT = "E:\\LinXuText\\tempxls\\omplexcel2007poiresult.xlsx";
	private static String OMPL_EXCEL_2003_POI_RESULT = "E:\\LinXuText\\tempxls\\omplexcel2003poiresult.xls";
	private static String OMPL_EXCEL_2003_JXL_RESULT = "E:\\LinXuText\\tempxls\\omplexcel2003jxlresult.xls";
	
	
	@Test
	public void test(){
		System.out.println("==========================开  始====================================================" );
		ExcelImporter excelImporter = excelImporterObj();
		System.out.println("==POI 2007 样式导入========");
		importPOIFileExcel2007(excelImporter);
		System.out.println("==POI 2003 样式导入========");
		importPOIFileExcel2003(excelImporter);
		System.out.println("==JXL 2003 样式导入========");
		importJXLFileExcel2003(excelImporter);
		
		System.out.println("====样式导出=============");
		ExcelExporter excelExporter = excelExporterObj();
		System.out.println("===POI 2007模板导出========");
		exportPOITempExcel2007(excelExporter);
		System.out.println("==POI 2003模板导出=========");
		exportPOITempExcel2003(excelExporter);
		System.out.println("==JXL 2003模板导出=========");
		exportJXLTempExcel2003(excelExporter);
		System.out.println("==POI 2007结果导出=========");
		exportPOIReslutExcel2007(excelExporter);
		System.out.println("==POI 2003结果导出=========");
		exportPOIReslutExcel2003(excelExporter);
		System.out.println("==JXL 2003结果导出==========");
		exportJXLReslutExcel2003(excelExporter);
		System.out.println("==========================结  束===================================================" );
	}
	
	/**
	 * Description：<br> 
	 * 导入执行对象
	 * @author  Lin Xu
	 * @date    2015年9月23日
	 * @version v1.0.0
	 * @return
	 */
	private ExcelImporter excelImporterObj() {
		// Excel2007 导入
		ExcelImporter excelImporter = new ExcelImporter();
		// 实例化拦截器
		FileImpPropertyChecker fileImpPropertyChecker = new FileImpPropertyChecker();
		FileTypeChecker fileTypeChecker = new FileTypeChecker();
		FileSuffixChecker fileSuffixChecker = new FileSuffixChecker();
		fileSuffixChecker.setDontAllowSuffix("exe,bin,sh,bat,apk");
		FileImpXmlChecker fileImpXmlChecker = new FileImpXmlChecker();
		// 责任链
		List<Checker> checkers = Lists.newArrayList();
		checkers.add(fileImpPropertyChecker);
		checkers.add(fileTypeChecker);
		checkers.add(fileSuffixChecker);
		checkers.add(fileImpXmlChecker);
		// 校验责任链
		CheckChain checkChain = new CheckChain();
		checkChain.setCheckers(checkers);
		excelImporter.setCheckChain(checkChain);
		return excelImporter;
	}
	
	/**
	 * Description：<br> 
	 * 导出执行对象
	 * @author  Lin Xu
	 * @date    2015年9月23日
	 * @version v1.0.0
	 * @return
	 */
	private ExcelExporter excelExporterObj(){
		ExcelExporter excelExporter = new ExcelExporter();
		//责任链
		FileOutPropertyChecker fopc = new FileOutPropertyChecker();
		FileOutXmlChecker foxc = new FileOutXmlChecker();
		//设置责任链
		List<OutChecker> outcheckers = Lists.newArrayList();
		outcheckers.add(fopc);
		outcheckers.add(foxc);
		//校验责任链
		CheckOutChain checkoutChain = new CheckOutChain();
		checkoutChain.setOutChecker(outcheckers);
		excelExporter.setCheckChain(checkoutChain);
		return excelExporter;
	}
	
	/**
	 * Description：<br> 
	 * POI 2007 样式导入
	 * @author  Lin Xu
	 * @date    2015年9月23日
	 * @version v1.0.0
	 * @param excelImporter
	 */
	@SuppressWarnings("unchecked")
	private void importPOIFileExcel2007(ExcelImporter excelImporter){
		ImportFile imf2007 = new ImportFile();
		imf2007.setFilepath(IMPL_EXCEL_2007_PATH);	
		imf2007.setXmlpath(XML_TEMP_PATH);
		imf2007.setTempid("poistudents2007");
		CheckResult checkR2007	= excelImporter.invoke(imf2007);
		if(checkR2007.isPass()){
			preslut2007 = (List<Student>) checkR2007.getStatusMessage().get(ComExcelConstants.EXCELRESLUT);
			for(Student stu : preslut2007){
				System.out.println(stu.toString());
			}
		}else{
			List<String> errorMsg = checkR2007.getErrorMessage();
			for(String str : errorMsg){
				System.out.println("=====>解析错误信息：" + str);
			}
		}
	}
	
	/**
	 * Description：<br> 
	 * POI 2003 样式导入
	 * @author  Lin Xu
	 * @date    2015年9月23日
	 * @version v1.0.0
	 * @param excelImporter
	 */
	@SuppressWarnings("unchecked")
	private void importPOIFileExcel2003(ExcelImporter excelImporter){
		ImportFile imf2003 = new ImportFile();
		imf2003.setFilepath(IMPL_EXCEL_2003_PATH);	
		imf2003.setXmlpath(XML_TEMP_PATH);
		imf2003.setTempid("poistudents2003");
		CheckResult checkR2003	= excelImporter.invoke(imf2003);
		if(checkR2003.isPass()){
			preslut2003 = (List<Student>) checkR2003.getStatusMessage().get(ComExcelConstants.EXCELRESLUT);
			for(Student stu : preslut2003){
				System.out.println(stu.toString());
			}
		}else{
			List<String> errorMsg = checkR2003.getErrorMessage();
			for(String str : errorMsg){
				System.out.println("=====>解析错误信息：" + str);
			}
		}
	}
	
	/**
	 * Description：<br> 
	 * JXL 2003 样式导入
	 * @author  Lin Xu
	 * @date    2015年9月23日
	 * @version v1.0.0
	 * @param excelImporter
	 */
	@SuppressWarnings("unchecked")
	private void importJXLFileExcel2003(ExcelImporter excelImporter){
		ImportFile jxlimf2003 = new ImportFile();
		jxlimf2003.setFilepath(IMPL_EXCEL_2003_PATH);	
		jxlimf2003.setXmlpath(XML_TEMP_PATH);
		jxlimf2003.setTempid("jxlstudents2003");
		CheckResult jxlcheckR2003	= excelImporter.invoke(jxlimf2003);
		if(jxlcheckR2003.isPass()){
			jreslut2003 = (List<Student>) jxlcheckR2003.getStatusMessage().get(ComExcelConstants.EXCELRESLUT);
			for(Student stu : jreslut2003){
				System.out.println(stu.toString());
			}
		}else{
			List<String> errorMsg = jxlcheckR2003.getErrorMessage();
			for(String str : errorMsg){
				System.out.println("=====>解析错误信息：" + str);
			}
		}
	}
	
	/**
	 * Description：<br> 
	 * POI 2007模板导出
	 * @author  Lin Xu
	 * @date    2015年9月23日
	 * @version v1.0.0
	 * @param excelExporter
	 */
	private void exportPOITempExcel2007(ExcelExporter excelExporter ){
		ExportFile poiexceltemp2007 = new ExportFile();
		poiexceltemp2007.setFilepath(OMPL_EXCEL_2007_POI_TEMP);
		poiexceltemp2007.setOutlist(null);
		poiexceltemp2007.setXmlpath(XML_TEMP_PATH);
		poiexceltemp2007.setTempid("poiexceltemp2007");
		CheckResult poiexceltempR2007	= excelExporter.invoke(poiexceltemp2007);
		if(poiexceltempR2007.isPass()){
			File file =  (File) poiexceltempR2007.getStatusMessage().get(ComExcelConstants.EXCELRESLUT);
			System.out.println("POI 2007 导出的文件为名：" + file.getName());
		}else{
			List<String> errorMsg = poiexceltempR2007.getErrorMessage();
			for(String str : errorMsg){
				System.out.println("=====>解析错误信息：" + str);
			}
		}
	}
	
	/**
	 * Description：<br> 
	 * POI 2003模板导出
	 * @author  Lin Xu
	 * @date    2015年9月23日
	 * @version v1.0.0
	 * @param excelExporter
	 */
	private void exportPOITempExcel2003(ExcelExporter excelExporter ){
		ExportFile poiexceltemp2003 = new ExportFile();
		poiexceltemp2003.setFilepath(OMPL_EXCEL_2003_POI_TEMP);
		poiexceltemp2003.setOutlist(null);
		poiexceltemp2003.setXmlpath(XML_TEMP_PATH);
		poiexceltemp2003.setTempid("poiexceltemp2003");
		CheckResult poiexceltemp2003R	= excelExporter.invoke(poiexceltemp2003);
		if(poiexceltemp2003R.isPass()){
			File file =  (File) poiexceltemp2003R.getStatusMessage().get(ComExcelConstants.EXCELRESLUT);
			System.out.println("POI 2003 导出的文件为名：" + file.getName());
		}else{
			List<String> errorMsg = poiexceltemp2003R.getErrorMessage();
			for(String str : errorMsg){
				System.out.println("=====>解析错误信息：" + str);
			}
		}
	}
	
	/**
	 * Description：<br> 
	 * JXL 2003模板导出
	 * @author  Lin Xu
	 * @date    2015年9月23日
	 * @version v1.0.0
	 * @param excelExporter
	 */
	private void exportJXLTempExcel2003(ExcelExporter excelExporter ){
		ExportFile jxlexceltemp2003 = new ExportFile();
		jxlexceltemp2003.setFilepath(OMPL_EXCEL_2003_JXL_TEMP);
		jxlexceltemp2003.setOutlist(null);
		jxlexceltemp2003.setXmlpath(XML_TEMP_PATH);
		jxlexceltemp2003.setTempid("jxlexceltemp2003");
		CheckResult jxlexceltemp2003R	= excelExporter.invoke(jxlexceltemp2003);
		if(jxlexceltemp2003R.isPass()){
			File file =  (File) jxlexceltemp2003R.getStatusMessage().get(ComExcelConstants.EXCELRESLUT);
			System.out.println("POI 2003 导出的文件为名：" + file.getName());
		}else{
			List<String> errorMsg = jxlexceltemp2003R.getErrorMessage();
			for(String str : errorMsg){
				System.out.println("=====>解析错误信息：" + str);
			}
		}
	}

	/**
	 * Description：<br> 
	 * POI 2007结果导出
	 * @author  Lin Xu
	 * @date    2015年9月23日
	 * @version v1.0.0
	 * @param excelExporter
	 */
	private void exportPOIReslutExcel2007(ExcelExporter excelExporter ){
		ExportFile poiexcelreslut2007 = new ExportFile();
		poiexcelreslut2007.setFilepath(OMPL_EXCEL_2007_POI_RESULT);
		poiexcelreslut2007.setOutlist(preslut2007);
		poiexcelreslut2007.setXmlpath(XML_TEMP_PATH);
		poiexcelreslut2007.setTempid("poiexcelreslut2007");
		CheckResult poiexcelreslut2007R	= excelExporter.invoke(poiexcelreslut2007);
		if(poiexcelreslut2007R.isPass()){
			File file =  (File) poiexcelreslut2007R.getStatusMessage().get(ComExcelConstants.EXCELRESLUT);
			System.out.println("POI 2007 导出的结果文件为名：" + file.getName());
		}else{
			List<String> errorMsg = poiexcelreslut2007R.getErrorMessage();
			for(String str : errorMsg){
				System.out.println("=====>解析错误信息：" + str);
			}
		}
	}
	
	/**
	 * Description：<br> 
	 * POI 2003模板导出
	 * @author  Lin Xu
	 * @date    2015年9月23日
	 * @version v1.0.0
	 * @param excelExporter
	 */
	private void exportPOIReslutExcel2003(ExcelExporter excelExporter ){
		ExportFile poiexcelreslut2003 = new ExportFile();
		poiexcelreslut2003.setFilepath(OMPL_EXCEL_2003_POI_RESULT);
		poiexcelreslut2003.setOutlist(preslut2003);
		poiexcelreslut2003.setXmlpath(XML_TEMP_PATH);
		poiexcelreslut2003.setTempid("poiexcelreslut2003");
		CheckResult poiexcelreslut2003R	= excelExporter.invoke(poiexcelreslut2003);
		if(poiexcelreslut2003R.isPass()){
			File file =  (File) poiexcelreslut2003R.getStatusMessage().get(ComExcelConstants.EXCELRESLUT);
			System.out.println("POI 2007 导出的结果文件为名：" + file.getName());
		}else{
			List<String> errorMsg = poiexcelreslut2003R.getErrorMessage();
			for(String str : errorMsg){
				System.out.println("=====>解析错误信息：" + str);
			}
		}
	}
	
	/**
	 * Description：<br> 
	 * JXL 2003模板导出
	 * @author  Lin Xu
	 * @date    2015年9月23日
	 * @version v1.0.0
	 * @param excelExporter
	 */
	private void exportJXLReslutExcel2003(ExcelExporter excelExporter ){
		ExportFile jxlexcelreslut2003 = new ExportFile();
		jxlexcelreslut2003.setFilepath(OMPL_EXCEL_2003_JXL_RESULT);
		jxlexcelreslut2003.setOutlist(jreslut2003);
		jxlexcelreslut2003.setXmlpath(XML_TEMP_PATH);
		jxlexcelreslut2003.setTempid("jxlexcelreslut2003");
		CheckResult jxlexcelreslut2003R	= excelExporter.invoke(jxlexcelreslut2003);
		if(jxlexcelreslut2003R.isPass()){
			File file =  (File) jxlexcelreslut2003R.getStatusMessage().get(ComExcelConstants.EXCELRESLUT);
			System.out.println("JXL 2003 导出的结果文件为名：" + file.getName());
		}else{
			List<String> errorMsg = jxlexcelreslut2003R.getErrorMessage();
			for(String str : errorMsg){
				System.out.println("=====>解析错误信息：" + str);
			}
		}
	}
}


