/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月10日     fengshiliang		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.system;

import java.util.List;

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.system.SysParams;

/**
 * Description： <br>
 * 系统参数服务接口
 * 
 * @author fengshiliang
 * @date 2015年9月10日
 * @version v1.0.0
 */
public interface ISysParamsService {

	/**
	 * 
	 * Description：<br>
	 * 根据key或value查询系统参数 如果为空查询全部
	 * 
	 * @author fengshiliang
	 * @date 2015年9月10日
	 * @version v1.0.0
	 * @param key
	 * @return
	 * @throws FrameworkException
	 */
	public PageList<SysParams> searchParamsByKeyOrVal(SysParams params,
			PageInfo info) throws FrameworkException;

	/**
	 * 
	 * Description：<br>
	 * 新增或修改系统参数
	 * 
	 * @author fengshiliang
	 * @date 2015年9月10日
	 * @version v1.0.0
	 * @param params
	 * @return
	 */
	public boolean saveOrUpdateParams(SysParams params)
			throws FrameworkException;

	/**
	 * 
	 * Description：<br>
	 * 批量删除 多个pid 以,号分割
	 * 
	 * @author fengshiliang
	 * @date 2015年9月10日
	 * @version v1.0.0
	 * @param pids
	 * @return
	 */
	public boolean batchDeletePara(SysParams params) throws FrameworkException;

	/**
	 * 
	 * Description：<br>
	 * 根据pid 查询系统参数
	 * 
	 * @author fengshiliang
	 * @date 2015年9月14日
	 * @version v1.0.0
	 * @param pid
	 * @return
	 */
	public SysParams getParamsByPid(String pid);

	/**
	 * 
	 * Description：<br>
	 * 根据id 更新系统参数
	 * 
	 * @author fengshiliang
	 * @date 2015年9月14日
	 * @version v1.0.0
	 * @param pid
	 * @return
	 */
	public void updateParamByPid(SysParams params);

	/**
	 * Description：<br>
	 * 根据参数主键查询
	 * 
	 * @author jenkin.yu
	 * @date 2015年10月21日
	 * @version v1.0.0
	 * @param paramKey
	 * @return
	 */
	public SysParams getParamsByParamKey(String paramKey);

	/**
	 * 
	 * Description：<br>
	 * 根据key 获取系统参数值
	 * 
	 * @author JunJie.Liu
	 * @date 2015年10月23日
	 * @version v1.0.0
	 * @param paramKey
	 * @return
	 */
	public List<SysParams> searchParamsByKey(String paramKey);
}
