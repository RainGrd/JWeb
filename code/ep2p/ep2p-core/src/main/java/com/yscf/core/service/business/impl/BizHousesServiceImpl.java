/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * TODO
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月22日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.business.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.core.dao.business.BizHousesMapper;
import com.yscf.core.model.business.BizHouses;
import com.yscf.core.service.business.IBizHousesService;

/**
 * Description：<br>
 * 楼盘管理业务处理类
 * 
 * @author Yu.Zhang
 * @date 2015年9月22日
 * @version v1.0.0
 */
@Service("bizHousesService")
public class BizHousesServiceImpl extends BaseService<BaseEntity, String>
		implements IBizHousesService {

	@Autowired
	public BizHousesServiceImpl(BizHousesMapper dao) {
		super(dao);
	}

	@Resource
	private BizHousesMapper bizHousesMapper;

	@Override
	public List<BizHouses> selectDistinctProvince(BizHouses record) {
		BizHousesMapper mapper = (BizHousesMapper) getDao();
		return mapper.selectDistinctProvince(record);
	}

	@Override
	public List<BizHouses> selectDistinctCityByProvince(BizHouses record) {
		BizHousesMapper mapper = (BizHousesMapper) getDao();
		return mapper.selectDistinctCityByProvince(record);
	}

	@Override
	public List<BizHouses> selectDistinctAreaByCity(BizHouses record) {
		BizHousesMapper mapper = (BizHousesMapper) getDao();
		return mapper.selectDistinctAreaByCity(record);
	}

	@Override
	public List<BizHouses> selectDistinctHomesNameByAddress(BizHouses record) {
		BizHousesMapper mapper = (BizHousesMapper) getDao();
		return mapper.selectDistinctHomesNameByAddress(record);
	}

	@Override
	public List<BizHouses> selectDistinctHomesTypeByHomesName(BizHouses record) {
		BizHousesMapper mapper = (BizHousesMapper) getDao();
		return mapper.selectDistinctHomesTypeByHomesName(record);
	}

	@Override
	public int updateByPrimaryKeySelective(BizHouses record) {
		BizHousesMapper mapper = (BizHousesMapper) getDao();
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public PageList<BizHouses> selectAllPage(BizHouses record, PageInfo info) {
		BizHousesMapper mapper = (BizHousesMapper) getDao();
		return mapper.selectAllPage(record, info);
	}

	@Override
	public BizHouses selectHousesByBorrowId(String borrowId) {
		return bizHousesMapper.selectHousesByBorrowId(borrowId);
	}

}
