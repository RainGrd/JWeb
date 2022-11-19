package com.yscf.core.dao.business;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.yscf.core.model.business.BizBorrowFileRel;

/**
 * 
 * Description：<br> 
 * 借款文件中间表 数据交互类
 * @author  Yu.Zhang
 * @date    2015年9月22日
 * @version v1.0.0
 */
@MapperScan("bizBorrowFileRelMapper")
public interface BizBorrowFileRelMapper extends IBaseDao<BaseEntity, String> {
    int deleteByPrimaryKey(String pid);

    int insert(BizBorrowFileRel record);

    int insertSelective(BizBorrowFileRel record);

    BizBorrowFileRel selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(BizBorrowFileRel record);

    int updateByPrimaryKey(BizBorrowFileRel record);

	/**
	 * 数据查询
	 * Description：<br> 
	 * TODO
	 * @author  Yu.Zhang
	 * @date    2015年9月23日
	 * @version v1.0.0
	 * @param borrow
	 * @return
	 */
	List<BizBorrowFileRel> selectAll(BizBorrowFileRel borrow);
	
	/**
	 * 
	 * Description：<br> 
	 * 通过借款ID得到上传的文件信息
	 * @author  Jie.Zou
	 * @date    2016年3月3日
	 * @version v1.0.0
	 * @param borrowId
	 * @return
	 */
	List<BizBorrowFileRel> selectByBorrowId(String borrowId);
}