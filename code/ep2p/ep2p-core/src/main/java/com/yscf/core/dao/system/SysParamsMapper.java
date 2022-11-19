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
package com.yscf.core.dao.system;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.system.SysParams;

/**
 * Description：<br>
 * 系统参数 数据持久层服务接口
 * 
 * @author fengshiliang
 * @date 2015年9月10日
 * @version v1.0.0
 */
@MapperScan("sysParamsMapper")
public interface SysParamsMapper extends IBaseDao<BaseEntity, String> {

	/**
	 * Description ： <br>
	 * 根据key或value查询系统参数 如果为空查询全部
	 * 
	 * @author fengshiliang
	 * @date 2015年9月10日
	 * @version v1.0.0
	 * @param key
	 * @return
	 */
	public PageList<SysParams> searchParamsByKeyOrVal(
			@Param("map") SysParams params, PageInfo info);

	/**
	 * Description ：<br>
	 * 根据pid 查询系统参数
	 * 
	 * @author fengshiliang
	 * @date 2015年9月10日
	 * @version v1.0.0
	 * @param key
	 * @return
	 */
	public SysParams getParamsByPid(@Param(value = "pid") String pid);

	/**
	 * Description ： <br>
	 * 根据多个 pid 修改为-1
	 * 
	 * @author fengshiliang
	 * @date 2015年9月10日
	 * @version v1.0.0
	 * @param key
	 * @return
	 */
	public boolean batchDeletePara(@Param("map") Map<String, Object> map);

	/**
	 * 
	 * Description：<br>
	 * 修改
	 * 
	 * @author fengshiliang
	 * @date 2015年9月16日
	 * @version v1.0.0
	 * @param params
	 * @return
	 */
	void updateParamByPid(SysParams params);

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
	SysParams getParamsByParamKey(@Param(value = "paramKey") String paramKey);

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
	public List<SysParams> searchParamsByKey(@Param("paramKey") String paramKey);
}
