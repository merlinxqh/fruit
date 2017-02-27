package com.fruit.base.framework.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * @author leo
 * 基类
 */
public class BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5557663618469589569L;
	
	private BigInteger id;
	
	private Date gmtCreate;//创建时间
	
	private Date gmtModified;//更新时间

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
	
}
