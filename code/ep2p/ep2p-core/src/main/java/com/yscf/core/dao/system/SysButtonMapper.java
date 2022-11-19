package com.yscf.core.dao.system;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.system.SysButton;

@MapperScan("sysButtonMapper")
public interface SysButtonMapper extends IBaseDao<BaseEntity, String> {
    int deleteByPrimaryKey(String pid);

    int insert(SysButton record);

    int insertSelective(SysButton record);

    SysButton selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(SysButton record);

    int updateByPrimaryKey(SysButton record);
    
    /**
     * Description：查询按钮列表  分页
     * @author  JingYu.Dai
     * @date    2015年9月22日
     * @version v1.0.0
     * @param sysButton 按钮对象
     * @param info 分页对象
     * @return PageList<SysButton>
     */ 
    PageList<SysButton> selectSelectivePage(@Param("map")SysButton sysButton,PageInfo info);
    
    /**
	 * Description：菜单按钮配置 查询可选按钮
	 * @author  JingYu.Dai
	 * @date    2015年9月22日
	 * @version v1.0.0
	 * @param info 分页对象
	 * @return PageList<SysButton>
	 * @throws FrameworkException
	 */
	PageList<SysButton> selectChoosableButtonPage(@Param("map")Map<String,String> map,PageInfo info);
	
	/**
	 * Description：菜单按钮配置 查询衣已选按钮 
	 * @author  JingYu.Dai
	 * @date    2015年9月22日
	 * @version v1.0.0
	 * @param info 分页对象
	 * @return PageList<SysButton>
	 * @throws FrameworkException
	 */
	PageList<SysButton> selectSelectedButtonPage(@Param("map")Map<String,String> map ,PageInfo info);
	
	/**
	 * Description：获取最大按钮编码
	 * @author  JingYu.Dai
	 * @date    2015年9月25日
	 * @version v1.0.0
	 * @return String
	 */
	String getMaxButtonCode();
	
	/**
	 * Description：<br> 
	 * 根据菜单ID 查询按钮集合
	 * @author  JingYu.Dai
	 * @date    2015年11月2日
	 * @version v1.0.0
	 * @param map 
	 * @return List<SysButton>
	 */
	List<SysButton> getButtonsByMenuId(Map<String,String> map);
}