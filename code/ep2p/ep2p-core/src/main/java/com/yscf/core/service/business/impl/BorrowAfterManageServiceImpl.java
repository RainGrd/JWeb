/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 借后管理服务层
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年10月13日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.business.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.core.dao.business.BorrowAfterManageMapper;
import com.yscf.core.model.business.BizBorrowAfter;
import com.yscf.core.model.comm.ExportObjectModel;
import com.yscf.core.service.business.IBorrowAfterManageService;

/**
 * Description：<br> 
 * 借后管理服务层
 * @author  Lin Xu
 * @date    2015年10月13日
 * @version v1.0.0
 */
@Service("borrowAfterManageServiceImpl")
public class BorrowAfterManageServiceImpl extends BaseService<BaseEntity, String> implements IBorrowAfterManageService{
	//日志处理
	Logger logger = Logger.getLogger(BorrowAfterManageServiceImpl.class);
	
	@Autowired
	public BorrowAfterManageServiceImpl(BorrowAfterManageMapper dao) {
		super(dao);
	}

	@Override
	public PageList<BizBorrowAfter> selectBorrowAfterAll(BizBorrowAfter bba,
			PageInfo pageinfo) {
		BorrowAfterManageMapper bamapper = (BorrowAfterManageMapper) getDao();
		PageList<BizBorrowAfter> pagelist = bamapper.selectBorrowAfterAll(bba, pageinfo);
		if(null != pagelist && pagelist.size() > 0){
			//统计总数
			BizBorrowAfter bbal = new BizBorrowAfter();
			ExportObjectModel expm = new ExportObjectModel();
			HashMap<String, Object> qmap = new HashMap<String, Object>();
				qmap.put("bba", bba);
				qmap.put("expm", expm);
			HashMap<String, BigDecimal> remap = bamapper.selectBorrowSumAfter(qmap);
				//bbal.setBorrowName("总计");
				if(null != remap){
					//获取统计的值
					String sabalance = remap.get("sabalance") == null ? "0.00" : String.valueOf(remap.get("sabalance"));
					String sbormoney = remap.get("sbormoney") == null ? "0.00" : String.valueOf(remap.get("sbormoney"));
					String scapital = remap.get("scapital") == null ? "0.00" : String.valueOf(remap.get("scapital"));
					bbal.setAvailableBalance(sabalance);
					bbal.setBorrowMoney(sbormoney);
					bbal.setCapital(scapital);
					bbal.setPid("1");
				}
			//添加到集合中
			pagelist.add(bbal);
		}else{
			pagelist = new PageList<BizBorrowAfter>();
		}
		return pagelist;
	}

	@Override
	public List<BizBorrowAfter> selectBorrowAfter(BizBorrowAfter bba,
			ExportObjectModel expm) {
		BorrowAfterManageMapper bamapper = (BorrowAfterManageMapper) getDao();
		HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("bba", bba);
			map.put("expm", expm);
		List<BizBorrowAfter> elist = bamapper.selectBorrowAfter(map); 
		if(null != elist && elist.size() > 0){
			//统计总数
			BizBorrowAfter bbal = new BizBorrowAfter();
			HashMap<String, BigDecimal> remap = bamapper.selectBorrowSumAfter(map);
				bbal.setBorrowName("总计");
				//获取统计的值
				if(null != remap){
					String sabalance = remap.get("sabalance") == null ? "0.00" : String.valueOf(remap.get("sabalance"));
					String sbormoney = remap.get("sbormoney") == null ? "0.00" : String.valueOf(remap.get("sbormoney"));
					String scapital = remap.get("scapital") == null ? "0.00" : String.valueOf(remap.get("scapital"));
					bbal.setAvailableBalance(sabalance);
					bbal.setBorrowMoney(sbormoney);
					bbal.setCapital(scapital);
					bbal.setPid("1");
				}
			//添加到集合中
			elist.add(bbal);
		}else{
			elist = new ArrayList<BizBorrowAfter>(0);
		}
		return elist;
	}

	
	@Override
	public Integer selectTotalReplayNumbers() {
		Date date = new Date();//当前日期
		//格式化对象
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");		
		//日历对象
		Calendar calendar = Calendar.getInstance();
		//设置当前日期
		calendar.setTime(date);
		//月份减一
		calendar.add(Calendar.MONTH, 1);
		//输出格式化的日期
		String nextdate = sdf.format(calendar.getTime());
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("nextdate", nextdate);
		//进行查询数据
		BorrowAfterManageMapper bamapper = (BorrowAfterManageMapper) getDao();
		return bamapper.selectTotalReplayNumbers(param);
	}

}


