/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 数据字典内容业务交互类
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月10日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.system;

import java.util.List;
import java.util.Map;

import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.yscf.core.model.system.SysDictionaryContent;

/**
 * Description：<br>
 * 数据字典内容业务交互接口
 * 
 * @author Yu.Zhang
 * @date 2015年9月10日
 * @version v1.0.0
 */
public interface ISysDictionaryContentService {

	/**
	 * 
	 * Description：<br>
	 * 更新数据字典内容状态 pid 可以以','拼接,进行批量修改状态
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月10日
	 * @version v1.0.0
	 * @param sysDictionaryContent
	 *            pid:213,321 status:0 or 1
	 * @return
	 */
	int updateStatusBatch(SysDictionaryContent sysDictionaryContent);

	/**
	 * 
	 * Description：<br>
	 * 数据字典内容数据查询
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月11日
	 * @version v1.0.0
	 * @param sysDictionaryContent
	 * @param info
	 * @return
	 */
	List<SysDictionaryContent> selectAll(SysDictionaryContent sysDictionaryContent);

	/**
	 * 
	 * Description：<br>
	 * 数据字典内容数据查询 分页
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月11日
	 * @version v1.0.0
	 * @param sysDictionaryContent
	 * @param info
	 * @return
	 */
	List<SysDictionaryContent> selectAllPage(SysDictionaryContent sysDictionaryContent, PageInfo info);

	/**
	 * 
	 * Description：<br>
	 * 验证code 是否存在
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月15日
	 * @version v1.0.0
	 * @param sysDictionaryConten
	 * @return
	 */
	boolean validateCode(SysDictionaryContent sysDictionaryConten);

	/**
	 * 
	 * Description：<br>
	 * 动态更新数据
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月17日
	 * @version v1.0.0
	 * @param sysDictionaryContent
	 * @return
	 */
	int updateByPrimaryKeySelective(SysDictionaryContent sysDictionaryContent);

	/**
	 * 
	 * Description：<br>
	 * 根据数据字典code 查询内容
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月18日
	 * @version v1.0.0
	 * @param dictCode
	 * @return
	 */
	List<SysDictionaryContent> selectByDisctCode(String dictCode);
	
	/**
	 * 
	 * Description：<br>
	 * 根据数据字典code 查询内容
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月18日
	 * @version v1.0.0
	 * @param dictCode
	 * @return
	 */
	Map<String,String> selectByDisctCodeMap(String dictCode);

	/**
	 * 
	 * @Description : 根据数据字典code和数据字典内容code查询数据字典内容名称
	 * @param dictCode
	 *            数据字典code
	 * @param dictContCode
	 *            数据字典内容code
	 * @return 数据字典类型名称
	 * @Author : Qing.Cai
	 * @Date : 2015年12月10日 下午3:29:30
	 */
	String selectDictionaryContentName(String dictCode, String dictContCode);
}
