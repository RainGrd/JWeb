package com.yscf.core.service.user.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.service.impl.BaseService;
import com.achievo.framework.util.DateUtil;
import com.yscf.common.Constant.Constant;
import com.yscf.core.dao.user.CustSignInMapper;
import com.yscf.core.model.user.CustSignIn;
import com.yscf.core.service.user.ICustPoinWaterService;
import com.yscf.core.service.user.ICustSignInService;

/**
 * 
 * @ClassName : CustSignInServiceImpl
 * @Description : 签到信息业务处理类
 * @Author : Qing.Cai
 * @Date : 2015年12月7日 下午3:36:40
 */
@Service("custSignInServiceImpl")
public class CustSignInServiceImpl extends BaseService<BaseEntity, String> implements ICustSignInService {

	private Logger logger = LoggerFactory.getLogger(CustSignInServiceImpl.class);

	@Autowired
	public CustSignInServiceImpl(CustSignInMapper dao) {
		super(dao);
	}

	@Resource(name = "custPoinWaterServiceImpl")
	private ICustPoinWaterService custPoinWaterService;

	/**
	 * 
	 * @Description : 查询签到达人榜(签到次数前五名)
	 * @return 签到集合
	 * @Author : Qing.Cai
	 * @Date : 2015年12月7日 下午3:58:36
	 */
	@Override
	public List<CustSignIn> selectSignInDarenCharts() {
		CustSignInMapper mapper = (CustSignInMapper) getDao();
		List<CustSignIn> list = null;
		try {
			list = mapper.selectSignInDarenCharts();
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("查询签到达人榜:" + e.getMessage());
			}
		}
		return list;
	}

	/**
	 * 
	 * @Description : 新增签到信息
	 * @param custSignIn
	 *            签到信息
	 * @Author : Qing.Cai
	 * @Date : 2015年12月7日 下午3:58:57
	 */
	@Override
	public int custSignInEdit(CustSignIn custSignIn) {
		// 0：失败 其他：成功
		int count = 0;
		CustSignInMapper mapper = (CustSignInMapper) getDao();
		try {

			// 判断今天是否签到
			Integer row = 0;
			row = mapper.checkWhetheSignIn(custSignIn.getCustomerId());
			if (row == 0) {
				String date = DateUtil.getToday().toString();
				custSignIn.setPid(custSignIn.getPK());// 设置主键
				custSignIn.setSignInTime(date);// 设置签到时间
				custSignIn.setVersion("1");// 设置版本好为1,初始值
				custSignIn.setCreateTime(date);// 设置创建时间
				// 新增
				mapper.insertSelective(custSignIn);

				// 新增成功后，需要添加添加用户积分
				custPoinWaterService.pointObtain(Constant.POINT_TYPE_11, null, custSignIn.getCustomerId());

				// 签到成功
				count = 1;
			}

		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("新增签到信息:" + e.getMessage());
			}
		}
		return count;
	}

	/**
	 * 
	 * @Description : 查询客户签到总数
	 * @param customerId
	 *            客户ID
	 * @return 签到总天数
	 * @Author : Qing.Cai
	 * @Date : 2015年12月7日 下午4:38:44
	 */
	@Override
	public int selectUserSignInSum(String customerId) {
		CustSignInMapper mapper = (CustSignInMapper) getDao();
		int count = 0;
		try {
			count = mapper.selectUserSignInSum(customerId);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("查询客户签到总数:" + e.getMessage());
			}
		}
		return count;
	}

	/**
	 * 
	 * @Description : 判断是否签到
	 * @param customerId
	 *            用户ID
	 * @return 0：未签到 其他：已签到
	 * @Author : Qing.Cai
	 * @Date : 2016年1月19日 上午10:39:54
	 */
	@Override
	public int checkWhetheSignIn(String customerId) {
		int row = 0 ;
		CustSignInMapper mapper = (CustSignInMapper) getDao();
		try {
			row = mapper.checkWhetheSignIn(customerId);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("判断是否签到:" + e.getMessage());
			}
		}
		return row;
	}

}
