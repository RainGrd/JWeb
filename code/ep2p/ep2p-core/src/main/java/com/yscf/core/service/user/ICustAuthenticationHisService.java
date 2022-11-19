/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 用户管理服务接口
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月6日     Simon.Hoo		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.user;

import java.util.List;

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.pub.PubFile;
import com.yscf.core.model.user.CustAuthenticationHis;

/**
 * 
 * @ClassName : ICustAuthenticationHisService
 * @Description : 实名认证业务接口
 * @Author : Qing.Cai
 * @Date : 2015年10月26日 下午4:09:07
 */
public interface ICustAuthenticationHisService {

	/**
	 * 
	 * @Description : 实名认证列表,带分页
	 * @param custAuthenticationHis
	 *            实名认证对象
	 * @param pageInfo
	 *            分页对象
	 * @return 当年实名认证列表
	 * @Author : Qing.Cai
	 * @Date : 2015年10月24日 下午6:47:04
	 */
	PageList<CustAuthenticationHis> selectAllPage(CustAuthenticationHis custAuthenticationHis, PageInfo pageInfo);

	/**
	 * 
	 * @Description : 新增实名认证历史记录
	 * @param custAuthenticationHis
	 *            实名认证对象
	 * @Author : Qing.Cai
	 * @Date : 2015年10月26日 上午2:28:11
	 */
	public void saveCustAuthenticationHis(CustAuthenticationHis custAuthenticationHis) throws FrameworkException;

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
	 * @Date : 2016年1月25日 下午4:40:35
	 */
	public void realNameAuthenticationMainland(String userId, String sname, String identificationNo);

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
	 * @Author : Qing.Cai
	 * @Date : 2016年1月25日 下午4:42:00
	 */
	public void realNameAuthenticationNonMainland(String userId, String sname, String identificationNo, List<PubFile> list);
}
