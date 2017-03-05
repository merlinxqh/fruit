package com.fruit.growup.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * 
 * @author 
 * @date 2017-02-28 21:41:03
 * @version 1.0.0
 * @copyright www.net.com
 */
public class GrowUpPlanDto implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private BigInteger id;

    /**
     * code.
     */
    private String code;

    /**
     * 计划时间 : 
.
     */
    private Date planDate;

    /**
     * 计划事情.
     */
    private String planThings;

    /**
     * 状态.
     */
    private Integer status;

    /**
     * 创建时间 : 

.
     */
    private Date gmtCreate;

    /**
     * 修改时间.
     */
    private Date gmtModified;
    
    public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	/**
     * 
     * {@linkplain #code}
     *
     * @return the value of grow_up_plan.code
     */
    public String getCode() {
        return code;
    }

    /**
     * {@linkplain #code}
     * @param code the value for grow_up_plan.code
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 
     * {@linkplain #planDate}
     *
     * @return the value of grow_up_plan.plan_date
     */
    public Date getPlanDate() {
        return planDate;
    }

    /**
     * {@linkplain #planDate}
     * @param planDate the value for grow_up_plan.plan_date
     */
    public void setPlanDate(Date planDate) {
        this.planDate = planDate;
    }

    /**
     * 
     * {@linkplain #planThings}
     *
     * @return the value of grow_up_plan.plan_things
     */
    public String getPlanThings() {
        return planThings;
    }

    /**
     * {@linkplain #planThings}
     * @param planThings the value for grow_up_plan.plan_things
     */
    public void setPlanThings(String planThings) {
        this.planThings = planThings == null ? null : planThings.trim();
    }

    /**
     * 
     * {@linkplain #status}
     *
     * @return the value of grow_up_plan.status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * {@linkplain #status}
     * @param status the value for grow_up_plan.status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 
     * {@linkplain #gmtCreate}
     *
     * @return the value of grow_up_plan.gmt_create
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * {@linkplain #gmtCreate}
     * @param gmtCreate the value for grow_up_plan.gmt_create
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * 
     * {@linkplain #gmtModified}
     *
     * @return the value of grow_up_plan.gmt_modified
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * {@linkplain #gmtModified}
     * @param gmtModified the value for grow_up_plan.gmt_modified
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}