/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 自动投标-后台自动投资线程信息
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年12月19日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.thread;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.achievo.framework.util.DateUtil;
import com.achievo.framework.util.StringUtil;
import com.yscf.core.dao.ptp.autobidding.AutoBiddingQueueMapper;
import com.yscf.core.dao.ptp.autobidding.AutoBiddingQueueRemarksMapper;
import com.yscf.core.model.business.BizBorrow;
import com.yscf.core.model.business.BizBorrowDetail;
import com.yscf.core.model.ptp.autobidding.AutoBiddPersonModel;
import com.yscf.core.model.ptp.autobidding.AutoBiddingQueue;
import com.yscf.core.model.ptp.autobidding.AutoBiddingQueueRemarks;
import com.yscf.core.model.system.SysLog;
import com.yscf.core.service.autobidding.impl.AutoBiddingProcessService;
import com.yscf.core.service.business.impl.BizBorrowDetailServiceImpl;
import com.yscf.core.service.business.impl.BizBorrowServiceImpl;
import com.yscf.core.service.system.impl.SysLogServiceImpl;

/**
 * Description：<br>
 * 自动投标-后台自动投资线程信息
 * 
 * @author Lin Xu
 * @date 2015年12月19日
 * @version v1.0.0
 */
public class AutoBiddingBackThread extends Thread {
	// 处理日志对象
	private Logger logger = Logger.getLogger(AutoBiddingProcessService.class);

	// 借款id
	private String bspid;
	// 投标服务服务层
	private BizBorrowServiceImpl bizBorrowService;

	// 查询自动投标队列信息
	private AutoBiddingQueueMapper autoBiddingQueueMapper;

	// 自动投标的信息投资结果处理
	private AutoBiddingQueueRemarksMapper abqremarkMapper;

	// 每个人进行具体投资服务类 --目前需要添加
	private BizBorrowDetailServiceImpl bizBDetailServiceImpl;

	// 日志处理服务类
	private SysLogServiceImpl sysLogServiceImpl;

	/**
	 * Description：<br>
	 * 带参数的构造器信息
	 * 
	 * @author Lin Xu
	 * @date 2015年12月19日
	 * @version v1.0.0
	 * @param borwid
	 * @param bbService
	 * @param abqMapper
	 * @param abqmaskMapper
	 * @param sysLogService
	 */
	public AutoBiddingBackThread(String borwid, BizBorrowServiceImpl bbService,
			AutoBiddingQueueMapper abqMapper,
			BizBorrowDetailServiceImpl bizbDetailServiceImpl,
			AutoBiddingQueueRemarksMapper abqmaskMapper,
			SysLogServiceImpl sysLogService) {
		this.bspid = borwid;
		this.bizBorrowService = bbService;
		this.bizBDetailServiceImpl = bizbDetailServiceImpl;
		this.autoBiddingQueueMapper = abqMapper;
		this.abqremarkMapper = abqmaskMapper;
		this.sysLogServiceImpl = sysLogService;
	}

	/**
	 * 线程入口
	 */
	@Override
	public void run() {
		try {
			processAutoBidding();
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("自动投标整体异常：" + e.getMessage());
			}
		}
	}

	/**
	 * 通过投标的id进行自动投标
	 */
	private synchronized void processAutoBidding() {
		try {
			if (StringUtil.isNotEmpty(bspid)) {
				// 创建日志对象信息
				SysLog sysLog = new SysLog();
				sysLog.setPid(sysLog.getPK());
				sysLog.setName("自动投标");
				sysLog.setAccountNo("编号：" + bspid);
				sysLog.setCreateDate(DateUtil.format(new Date()));
				sysLog.setCreateTime(new Date());
				sysLog.setCreateUser("system");
				sysLog.setSystemType("2");
				sysLog.setLoginIp("127.0.0.1");
				sysLog.setStatus("1");
				// 通过借款的id查询借款信息
				BizBorrow bizbmodel = bizBorrowService.getBizBorrowById(bspid);
				if (null != bizbmodel && null != bizbmodel.getBorrowMoney()) {
					HashMap<String, Object> qmap = new HashMap<String, Object>();
					if (StringUtil.isNotEmpty(bizbmodel.getIsTimesInvest())) {
						qmap.put("isTimesInvest", bizbmodel.getIsTimesInvest());
					}
					if (null != bizbmodel.getStartMoney()) {
						BigDecimal startbig = bizbmodel.getStartMoney();
						if (startbig.compareTo(BigDecimal.ZERO) == 1) {
							qmap.put("startMoney", startbig);
						} else {
							qmap.put("startMoney", 0);
						}
					}
					if (null != bizbmodel.getEndMoney()) {
						BigDecimal endbig = bizbmodel.getEndMoney();
						if (endbig.compareTo(BigDecimal.ZERO) == 1) {
							qmap.put("endMoney", endbig);
						} else {
							qmap.put("endMoney", 0);
						}
					}
					if (StringUtil.isNotEmpty(bizbmodel.getDeadline())) {
						qmap.put("deadline", bizbmodel.getDeadline());
					}
					if (null != bizbmodel.getBorrowApr()) {
						BigDecimal borbig = bizbmodel.getBorrowRate();
						if (borbig.compareTo(BigDecimal.ZERO) == 1) {
							borbig = borbig.multiply(new BigDecimal(100));
							qmap.put("borrowApr", borbig);
						}
					}
					if (StringUtil.isNotEmpty(bizbmodel.getBorrowType())) {
						qmap.put("borrowType", bizbmodel.getBorrowType());
					}

					// 查询队列
					List<AutoBiddPersonModel> insertlist = autoBiddingQueueMapper
							.selectInsertQeneList(qmap);
					if (null != insertlist && insertlist.size() > 0) {
						// 获取总投资额
						BigDecimal totalborrowMoney = bizbmodel
								.getBorrowMoney();
						// 进行分发投资信息
						for (AutoBiddPersonModel abpm : insertlist) {
							// 当总投资金额大于可投资金额
							if (totalborrowMoney.compareTo(abpm.getAutomoney()) == 1
									|| totalborrowMoney.compareTo(abpm
											.getAutomoney()) == 0) {
								BizBorrowDetail borrowDetail = new BizBorrowDetail();
								borrowDetail.setBorrowId(bspid);
								borrowDetail.setInvestmentAmount(abpm
										.getAutomoney());
								// 1.进行投资并返回Hashmap数据 --需要冯仕良提供
								HashMap<String, Object> inmap = bizBDetailServiceImpl
										.investmentAuto(borrowDetail,
												abpm.getCustomerId(), "2");
								boolean bool = false;
								String remsg = "操作返回异常";
								if (null != inmap) {
									bool = (Boolean) inmap.get("result");
									remsg = (String) inmap.get("msg");
								}
								// 2.自动投标成功 扣减总金额
								if (bool) {
									totalborrowMoney = totalborrowMoney
											.subtract(abpm.getAutomoney());
									// 投资成功后重新排队
									AutoBiddingQueue newabque = new AutoBiddingQueue();
									newabque.setPid(abpm.getPid());
									newabque.setCustomerId(abpm.getCustomerId());
									newabque.setAutoBiddingId(abpm.getAutoPid());
									newabque.setQueuetime(DateUtil
											.format(new Date()));
									autoBiddingQueueMapper
											.updateByPrimaryKeySelective(newabque);
								}

								// 3.进行会写日志处理
								insertEndLog(bspid, bool, remsg, abpm);
								continue;

								// 当总投资金额小于可投资金额
							} else {
								// 按比例投资时候可以按照剩余的投资金额进行投资
								if ("0".equals(abpm.getAutomoneyType())) {
									BizBorrowDetail borrowDetail = new BizBorrowDetail();
									borrowDetail.setBorrowId(bspid);
									borrowDetail.setInvestmentAmount(abpm
											.getAutomoney());
									// 1.进行投资并返回Hashmap数据 --需要冯仕良提供
									HashMap<String, Object> einmap = bizBDetailServiceImpl
											.investmentAuto(borrowDetail,
													abpm.getCustomerId(), "2");
									boolean ebool = false;
									String eremsg = "操作返回异常";
									if (null != einmap) {
										ebool = (Boolean) einmap.get("result");
										eremsg = (String) einmap.get("msg");
									}

									// 2.自动投标成功 扣减总金额
									if (ebool) {
										// 投资成功后重新排队
										AutoBiddingQueue enewabque = new AutoBiddingQueue();
										enewabque.setPid(abpm.getPid());
										enewabque.setCustomerId(abpm
												.getCustomerId());
										enewabque.setAutoBiddingId(abpm
												.getAutoPid());
										enewabque.setQueuetime(DateUtil
												.format(new Date()));
										autoBiddingQueueMapper
												.updateByPrimaryKeySelective(enewabque);
									}
									// 3.进行会写日志处理
									insertEndLog(bspid, ebool, eremsg, abpm);
								}
								// 结束自动投标流结束
								break;
							}
						}
					} else {
						sysLog.setOperContent("标号：" + bizbmodel.getBorrowCode()
								+ ",没有满足的自动投标人");
						sysLogServiceImpl.insert(sysLog);
					}
				} else {
					sysLog.setOperContent("借款投标信息不存在");
					sysLogServiceImpl.insert(sysLog);
				}
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("失效自动投标中已启动的投标：" + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	/**
	 * Description：<br>
	 * 对自动投标进行日志最后处理
	 * 
	 * @author Lin Xu
	 * @date 2015年12月19日
	 * @version v1.0.0
	 * @param bspid
	 * @param bool
	 * @param remsg
	 * @param abpm
	 */
	private void insertEndLog(String bspid, boolean bool, String remsg,
			AutoBiddPersonModel abpm) {
		// 3.进行会写日志处理
		AutoBiddingQueueRemarks abqr = new AutoBiddingQueueRemarks();
		abqr.setPid(abqr.getPK());
		abqr.setCustomerId(abpm.getCustomerId());
		abqr.setAutoBiddingId(abpm.getAutoPid());
		abqr.setBorrowId(bspid);
		abqr.setAutoResultStatus(bool == true ? 1 : 2);
		abqr.setDescription(remsg);
		abqr.setCreateUser(abpm.getCustomerId());
		abqr.setCreateTime(DateUtil.format(new Date()));
		abqr.setLastUpdateUser(abpm.getCustomerId());
		abqr.setLastUpdateTime(DateUtil.format(new Date()));
		abqr.setStatus("1");
		abqremarkMapper.insertSelective(abqr);
	}

}
