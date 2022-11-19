/*
 **************************************************************************
 * 版权声明：
 * 本软件为大展信息科技（深圳）有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 导入和导出的Excel的学生对象
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月23日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.test.excel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.achievo.framework.util.DateUtil;


/**
 * Description：<br> 
 * 导入和导出的Excel的学生对象
 * @author  Lin Xu
 * @date    2015年9月23日
 * @version v1.0.0
 */
public class Student implements Serializable{

	private static final long serialVersionUID = 3041137695414288274L;
	
	private int id;
	//姓名
	private String name;
	//年龄
	private Integer age;
	//性别
	private String sex;
	//生日
	private Date brith;
	private boolean status;
	//英语成绩
	private BigDecimal english;
	//数学成绩
	private BigDecimal math;
	//总成绩
	private BigDecimal tatol;
	//已交学费
	private BigDecimal doller;
	
	public Student(int id, String name, int age){
		this.id = id;
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBrith() {
		return brith;
	}
	public void setBrith(Date brith) {
		this.brith = brith;
	}
	public BigDecimal getEnglish() {
		return english;
	}
	public void setEnglish(BigDecimal english) {
		this.english = english;
	}
	public BigDecimal getMath() {
		return math;
	}
	public void setMath(BigDecimal math) {
		this.math = math;
	}
	public BigDecimal getDoller() {
		return doller;
	}
	public void setDoller(BigDecimal doller) {
		this.doller = doller;
	}
	public BigDecimal getTatol() {
		return tatol;
	}
	public void setTatol(BigDecimal tatol) {
		this.tatol = tatol;
	}
	
	
	@Override
	public String toString() {
		String tostr = "姓名: "
				+ name
				+ "	年龄: "
				+ age
				+ "	性别: "
				+ sex
				+ "	生日: "
				+ DateUtil.format(brith)
				+ "	英语: "
				+ english
				+ "	数学: "
				+ math
				+ "	总分: "
				+ tatol
				+ "	学费: "
				+ doller;
		return tostr;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}


	
}


