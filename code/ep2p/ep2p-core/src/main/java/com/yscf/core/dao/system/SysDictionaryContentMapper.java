package com.yscf.core.dao.system;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.system.SysDictionaryContent;

/**
 * 
 * Description：<br>
 * 数据字典内容数据交互接口
 * 
 * @author Yu.Zhang
 * @date 2015年9月10日
 * @version v1.0.0
 */
@MapperScan("sysDictionaryContentMapper")
public interface SysDictionaryContentMapper extends IBaseDao<BaseEntity, String> {
	int deleteByPrimaryKey(String pid);

	int insert(SysDictionaryContent record);

	int insertSelective(SysDictionaryContent record);

	SysDictionaryContent selectByPrimaryKey(String pid);

	int updateByPrimaryKeySelective(SysDictionaryContent record);

	int updateByPrimaryKey(SysDictionaryContent record);

	/**
	 * 
	 * Description：<br>
	 * 批量更新状态
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月10日
	 * @version v1.0.0
	 * @param map
	 * @return
	 */
	int updateStatusBatch(@Param("map") Map<String, Object> map);

	/**
	 * 
	 * Description：<br>
	 * 数据字典内容查询
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月11日
	 * @version v1.0.0
	 * @param sysDictionaryContent
	 * @return
	 */
	List<SysDictionaryContent> selectAll(SysDictionaryContent sysDictionaryContent);

	/**
	 * 
	 * Description：<br>
	 * 数据字典内容查询分页
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月11日
	 * @version v1.0.0
	 * @param sysDictionaryContent
	 * @return
	 */
	PageList<SysDictionaryContent> selectAllPage(@Param("map") SysDictionaryContent sysDictionaryContent, PageInfo info);

	/**
	 * 
	 * Description：<br>
	 * 验证Code 是否存在
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月15日
	 * @version v1.0.0
	 * @param sysDictionaryContent
	 * @return
	 */
	List<SysDictionaryContent> validateCode(SysDictionaryContent sysDictionaryContent);

	/**
	 * 
	 * Description：<br>
	 * 根据数据字典code 查询
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
	 * @Description : 根据数据字典code和数据字典内容code查询数据字典内容名称
	 * @param dictCode
	 *            数据字典code
	 * @param dictContCode
	 *            数据字典内容code
	 * @return 数据字典类型名称
	 * @Author : Qing.Cai
	 * @Date : 2015年12月10日 下午3:28:30
	 */
	String selectDictionaryContentName(@Param("dictCode")String dictCode, @Param("dictContCode")String dictContCode);
}