package com.yscf.core.dao.content;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.yscf.core.model.content.ContTag;
@MapperScan("contTagMapper")
public interface ContTagMapper extends IBaseDao<BaseEntity, String> {
	public int deleteByPrimaryKey(String pid);

    public int insert(ContTag record);

    public int insertSelective(ContTag record);

    public ContTag selectByPrimaryKey(String pid);

    public  int updateByPrimaryKeySelective(ContTag record);

    public int updateByPrimaryKey(ContTag record);

    /**
     * 
     * Description：<br> 
     *  根据栏目id 查询栏目已关注的标签
     * @author  fengshiliang
     * @date    2015年9月21日
     * @version v1.0.0
     * @param contentId 栏目id
     * @return
     */
    public List<ContTag> selectedTag(String contentId);
	/**
	 *
	 * Description：<br> 
	 *  每次修改的时候先删除原来栏目内容绑定的标签
	 * @author  fengshiliang
	 * @date    2015年9月23日
	 * @version v1.0.0
	 * @param contentId
	 */
	public void deleteByColuContId(String contentId);
}