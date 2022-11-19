/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 社交软件统计model
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年11月2日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.model.radio;

import com.achievo.framework.entity.BaseEntity;

/**
 * Description：<br> 
 * 社交软件统计model
 * @author  Lin Xu
 * @date    2015年11月2日
 * @version v1.0.0
 */
public class SocalAppModel extends BaseEntity{

	private static final long serialVersionUID = -6010893538297607916L;
	
	//名称
	private String name;
	//y轴信息
	private Integer y;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}
	
}


