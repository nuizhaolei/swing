/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.vehicle.entity.amt;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * amtEntity
 * @author amt
 * @version 2020-06-19
 */
public class SysAmtD extends DataEntity<SysAmtD> {
	
	private static final long serialVersionUID = 1L;
	private Date amtDate;		// 日期
	private String amtName;		// 摘要（说明）
	private Double amtInput;		// 进项
	private Double amtOutput;		// 出项
	private Double amtBalance;		// 余额
	private String remark;		// 备注
	
	public SysAmtD() {
		super();
	}

	public SysAmtD(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAmtDate() {
		return amtDate;
	}

	public void setAmtDate(Date amtDate) {
		this.amtDate = amtDate;
	}
	
	@Length(min=0, max=512, message="摘要（说明）长度必须介于 0 和 512 之间")
	public String getAmtName() {
		return amtName;
	}

	public void setAmtName(String amtName) {
		this.amtName = amtName;
	}
	
	public Double getAmtInput() {
		return amtInput;
	}

	public void setAmtInput(Double amtInput) {
		this.amtInput = amtInput;
	}
	
	public Double getAmtOutput() {
		return amtOutput;
	}

	public void setAmtOutput(Double amtOutput) {
		this.amtOutput = amtOutput;
	}
	
	public Double getAmtBalance() {
		return amtBalance;
	}

	public void setAmtBalance(Double amtBalance) {
		this.amtBalance = amtBalance;
	}
	
	@Length(min=0, max=512, message="备注长度必须介于 0 和 512 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}