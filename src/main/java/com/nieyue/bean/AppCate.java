package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 应用类型
 * @author yy
 *
 */
public class AppCate implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 应用类型id
	 */
	private Integer appCateId;
	
	/**
	 * 应用类型名
	 */
	private String name;
	/**
	 * 更新时间
	 */
	private Date updateDate;

	public AppCate(Integer appCateId, String name, String duty, Date updateDate) {
		super();
		this.appCateId = appCateId;
		this.name = name;
		this.updateDate = updateDate;
	}
	public AppCate() {
		super();
	}
	public Integer getAppCateId() {
		return appCateId;
	}
	public void setAppCateId(Integer appCateId) {
		this.appCateId = appCateId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
