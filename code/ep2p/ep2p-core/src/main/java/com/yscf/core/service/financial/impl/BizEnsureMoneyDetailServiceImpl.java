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
 * 2015年10月22日     jenkin.yu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.financial.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.core.dao.financial.BizEnsureMoneyDetailMapper;
import com.yscf.core.model.comm.ExportObjectModel;
import com.yscf.core.model.financial.BizEnsureMoneyDetail;
import com.yscf.core.service.financial.IBizEnsureMoneyDetailService;

/**
 * Description：<br> 
 * TODO
 * @author  jenkin.yu
 * @date    2015年10月22日
 * @version v1.0.0
 */
@Service("bizEnsureMoneyDetailServiceImpl")
public class BizEnsureMoneyDetailServiceImpl extends BaseService<BaseEntity, String> implements IBizEnsureMoneyDetailService{
	
	@Autowired
	public BizEnsureMoneyDetailServiceImpl(BizEnsureMoneyDetailMapper dao) {
		super(dao);
	}

	@Override
	public PageList<BizEnsureMoneyDetail> selectBizEnsureDetailPage(BizEnsureMoneyDetail detail, PageInfo info)
			throws FrameworkException {
		
		BizEnsureMoneyDetailMapper mapper =   (BizEnsureMoneyDetailMapper) getDao();
		return mapper.selectAllPage(detail,info);
	}

	@Override
	public BigDecimal selectSumHappenValue(BizEnsureMoneyDetail detail) {
		BizEnsureMoneyDetailMapper mapper =   (BizEnsureMoneyDetailMapper) getDao();
		List<BizEnsureMoneyDetail> list = mapper.selectSumHappenValue(detail);
		if(null!= list && list.size() > 0 && null!=list.get(0)){
			return list.get(0).getHappenValue();
		}
		return new BigDecimal(0);
	}

	public List<BizEnsureMoneyDetail> selectAll(BizEnsureMoneyDetail bizEnsureMoneyDetail) {
		BizEnsureMoneyDetailMapper mapper =   (BizEnsureMoneyDetailMapper) getDao();
		return mapper.selectAll(bizEnsureMoneyDetail);
	}

	public List<BizEnsureMoneyDetail> selectEnsureMoneyDetail(BizEnsureMoneyDetail bizEnsureMoneyDetail, ExportObjectModel eom) {
		
		BizEnsureMoneyDetailMapper bamapper = (BizEnsureMoneyDetailMapper) getDao();
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("bba", bizEnsureMoneyDetail);
		map.put("expm", eom);
		
		List<BizEnsureMoneyDetail> elist = bamapper.selectEnsureMoneyDetail(map); 
		if(null == elist){
			elist = new ArrayList<BizEnsureMoneyDetail>(0);
		}
		return elist;
	}
	
}


