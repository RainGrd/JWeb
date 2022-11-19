package com.yscf.core.dao.business;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.yscf.core.model.business.BizBorrowCondRel;

/**
 * 
 * Description：<br>
 * 借款信息与条件关系表mapper
 * 
 * @author fengshiliang
 * @date 2015年10月20日
 * @version v1.0.0
 */
@MapperScan("bizBorrowCondRelMapper")
public interface BizBorrowCondRelMapper extends IBaseDao<BaseEntity, String> {
	public int deleteByPrimaryKey(String pid);

	public int insert(BizBorrowCondRel record);

	public int insertSelective(BizBorrowCondRel record);

	public BizBorrowCondRel selectByPrimaryKey(String pid);

	public int updateByPrimaryKeySelective(BizBorrowCondRel record);

	public int updateByPrimaryKey(BizBorrowCondRel record);

	public void deleteByBorrowId(String borrowId);

	/**
	 * 
	 * Description：<br>
	 * 根据标的id获取已参与条件
	 * 
	 * @author JunJie.Liu
	 * @date 2015年10月22日
	 * @version v1.0.0
	 * @param objectId
	 * @return
	 */
	public List<BizBorrowCondRel> selectNewStandardConRelByObjectId(
			String objectId);
}