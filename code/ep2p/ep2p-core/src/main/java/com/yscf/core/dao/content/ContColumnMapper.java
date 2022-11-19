package com.yscf.core.dao.content;
 

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.content.ContColumn;
@MapperScan("contColumnMapper")
public interface ContColumnMapper extends IBaseDao<BaseEntity, String> {
	public int deleteByPrimaryKey(String pid);

    public  int insert(ContColumn record);

    public  int insertSelective(ContColumn record);

    public   ContColumn selectByPrimaryKey(String pid);

    public  int updateByPrimaryKeySelective(ContColumn record);

    public int updateByPrimaryKey(ContColumn record);
    /**
     * 
     * Description：<br> 
     *  根据条件查询 内容栏目  查询全部请new ContColumn
     * @author  fengshiliang
     * @date    2015年9月17日
     * @version v1.0.0
     * @param record
     * @return
     */
    public PageList<ContColumn> selectContentByParameter(@Param("map")ContColumn column, PageInfo info);
    /**
     * 
     * Description：<br> 
     *  根据pids 批量更新  栏目信息
     * @author  fengshiliang
     * @date    2015年9月17日
     * @version v1.0.0
     * @param params
     * @return
     */
    public  boolean batchUpdateByPids(@Param("map") Map<String,Object> map);
}