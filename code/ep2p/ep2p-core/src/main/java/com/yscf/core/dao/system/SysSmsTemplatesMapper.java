package com.yscf.core.dao.system;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.system.SysSmsTemplates;

/**
 * 
 * Description：<br> 
 * 短信模板数据交互接口
 * @author  Jie.Zou
 * @date    2015年9月14日
 * @version v1.0.0
 */
@MapperScan("sysSmsTemplatesMapper")
public interface SysSmsTemplatesMapper extends IBaseDao<BaseEntity, String> {
    int deleteByPrimaryKey(String pid);

    int insert(SysSmsTemplates record);

    int insertSelective(SysSmsTemplates record);

    SysSmsTemplates selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(SysSmsTemplates record);

    int updateByPrimaryKey(SysSmsTemplates record);
    
    /**
     * 
     * Description：<br> 
     * 短信模板分页查询
     * @author  Jie.Zou
     * @date    2015年9月14日
     * @version v1.0.0
     * @param smsTemplates
     * @param info
     * @return
     */
    PageList<SysSmsTemplates> selectAllPage(@Param("map")SysSmsTemplates smsTemplates, PageInfo info);
    
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
    List<SysSmsTemplates> selectAll(SysSmsTemplates smsTemplates);
    
    /**
     * 
     * Description：<br> 
     * 批量更新短信模板状态
     * @author  Jie.Zou
     * @date    2015年9月16日
     * @version v1.0.0
     * @param map
     * @return
     */
    int updateStatusBatch(@Param("map") Map<String,Object> map);

    /**
     * 
     * Description：<br> 
     * 根据手机短信模本编码查询
     * @author  Yu.Zhang
     * @date    2015年12月15日
     * @version v1.0.0
     * @param smsTemplates
     * @return
     */
	SysSmsTemplates selectByTempCode(SysSmsTemplates smsTemplates);
}