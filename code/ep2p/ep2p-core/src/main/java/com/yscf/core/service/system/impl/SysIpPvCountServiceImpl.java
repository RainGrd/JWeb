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
 * 2015年10月19日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.system.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.service.impl.BaseService;
import com.achievo.framework.util.DateUtil;
import com.yscf.core.dao.system.SysIpPvCountMapper;
import com.yscf.core.model.system.SysIpPvCount;
import com.yscf.core.service.system.ISysIpPvCountService;

/**
 * Description：<br> 
 * TODO
 * @author  Yu.Zhang
 * @date    2015年10月19日
 * @version v1.0.0
 */
@Service("sysIpPvCountService")
public class SysIpPvCountServiceImpl extends BaseService<BaseEntity, String>  implements ISysIpPvCountService{

	@Autowired
	public SysIpPvCountServiceImpl(SysIpPvCountMapper dao) {
		super(dao);
	}

	@Override
	public void executeIpPvCount(SysIpPvCount record) {
		SysIpPvCountMapper mapper = (SysIpPvCountMapper) getDao();
		mapper.executeIpPvCount(record);
	}

	@SuppressWarnings("static-access")
	@Override
	public Map<String, Object> selectNearlySevenData() {
		Map<String, Object> map = new HashMap<String, Object>();	// 近七天 查询日期条件集合
		
		Date date = new Date();//获取当前时间 
		Calendar calendar = new GregorianCalendar(); 
		calendar.setTime(date); 
		List<String> days = new ArrayList<String>();	// 近七天 天 集合
		
		for(int i=1;i<8;i++){
			calendar.add(calendar.DATE,-1);//把日期往后增加一天.整数往后推,负数往前移动 
			date=calendar.getTime();   //这个时间就是日期往后推一天的结果 
			map.put("day"+i, DateUtil.format(date,"yyyy-MM-dd"));
			days.add( DateUtil.format(date,"dd")+"日");
		}
		
		// 查询近七日fangwe数据
		SysIpPvCountMapper mapper = (SysIpPvCountMapper) getDao();
		List<SysIpPvCount> list = mapper.selectNearlySevenData(map);
		map.clear();
		
		List<Integer> ipCount = new ArrayList<Integer>();
		List<Integer> pvCount = new ArrayList<Integer>();
		
		for(SysIpPvCount ipPvCount : list){
			ipCount.add(ipPvCount.getIpCount());
			pvCount.add(ipPvCount.getPvCount());
		}
		
		if(list.size()< 8){
			for(int i = list.size();i< 7;i++){
				ipCount.add(0);
				pvCount.add(0);
			}
		}
		
		
		map.put("days", days.toArray());
		map.put("ipCount", ipCount.toArray());
		map.put("pvCount", pvCount.toArray());
		return map;
	}

}


