/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 用户管理服务实现
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月6日     Simon.Hoo		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.user.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.service.impl.BaseService;
import com.achievo.framework.util.DateUtil;
import com.yscf.core.dao.system.SysSmsTemplatesMapper;
import com.yscf.core.dao.user.CusBirWarnHisMapper;
import com.yscf.core.dao.user.CusTomerMapper;
import com.yscf.core.exception.NoExistPhoneNoException;
import com.yscf.core.model.comm.ExportObjectModel;
import com.yscf.core.model.user.CusBirWarnHis;
import com.yscf.core.model.user.CusTomer;
import com.yscf.core.service.activity.impl.ActVipActDetailServiceImpl;
import com.yscf.core.service.sms.impl.SmsServiceImpl;
import com.yscf.core.service.user.ICusBirWarnHisService;

/**
 * 
 * @ClassName : CusBirWarnHisServiceImpl
 * @Description : VIP生日提醒历史商务接口
 * @Author : Qing.Cai
 * @Date : 2015年10月24日 下午6:48:09
 */
@Service("cusBirWarnHisServiceImpl")
public class CusBirWarnHisServiceImpl extends BaseService<BaseEntity, String> implements ICusBirWarnHisService {

	private Logger logger = LoggerFactory.getLogger(ActVipActDetailServiceImpl.class);

	@Autowired
	public CusBirWarnHisServiceImpl(CusBirWarnHisMapper dao) {
		super(dao);
	}

	/** 客户信息Maper接口 */
	@Autowired
	public CusTomerMapper cusTomerMapper;
	
	/** 短信模板Maper接口 */
	@Autowired
	public SysSmsTemplatesMapper sysSmsTemplatesMapper;
	
	/** 发送短信服务类 */
	@Autowired
	public SmsServiceImpl smsServiceImpl;
	
	/**
	 * 
	 * @Description : VIP生日提醒列表,带分页
	 * @param cusBirWarnHis
	 *            VIP生日提醒对象
	 * @param pageInfo
	 *            分页对象
	 * @return 当年VIP生日提醒列表
	 * @Author : Qing.Cai
	 * @Date : 2015年10月24日 下午6:49:53
	 */
	@Override
	public PageList<CusBirWarnHis> selectAllPage(CusBirWarnHis cusBirWarnHis, PageInfo pageInfo) {
		CusBirWarnHisMapper mapper = (CusBirWarnHisMapper) getDao();
		return mapper.selectAllPage(cusBirWarnHis, pageInfo);
	}

	/**
	 * 
	 * @Description : 新增VIP生日短信提醒
	 * @param cusBirWarnHis
	 *            VIP生日对象
	 * @Author : Qing.Cai
	 * @Date : 2015年10月26日 上午2:29:04
	 */
	@Override
	public void saveCusBirWarnHis(CusBirWarnHis cusBirWarnHis) throws FrameworkException {
		CusBirWarnHisMapper mapper = (CusBirWarnHisMapper) getDao();
		try {
			// 赋值
			cusBirWarnHis.setPid(cusBirWarnHis.getPK());// 主键
			cusBirWarnHis.setOperTime(DateUtil.getToday().toString());// 提醒时间
			cusBirWarnHis.setIsWran("1");// 已提醒
			cusBirWarnHis.setStatus("1");// 状态为正常
			cusBirWarnHis.setCreateTime(DateUtil.getToday());// 创建时间
			mapper.insertSelective(cusBirWarnHis);
			// TODO:发送短信
			// 创建用户对象
			CusTomer ct = cusTomerMapper.selectByPrimaryKey(cusBirWarnHis.getCustomerId());
			// 手机号码字符串(以,隔开)
			String phoneNo = ct.getPhoneNo();
			// 判断手机号码是否存在
			if(null == phoneNo || phoneNo.equals("")){
				throw new NoExistPhoneNoException("手机号码不存在.");
			}
			// 所属模块
			String model = "user";
			// 短信模板ID
			String tempKey = sysSmsTemplatesMapper.selectByPrimaryKey(cusBirWarnHis.getSmsTempId()).getTempCode();
			// 短信模板替换Map
			Map<String, String> map = new HashMap<String, String>();
			// 批量发送短信
			smsServiceImpl.sendSmsCode(phoneNo, model, tempKey, map);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("VIP生日提醒失败", e.getMessage());
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * 
	 * @Description : 导出
	 * @param cusBirWarnHis
	 *            VIP生日提醒对象
	 * @param eom
	 *            导出对象
	 * @return 今年VIP生日提醒列表
	 * @Author : Qing.Cai
	 * @Date : 2015年11月5日 下午6:58:12
	 */
	@Override
	public List<CusBirWarnHis> selectAllPageDetailExport(CusBirWarnHis cusBirWarnHis, ExportObjectModel eom) {
		List<CusBirWarnHis> list = null;
		try {
			CusBirWarnHisMapper mapper = (CusBirWarnHisMapper) getDao();

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("cusBirWarnHis", cusBirWarnHis);
			map.put("expm", eom);

			list = mapper.selectAllPageDetailExport(map);

			if (null == list) {
				list = new ArrayList<CusBirWarnHis>();
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("今年VIP生日提醒列表:" + e.getMessage());
			}
		}
		return list;
	}

	/**
	 * 
	 * @Description : 查询今天VIP生日提醒数
	 * @return 总数
	 * @Author : Qing.Cai
	 * @Date : 2015年11月12日 下午5:11:52
	 */
	@Override
	public int selectVipBirCount() {
		int count = 0;
		try {
			CusBirWarnHisMapper mapper = (CusBirWarnHisMapper) getDao();
			count = mapper.selectVipBirCount();
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("查询今天VIP生日提醒数:" + e.getMessage());
			}
		}
		return count;
	}

}
