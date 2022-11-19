package com.yscf.system.vo;

/**
 * Description：返回值封装类
 * @author  JingYu.Dai
 * @date    2015年9月9日
 * @version v1.0.0
 * @param <T>
 */
public class JsonResultModel<T> {
	
	private int code; //状态码
	private String msg; //状态消息
	private T data;	//数据对象
	private boolean success; //是否成功
	
	public JsonResultModel(){}

	public JsonResultModel(boolean success) {
		this.success = success;
	}
	
	public JsonResultModel(T data) {
		if(null != data){
			this.data =  data;
			this.success = true;
		}
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
}
