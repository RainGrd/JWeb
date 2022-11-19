/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 安全保障介绍控制层
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年11月12日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.ep2p.controller.securtinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yscf.core.model.content.ColumnContent;
import com.yscf.core.service.content.impl.ColumnContentServiceImpl;
import com.yscf.ep2p.controller.base.EscfBaseWebController;

/**
 * Description：<br> 
 * 安全保障介绍控制层
 * @author  Lin Xu
 * @date    2015年11月12日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/security/securityController")
public class SecurityController extends EscfBaseWebController {

	@Autowired
	public SecurityController(ColumnContentServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return ColumnContent.class;
	}
	
	
	
}


