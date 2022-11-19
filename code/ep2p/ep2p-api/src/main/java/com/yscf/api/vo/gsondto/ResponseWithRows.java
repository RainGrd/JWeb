/*
 ********************************************************************************
 * 版权声明：
 * 本软件为深圳高速工程顾问有限公司开发研制。未经本公司正式书面同意，其它任何
 * 个人、团体不得使用、复制、修改和发布本软件。
 ********************************************************************************
 * 程序描述：
 * TODO
 *
 ********************************************************************************
 * 修改历史：
 * Date：                           by：                                       Reason：
 * 2015年5月27日                          chengui                                    Initial Version
 * 
 ********************************************************************************
 */
package com.yscf.api.vo.gsondto;

import java.io.Serializable;
import java.util.List;

/**
 * Description: <br>
 * TODO
 * @author  chengui
 * @date 	 2015年5月27日
 * @version v1.0.0
 * @since 	 1.6
 */
public class ResponseWithRows implements Serializable {
	
	private static final long serialVersionUID = 192328255089640936L;
	
	private int code;
	private String message;
	
	private int count;
	private List<?> rows;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
}