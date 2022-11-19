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

import java.util.List;

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
import com.yscf.core.dao.pub.PubFileMapper;
import com.yscf.core.dao.user.CusTomerMapper;
import com.yscf.core.dao.user.CustAuthenticationFileRelMapper;
import com.yscf.core.dao.user.CustAuthenticationHisMapper;
import com.yscf.core.model.pub.PubFile;
import com.yscf.core.model.user.CusTomer;
import com.yscf.core.model.user.CustAuthenticationFileRel;
import com.yscf.core.model.user.CustAuthenticationHis;
import com.yscf.core.service.activity.impl.ActVipActDetailServiceImpl;
import com.yscf.core.service.user.ICustAuthenticationHisService;

/**
 * 
 * @ClassName : CustAuthenticationHisServiceImpl
 * @Description : 实名认证历史商务接口
 * @Author : Qing.Cai
 * @Date : 2015年10月24日 下午6:48:09
 */
@Service("custAuthenticationHisServiceImpl")
public class CustAuthenticationHisServiceImpl extends BaseService<BaseEntity, String> implements ICustAuthenticationHisService {
	

	private Logger logger = LoggerFactory.getLogger(ActVipActDetailServiceImpl.class);
	
	@Autowired
	public CustAuthenticationHisServiceImpl(CustAuthenticationHisMapper dao) {
		super(dao);
	}

	/** 客户Mapper */
	@Autowired
	public CusTomerMapper cusTomerMapper;
	/** 客户实名认证图片Mapper */
	@Autowired
	public CustAuthenticationFileRelMapper custAuthenticationFileRelMapper;
	/** 公共文件Mapper */
	@Autowired
	public PubFileMapper pubFileMapper;

	/**
	 * 
	 * @Description : 实名认证列表,带分页
	 * @param custAuthenticationHis
	 *            实名认证对象
	 * @param pageInfo
	 *            分页对象
	 * @return 当年实名认证列表
	 * @Author : Qing.Cai
	 * @Date : 2015年10月24日 下午6:49:53
	 */
	@Override
	public PageList<CustAuthenticationHis> selectAllPage(CustAuthenticationHis custAuthenticationHis, PageInfo pageInfo) {
		CustAuthenticationHisMapper mapper = (CustAuthenticationHisMapper) getDao();
		return mapper.selectAllPage(custAuthenticationHis, pageInfo);
	}

	/**
	 * 
	 * @Description : 后台操作实名认证
	 * @param custAuthenticationHis
	 *            实名认证对象
	 * @Author : Qing.Cai
	 * @Date : 2015年10月26日 上午2:29:04
	 */
	@Override
	public void saveCustAuthenticationHis(CustAuthenticationHis custAuthenticationHis) throws FrameworkException {
		try {
			CustAuthenticationHisMapper mapper = (CustAuthenticationHisMapper) getDao();
			String cusId = custAuthenticationHis.getPid();
			// 赋值
			custAuthenticationHis.setPid(custAuthenticationHis.getPK());// 主键
			custAuthenticationHis.setCustomerId(cusId);// 设置客户ID
			custAuthenticationHis.setOperTime(DateUtil.getToday().toString());// 操作时间
			custAuthenticationHis.setCreateTime(DateUtil.getToday().toString());// 创建时间
			mapper.insertSelective(custAuthenticationHis);
			CusTomer c = new CusTomer();
			c.setPid(cusId);// 设置客户ID
			// 判断是否认证成功
			if (custAuthenticationHis.getStatus().equals("1")) {
				// 如果已同意实名认证的话,就修改客户的实名认证状态为 已认证
				c.setIsAttestation("1");
				c.setAttestationStatus("2");
			} else {
				c.setIsAttestation("2");
				c.setAttestationStatus("3");
			}
			// 修改
			cusTomerMapper.updateCusTomerByPid(c);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("后台操作实名认证:" + e.getMessage());
			}
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Description : 实名认证（大陆）- API
	 * @param userId
	 *            用户ID
	 * @param sname
	 *            用户真实姓名
	 * @param identificationNo
	 *            用户身份证号码
	 * @Author : Qing.Cai
	 * @Date : 2016年1月25日 下午4:43:01
	 */
	@Override
	public void realNameAuthenticationMainland(String userId, String sname, String identificationNo) {
		try {
			// 修改用户的认证状态为已认证
			CusTomer c = new CusTomer();
			// 获取
			c.setPid(userId);// 设置客户ID
			c.setIsAttestation("2");// 修改是否认证为：已认证
			c.setAttestationStatus("2");// 修改认证状态为：已同意
			c.setSname(sname);// 真实姓名
			c.setIdentificationNo(identificationNo);// 身份证号码
			// 获取当前用户的出生年月
			String idcardbrithday = identificationNo.substring(6, 14);
			// 拼装出生日期：格式yyyy-MM-dd
			String birthdayStr = idcardbrithday.substring(0, 4)+"-"+idcardbrithday.substring(4, 6)+"-"+idcardbrithday.substring(6);
			c.setBirthday(DateUtil.format(birthdayStr));// 设置出生年月
			// 获取性别  1=男，2=女
			int sex = (Integer.parseInt(identificationNo.substring(16, 17)) % 2 != 0) ? 1: 2; 
			c.setSex(sex+"");// 设置性别
			// 修改
			cusTomerMapper.updateCusTomerByPid(c);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("实名认证（大陆）:" + e.getMessage());
			}
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @Description : 实名认证（非大陆）- API
	 * @param userId
	 *            用户ID
	 * @param sname
	 *            用户真实姓名
	 * @param identificationNo
	 *            用户身份证号码
	 * @param list
	 *            文件对象(有效证件图片)
	 * @Author : Qing.Cai CustAuthenticationFileRel
	 * @Date : 2016年1月25日 下午4:43:05
	 */
	@Override
	public void realNameAuthenticationNonMainland(String userId, String sname, String identificationNo, List<PubFile> list) {
		try {
			// 循环添加PubFile的主键
			for (int i = 0; i < list.size(); i++) {
				PubFile pubFile = list.get(i);
				pubFile.setPid(pubFile.getPK());// 设置主键
				pubFileMapper.insertSelective(pubFile);// 新增文件
				// 创建 实名认证与上传文件 对象
				CustAuthenticationFileRel custAuthenticationFileRel = new CustAuthenticationFileRel();
				// 赋值
				custAuthenticationFileRel.setPid(custAuthenticationFileRel.getPK());// 设置主键
				custAuthenticationFileRel.setFileId(pubFile.getPid());// 设置文件ID
				custAuthenticationFileRelMapper.insertSelective(custAuthenticationFileRel);
			}
			
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("实名认证（非大陆）:" + e.getMessage());
			}
			e.printStackTrace();
		}
	}
	
	

}
