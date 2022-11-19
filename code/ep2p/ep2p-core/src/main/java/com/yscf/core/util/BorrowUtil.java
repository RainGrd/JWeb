/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 借款工具类
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月28日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.core.util;

import java.util.List;

import com.achievo.framework.context.SpringApplicationContext;
import com.achievo.framework.util.DateUtil;
import com.yscf.core.model.system.SysDictionaryContent;
import com.yscf.core.service.system.ISysDictionaryContentService;

/**
 * Description：<br> 
 * 借款工具类
 * @author  Yu.Zhang
 * @date    2015年9月28日
 * @version v1.0.0
 */
public class BorrowUtil {
	
	/**
	 * 借款编号头
	 */
	private static final String HEAD = "JK";
	
	private static final String NUMBER_HEAD_1="0";
	
	private static final String NUMBER_HEAD_2="00";
	
	private static final String NUMBER_HEAD_3="000";
	
	private static final String CONNECTOR = "-";
	
	/**
	 * 
	 * Description：<br> 
	 * 生成借款申请编号
	 * <br>
	 * 借款借款编号生成规则:JK-150826-0001
	 * 
	 * @author  Yu.Zhang
	 * @date    2015年9月26日
	 * @version v1.0.0
	 * @return
	 * @throws Exception 
	 */
	public synchronized static String generateBorrowNumber() throws Exception{
		ISysDictionaryContentService sysDictionaryContentService = (ISysDictionaryContentService) SpringApplicationContext.getBean("sysDictionaryContentService");
		// 获取数据字典中的值
		List<SysDictionaryContent> list = sysDictionaryContentService.selectByDisctCode("BORROW_NUMBER");
		if(null == list || list.size() == 0){
			throw new Exception("generate borrow bumber fail , dictionary content is null . ");
		}
		
		if(null == list || list.size() < 2){
			throw new Exception("generate borrow bumber fail , dictionary content not all . ");
		}
		
		String today = DateUtil.format(DateUtil.getSystemDate(),"yyMMdd");
		Integer number = 1;
		SysDictionaryContent content = null;
		if(list.get(0).getDictContName().equals(today)){
			 number = Integer.parseInt(list.get(1).getDictContName());
			 number+=1;
			 // 将变动后的 编号保存到数据库中
			 content = new SysDictionaryContent();
			 content.setPid(list.get(1).getPid());
			 content.setDictContName(number.toString());
			 sysDictionaryContentService.updateByPrimaryKeySelective(content);
		}else{
			// 更新数据字典日期为当天日期
			 content = new SysDictionaryContent();
			 content.setPid(list.get(0).getPid());
			 content.setDictContName(today);
			 // 更新数据字典 编号为 0 ,新的一天编号从1 开始
			 sysDictionaryContentService.updateByPrimaryKeySelective(content);
			 content = new SysDictionaryContent();
			 content.setPid(list.get(1).getPid());
			 content.setDictContName("1");
			 sysDictionaryContentService.updateByPrimaryKeySelective(content);
		}
		return getNumber(today,number);
	}

	private static String getNumber(String today, Integer number) {
		if(number < 10){
			return HEAD+CONNECTOR+today+CONNECTOR+NUMBER_HEAD_3+number;
		}else if(number >= 10 && number < 100){
			return HEAD+CONNECTOR+today+CONNECTOR+NUMBER_HEAD_2+number;
		}else if(number >= 100 && number < 1000){
			return HEAD+CONNECTOR+today+CONNECTOR+NUMBER_HEAD_1+number;
		}else if(number >= 1000){
			return HEAD+CONNECTOR+today+"-"+number;
		}
		return "";
	}
}


