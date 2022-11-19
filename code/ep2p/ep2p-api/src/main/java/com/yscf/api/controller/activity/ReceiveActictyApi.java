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
 * 2015年12月28日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.api.controller.activity;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.vo.JsonObject;
import com.yscf.api.common.ApiCode;
import com.yscf.api.controller.base.EscfBaseApi;
import com.yscf.api.util.ApiUtil;
import com.yscf.api.vo.exchange.ActivityVo;
import com.yscf.common.Constant.Constant;
import com.yscf.core.exception.InterestNotEnoughException;
import com.yscf.core.exception.RedNotEnoughException;
import com.yscf.core.model.activity.ActActivity;
import com.yscf.core.model.activity.ActInvAwaActDetail;
import com.yscf.core.model.activity.ActParConRel;
import com.yscf.core.model.activity.ActRedpActDetail;
import com.yscf.core.model.activity.PubActivityArea;
import com.yscf.core.model.activity.PubInvestAward;
import com.yscf.core.model.activity.PubRedpacket;
import com.yscf.core.service.activity.IActActivityService;
import com.yscf.core.service.activity.IActInvAwaActDetailService;
import com.yscf.core.service.activity.IActRedpActDetailService;
import com.yscf.core.service.activity.IPubActivityAreaService;
import com.yscf.core.service.activity.IPubInvestAwardService;
import com.yscf.core.service.activity.IPubRedpacketService;
import com.yscf.core.service.activity.impl.PubActivityAreaServiceImpl;
import com.yscf.core.service.user.impl.CusTomerServiceImpl;

/**
 * 
 * @ClassName : ActivityApi
 * @Description : 活动API控制
 * @Author : Qing.Cai
 * @Date : 2016年1月21日 下午2:29:50
 */
@Controller
@RequestMapping("/activity/receiveActictyApi")
public class ReceiveActictyApi extends EscfBaseApi {

	private Logger logger = LoggerFactory.getLogger(ReceiveActictyApi.class);

	@Autowired
	public ReceiveActictyApi(PubActivityAreaServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return PubActivityArea.class;
	}

	/** 客户信息 */
	@Resource(name = "cusTomerService")
	private CusTomerServiceImpl cusTomerService;
	/** 投资奖励明细商务接口 */
	@Resource(name = "actInvAwaActDetailServiceImpl")
	private IActInvAwaActDetailService actInvAwaActDetailServiceImpl;
	/** 红包明细商务接口 */
	@Resource(name = "actRedpActDetailServiceImpl")
	private IActRedpActDetailService actRedpActDetailServiceImpl;
	/** 红包商务接口 */
	@Resource(name = "pubRedpacketServiceImpl")
	private IPubRedpacketService pubRedpacketServiceImpl;
	/** 加息劵商务接口 */
	@Resource(name = "pubInvestAwardServiceImpl")
	private IPubInvestAwardService pubInvestAwardServiceImpl;
	/** 活动专区商务接口 */
	@Resource(name = "pubActivityAreaServiceImpl")
	private IPubActivityAreaService pubActivityAreaServiceImpl;
	/** 活动管理商务接口 */
	@Resource(name = "actActivityServiceImpl")
	private IActActivityService actActivityServiceImpl;

	/**
	 * 
	 * @Description : 领取红包
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @return JsonObject
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2016年3月8日 下午4:06:24
	 */
	@RequestMapping(value = "/receiveRed", method = RequestMethod.POST)
	@ResponseBody
	public String receiveRed(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		try {
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			// 用户ID
			String userId = getCustomerId(xtoken);
			// 获取传过来的参数
			ActivityVo activity = (ActivityVo) ApiUtil.convertObjectByBody(request, ActivityVo.class);
			// 判断
			if (!StringUtils.hasLength(activity.getRedpacketId())) {
				processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "红包活动ID不能为空", false);
			} else if (!StringUtils.hasLength(userId)) {
				processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "客户id为null", false);
			} else {
				// 查询当前最新的抢红包活动
				PubActivityArea pubActivityArea = pubActivityAreaServiceImpl.selectPubActivityByType(1);
				// 检查当前用户是否领取当前活动的红包 (1.未领取 2.已领取 3.抢光了)
				String redStatus = actRedpActDetailServiceImpl.checkUserReceiveRedStatus(activity.getRedpacketId(), userId);
				if(pubActivityArea.getActivityStatus().equals("1")){
					if (redStatus.equals("1")) {
						// 调用领取红包的接口
						Double receiveAmount = actRedpActDetailServiceImpl.receiveRed(activity.getRedpacketId(), userId);
						// 获取红包领取明细
						List<ActRedpActDetail> alreadyReceiveList = actRedpActDetailServiceImpl.selectReceiveRedDetail(activity.getRedpacketId());
						// 根据红包ID获取红包对象
						PubRedpacket pubRedpacket = pubRedpacketServiceImpl.getPubRedpacketByPid(activity.getRedpacketId());
						// 获取已经领取的金额
						Double alreadyReceiveAmount = 0.0;
						// 循环
						for (int i = 0; i < alreadyReceiveList.size(); i++) {
							// 累加
							alreadyReceiveAmount += alreadyReceiveList.get(i).getGetAmount().doubleValue();
						}
						// 返回参数
						map.put("alreadyReceiveList", alreadyReceiveList);// 领取明细集合
						map.put("alreadyReceiveAmount", alreadyReceiveAmount);// 领取总额
						map.put("repAmountTotal", pubRedpacket.getRepAmount().doubleValue());// 红包总额
						map.put("alreadyReceiveNumber", alreadyReceiveList.size());// 已领取红包数量
						map.put("repNumber", pubRedpacket.getRepNumber());// 红包总数
						// 返回领取的金额
						map.put("receiveAmount", receiveAmount);
						jsonObject.setResult(map);
						processResultStatus(jsonObject, ApiCode.HTTP_CODE_200, "领取红包成功", true);
					} else if(redStatus.equals("2")){
						processResultStatus(jsonObject, ApiCode.HTTP_CODE_702, "已领取红包", false);
					} else if(redStatus.equals("3")){
						processResultStatus(jsonObject, ApiCode.HTTP_CODE_703, "抢光了", false);
					}
				} else if (pubActivityArea.getActivityStatus().equals("2")) {
					// 抢红包已结束
					processResultStatus(jsonObject, ApiCode.HTTP_CODE_704, "活动已结束", false);
				} else if (pubActivityArea.getActivityStatus().equals("5")) {
					// 抢红包活动未开始,请同步本地时间
					processResultStatus(jsonObject, ApiCode.HTTP_CODE_705, "活动未开始,请同步本地时间", false);
				}
			}
		} catch (RedNotEnoughException e) {
			// 红包不够
			processResultStatus(jsonObject, ApiCode.HTTP_CODE_703, "抢光了", false);
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			e.printStackTrace();
		} catch (Exception e) {
			processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "领取红包失败", false);
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			e.printStackTrace();
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}

	/**
	 * 
	 * @Description : 获取红包领取明细
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @return JsonObject
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2016年3月8日 下午7:04:46
	 */
	@RequestMapping(value = "/selectReceiveRedDetail", method = RequestMethod.POST)
	@ResponseBody
	public String selectReceiveRedDetail(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		try {
			// 获取传过来的参数
			ActivityVo activity = (ActivityVo) ApiUtil.convertObjectByBody(request, ActivityVo.class);
			// 判断
			if (!StringUtils.hasLength(activity.getRedpacketId())) {
				processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "红包活动ID不能为空", false);
			} else {
				// 获取红包领取明细
				List<ActRedpActDetail> alreadyReceiveList = actRedpActDetailServiceImpl.selectReceiveRedDetail(activity.getRedpacketId());
				// 根据红包ID获取红包对象
				PubRedpacket pubRedpacket = pubRedpacketServiceImpl.getPubRedpacketByPid(activity.getRedpacketId());
				// 获取已经领取的金额
				Double alreadyReceiveAmount = 0.0;
				// 循环
				for (int i = 0; i < alreadyReceiveList.size(); i++) {
					// 累加
					alreadyReceiveAmount += alreadyReceiveList.get(i).getGetAmount().doubleValue();
				}

				// 返回参数
				map.put("alreadyReceiveList", alreadyReceiveList);// 领取明细集合
				map.put("alreadyReceiveAmount", alreadyReceiveAmount);// 领取总额
				map.put("repAmountTotal", pubRedpacket.getRepAmount().doubleValue());// 红包总额
				map.put("alreadyReceiveNumber", alreadyReceiveList.size());// 已领取红包数量
				map.put("repNumber", pubRedpacket.getRepNumber());// 红包总数
				jsonObject.setResult(map);
				processResultStatus(jsonObject, ApiCode.HTTP_CODE_200, "查询红包明细成功", true);
			}
		} catch (Exception e) {
			processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "查询红包明细失败", false);
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			e.printStackTrace();
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}

	/**
	 * 
	 * @Description : 显示红包
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @return 当前抢红包的活动
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2016年3月9日 上午9:47:30
	 */
	@RequestMapping(value = "/showRed", method = RequestMethod.POST)
	@ResponseBody
	public String showRed(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		try {
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			// 用户ID
			String userId = getCustomerId(xtoken);
			// 查询当前最新的抢红包活动
			PubActivityArea pubActivityArea = pubActivityAreaServiceImpl.selectPubActivityByType(1);
			// 查询活动对象
			ActActivity actActivity = actActivityServiceImpl.selectByPrimaryKey(pubActivityArea.getActivityId());
			// 查询抢红包活动对象
			PubRedpacket pubRedpacket = pubRedpacketServiceImpl.getPubRedpacketByPid(actActivity.getObjectId());
			// 根据当前活动,检查当前活动是处于什么状态(1.进行中 2.过期 5.未开始)
			if (pubActivityArea.getActivityStatus().equals("1")) {
				// 证明当前活动正在进行
				// 检查当前用户是否领取当前活动的红包 (1.未领取 2.已领取 3.抢光了)
				String redStatus = userId == null ? "1" : actRedpActDetailServiceImpl.checkUserReceiveRedStatus(pubRedpacket.getPid(), userId);
				// 查询出已领取的列表
				List<ActRedpActDetail> alreadyReceiveList = actRedpActDetailServiceImpl.selectReceiveRedDetail(pubRedpacket.getPid());
				// 获取当前活动参与的条件
				List<ActParConRel> listParConRel = actActivityServiceImpl.selectActParConRelByObjectId(pubRedpacket.getPid(), Constant.ACTIVITY_TYPE_2);
				if (redStatus.equals("1")) {
					// 如果是未领取
					// 算出未领取数 = 总数 - 已领取数
					int noAlreadyReceiveNumber = pubRedpacket.getRepNumber() - alreadyReceiveList.size();
					map.put("pid", pubRedpacket.getPid());// 红包ID
					map.put("noAlreadyReceiveNumber", noAlreadyReceiveNumber);// 未领取数
					map.put("repNumber", pubRedpacket.getRepNumber());// 红包总数
					map.put("listParConRel", listParConRel);// 领取条件
					jsonObject.setResult(map);
					processResultStatus(jsonObject, ApiCode.HTTP_CODE_701, "未领取", true);
				} else if (redStatus.equals("2")) {
					// 获取已经领取的金额
					Double alreadyReceiveAmount = 0.0;
					// 循环
					for (int i = 0; i < alreadyReceiveList.size(); i++) {
						// 累加
						alreadyReceiveAmount += alreadyReceiveList.get(i).getGetAmount().doubleValue();
					}
					// 查询用户领取了多少红包金额
					Double receiveAmount = actRedpActDetailServiceImpl.selectUserReceiveAmount(pubRedpacket.getPid(), userId);
					// 如果是已领取
					map.put("pid", pubRedpacket.getPid());// 红包ID
					map.put("receiveAmount", receiveAmount);// 用户领取金额
					map.put("alreadyReceiveAmount", alreadyReceiveAmount);// 领取总额
					map.put("repAmountTotal", pubRedpacket.getRepAmount().doubleValue());// 红包总额
					map.put("alreadyReceiveNumber", alreadyReceiveList.size());// 已领取数
					map.put("repNumber", pubRedpacket.getRepNumber());// 红包总数
					map.put("alreadyReceiveList", alreadyReceiveList);// 已领取明细
					jsonObject.setResult(map);
					processResultStatus(jsonObject, ApiCode.HTTP_CODE_702, "已领取", true);
				} else if (redStatus.equals("3")) {
					// 如果是抢光了
					map.put("pid", pubRedpacket.getPid());// 红包ID
					map.put("noAlreadyReceiveNumber", 0);// 未领取数
					map.put("repNumber", pubRedpacket.getRepNumber());// 红包总数
					map.put("listParConRel", listParConRel);// 领取条件
					jsonObject.setResult(map);
					processResultStatus(jsonObject, ApiCode.HTTP_CODE_703, "抢光了", true);
				}
			} else if (pubActivityArea.getActivityStatus().equals("2")) {
				map.put("pid", pubRedpacket.getPid());// 红包ID
				// 当前活动已经过期》》已结束
				jsonObject.setResult(map);
				processResultStatus(jsonObject, ApiCode.HTTP_CODE_704, "抢红包活动已结束", true);
			} else if (pubActivityArea.getActivityStatus().equals("5")) {
				map.put("pid", pubRedpacket.getPid());// 红包ID
				// 把活动开始时间传过去
				map.put("startDate", pubActivityArea.getActivityStartDate());// 活动开始时间
				jsonObject.setResult(map);
				// 当前活动未开始
				processResultStatus(jsonObject, ApiCode.HTTP_CODE_705, "抢红包活动未开始", true);
			}
		} catch (Exception e) {
			processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "显示红包失败", false);
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			e.printStackTrace();
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}

	/**
	 * 
	 * @Description : 领取加息劵
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @return JsonObject
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2016年3月10日 下午2:28:17
	 */
	@RequestMapping(value = "/receiveInterest", method = RequestMethod.POST)
	@ResponseBody
	public String receiveInterest(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		try {
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			// 用户ID
			String userId = getCustomerId(xtoken);
			// 获取传过来的参数
			ActivityVo activity = (ActivityVo) ApiUtil.convertObjectByBody(request, ActivityVo.class);
			// 判断
			if (!StringUtils.hasLength(activity.getInvestAwardId())) {
				processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "红包活动ID不能为空", false);
			} else if (!StringUtils.hasLength(userId)) {
				processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "客户id为null", false);
			} else {
				// 查询当前最新的加息劵活动
				PubActivityArea pubActivityArea = pubActivityAreaServiceImpl.selectPubActivityByType(2);
				// 查询活动对象
				ActActivity actActivity = actActivityServiceImpl.selectByPrimaryKey(pubActivityArea.getActivityId());
				// 查询加息劵活动对象
				PubInvestAward pubInvestAward = pubInvestAwardServiceImpl.getPubInvestAwardByPid(actActivity.getObjectId());
				// 检查当前用户是否领取当前活动的加息劵 (1.未领取 2.已领取 3.抢光了)
				String redStatus = actInvAwaActDetailServiceImpl.checkUserReceiveInterestStatus(activity.getInvestAwardId(), userId);
				// 查询出已领取的列表
				List<ActInvAwaActDetail> alreadyReceiveList = actInvAwaActDetailServiceImpl.selectReceiveInvestDetail(pubInvestAward.getPid());
				map.put("InvestNumber", pubInvestAward.getInterest());// 加息劵总数
				map.put("alreadyReceiveNumber", alreadyReceiveList.size());// 已领取数
				if (pubActivityArea.getActivityStatus().equals("1")) {
					if (redStatus.equals("1")) {
						// 调用领取加息劵的接口
						actInvAwaActDetailServiceImpl.receiveInterest(activity.getRedpacketId(), userId);
						map.put("validity", pubInvestAward.getValidity());// 有效期
						jsonObject.setResult(map);
						processResultStatus(jsonObject, ApiCode.HTTP_CODE_200, "领取加息劵成功", true);
					} else if (redStatus.equals("2")) {
						map.put("validity", pubInvestAward.getValidity());// 有效期
						jsonObject.setResult(map);
						// 已领取加息劵，不同重复领取
						processResultStatus(jsonObject, ApiCode.HTTP_CODE_802, "已领取加息劵", false);
					} else if (redStatus.equals("3")) {
						jsonObject.setResult(map);
						// 已经领取完了
						processResultStatus(jsonObject, ApiCode.HTTP_CODE_803, "抢光了", false);
					}
				} else if (pubActivityArea.getActivityStatus().equals("2")) {
					jsonObject.setResult(map);
					// 领取加息劵活动已结束
					processResultStatus(jsonObject, ApiCode.HTTP_CODE_804, "活动已结束", false);
				} else if (pubActivityArea.getActivityStatus().equals("5")) {
					map.put("startDate", pubActivityArea.getActivityStartDate());// 活动开始时间
					jsonObject.setResult(map);
					// 领取加息劵活动未开始,请同步本地时间
					processResultStatus(jsonObject, ApiCode.HTTP_CODE_805, "活动未开始,请同步本地时间", false);
				}

			}
		} catch (InterestNotEnoughException e) {
			// 加息劵不够
			processResultStatus(jsonObject, ApiCode.HTTP_CODE_803, "抢光了", false);
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			e.printStackTrace();
		} catch (Exception e) {
			processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "领取加息劵失败", false);
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			e.printStackTrace();
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}

	/**
	 * 
	 * @Description : 显示加息劵活动
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @return jsonObject
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2016年3月10日 下午4:26:52
	 */
	@RequestMapping(value = "/showInterest", method = RequestMethod.POST)
	@ResponseBody
	public String showInterest(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		try {
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			// 用户ID
			String userId = getCustomerId(xtoken);
			// 查询当前最新的加息劵活动
			PubActivityArea pubActivityArea = pubActivityAreaServiceImpl.selectPubActivityByType(1);
			// 查询活动对象
			ActActivity actActivity = actActivityServiceImpl.selectByPrimaryKey(pubActivityArea.getActivityId());
			// 查询加息劵活动对象
			PubInvestAward pubInvestAward = pubInvestAwardServiceImpl.getPubInvestAwardByPid(actActivity.getObjectId());
			// 根据当前活动,检查当前活动是处于什么状态(1.进行中 2.过期 5.未开始)
			if (pubActivityArea.getActivityStatus().equals("1")) {
				// 证明当前活动正在进行
				// 检查当前用户是否领取当前活动的加息劵 (1.未领取 2.已领取 3.抢光了)
				String redStatus = userId == null ? "1" : actInvAwaActDetailServiceImpl.checkUserReceiveInterestStatus(pubInvestAward.getPid(), userId);
				// 查询出已领取的列表
				List<ActInvAwaActDetail> alreadyReceiveList = actInvAwaActDetailServiceImpl.selectReceiveInvestDetail(pubInvestAward.getPid());
				// 获取当前活动参与的条件
				List<ActParConRel> listParConRel = actActivityServiceImpl.selectActParConRelByObjectId(pubInvestAward.getPid(), Constant.ACTIVITY_TYPE_4);
				map.put("pid", pubInvestAward.getPid());// 加息劵ID
				map.put("InvestValue", pubInvestAward.getInvestAwardValue().doubleValue());// 加息比例
				map.put("validity", pubInvestAward.getValidity());// 有效期
				map.put("listParConRel", listParConRel);// 领取条件
				map.put("InvestNumber", pubInvestAward.getInterest());// 加息劵总数
				if (redStatus.equals("1")) {
					// 如果是未领取
					// 算出未领取数 = 总数 - 已领取数
					int noAlreadyReceiveNumber = pubInvestAward.getValidity() - alreadyReceiveList.size();
					map.put("noAlreadyReceiveNumber", noAlreadyReceiveNumber);// 未领取数
					jsonObject.setResult(map);
					processResultStatus(jsonObject, ApiCode.HTTP_CODE_801, "未领取", true);
				} else if (redStatus.equals("2")) {
					// 如果是已领取
					map.put("alreadyReceiveNumber", alreadyReceiveList.size());// 已领取数
					jsonObject.setResult(map);
					processResultStatus(jsonObject, ApiCode.HTTP_CODE_802, "已领取", true);
				} else if (redStatus.equals("3")) {
					// 如果是抢光了
					map.put("noAlreadyReceiveNumber", 0);// 未领取数
					jsonObject.setResult(map);
					processResultStatus(jsonObject, ApiCode.HTTP_CODE_803, "抢光了", true);
				}
			} else if (pubActivityArea.getActivityStatus().equals("2")) {
				map.put("pid", pubInvestAward.getPid());// 加息劵ID
				jsonObject.setResult(map);
				// 当前活动已经过期》》已结束
				processResultStatus(jsonObject, ApiCode.HTTP_CODE_804, "加息劵活动已结束", true);
			} else if (pubActivityArea.getActivityStatus().equals("5")) {
				map.put("pid", pubInvestAward.getPid());// 加息劵ID
				// 把活动开始时间传过去
				map.put("startDate", pubActivityArea.getActivityStartDate());// 活动开始时间
				jsonObject.setResult(map);
				// 当前活动未开始
				processResultStatus(jsonObject, ApiCode.HTTP_CODE_805, "加息劵活动未开始", true);
			}
		} catch (Exception e) {
			processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "显示加息劵失败", false);
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			e.printStackTrace();
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}

	/**
	 * 
	 * @Description : 查询加息劵领取明细
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @return jsonObject
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2016年3月10日 下午4:53:21
	 */
	@RequestMapping(value = "/selectReceiveInterestDetail", method = RequestMethod.POST)
	@ResponseBody
	public String selectReceiveInterestDetail(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		try {
			// 获取传过来的参数
			ActivityVo activity = (ActivityVo) ApiUtil.convertObjectByBody(request, ActivityVo.class);
			// 判断
			if (!StringUtils.hasLength(activity.getInvestAwardId())) {
				processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "加息劵活动ID不能为空", false);
			} else {
				// 获取加息劵领取明细
				List<ActInvAwaActDetail> alreadyReceiveList = actInvAwaActDetailServiceImpl.selectReceiveInvestDetail(activity.getInvestAwardId());
				// 查询加息劵活动对象
				PubInvestAward pubInvestAward = pubInvestAwardServiceImpl.getPubInvestAwardByPid(activity.getInvestAwardId());

				// 返回参数
				map.put("alreadyReceiveList", alreadyReceiveList);// 领取明细集合
				map.put("alreadyReceiveNumber", alreadyReceiveList.size());// 已领取加息劵数量
				map.put("InvestNumber", pubInvestAward.getInterest());// 加息劵总数
				jsonObject.setResult(map);
				processResultStatus(jsonObject, ApiCode.HTTP_CODE_200, "查询加息劵明细成功", true);
			}
		} catch (Exception e) {
			processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "查询加息劵明细失败", false);
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			e.printStackTrace();
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}

}
