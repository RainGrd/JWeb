/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 短信模板Service接口
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月14日     Jie.Zou		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.system;

import java.util.List;

import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.yscf.core.model.system.SysSmsTemplates;

/**
 * Description：<br> 
 * 短信模板Service借口
 * @author  Jie.Zou
 * @date    2015年9月14日
 * @version v1.0.0
 */
public interface ISysSmsTemplatesService {
	
	/**
	 * 
	 * Description：<br> 
	 * 短信模板查询（分页查询）
	 * @author  Jie.Zou
	 * @date    2015年9月14日
	 * @version v1.0.0
	 * @param smsTemplates
	 * @param info
	 * @return
	 */
	public List<SysSmsTemplates> selectAllPage(SysSmsTemplates smsTemplates,PageInfo info);
	
	/**
	 * 
	 * Description：<br> 
	 * 短信模板查询
	 * @author  Jie.Zou
	 * @date    2015年9月14日
	 * @version v1.0.0
	 * @param smsTemplates
	 * @return
	 */
	public List<SysSmsTemplates> selectAll(SysSmsTemplates smsTemplates);
	
	/**
	 * 
	 * Description：<br> 
	 * 通过Id查询短信模板
	 * @author  Jie.Zou
	 * @date    2015年9月16日
	 * @version v1.0.0
	 * @param pid
	 * @return
	 */
	public SysSmsTemplates getSmsTemplatesById(String pid);
	
	/**
	 * 
	 * Description：<br> 
	 * 修改短信模板
	 * @author  Jie.Zou
	 * @date    2015年9月16日
	 * @version v1.0.0
	 * @param smsTemplates
	 */
	public void updateSmsTemplates(SysSmsTemplates smsTemplates);
	
	/**
	 * 
	 * Description：<br> 
	 * 批量更新短信模板状态
	 * @author  Jie.Zou
	 * @date    2015年9月16日
	 * @version v1.0.0
	 * @param smsTemplates
	 * @return
	 */
	public int updateStatusBatch(SysSmsTemplates smsTemplates);

	
	/**
	 * 
	 * Description：<br> 
	 * 保存
	 * @author  Yu.Zhang
	 * @date    2015年12月15日
	 * @version v1.0.0
	 * @return
	 */
	public int insert(SysSmsTemplates smsTemplates);

	/**
	 * 
	 * Description：<br> 
	 * 根据字典编码查询
	 * @author  Yu.Zhang
	 * @date    2015年12月15日
	 * @version v1.0.0
	 * @param tempKey
	 * @return
	 */
	public SysSmsTemplates selectByTempCode(String tempKey);
	
}


