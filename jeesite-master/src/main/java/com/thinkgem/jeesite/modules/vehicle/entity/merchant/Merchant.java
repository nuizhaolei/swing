/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.vehicle.entity.merchant;

import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * merchantEntity
 * @author merchant
 * @version 2020-04-05
 */
public class Merchant extends DataEntity<Merchant> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// name
	private String address;		// address
	private String telephone;		// telephone
	private String remark;		// remark
	private String linkName; //联系人
	
	public Merchant() {
		super();
	}

	public Merchant(String id){
		super(id);
	}

	@Length(min=1, max=256, message="name长度必须介于 1 和 256 之间")
	@ExcelField(title="商家名称", align=2, sort=20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=256, message="address长度必须介于 0 和 256 之间")
	@ExcelField(title="地址", align=2, sort=25)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=32, message="telephone长度必须介于 0 和 32 之间")
	@ExcelField(title="联系电话", align=2, sort=35)
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	@Length(min=0, max=512, message="remark长度必须介于 0 和 512 之间")
	@ExcelField(title="备注", align=2, sort=40)
	public String getRemark() {
		return remark;
	}

	@Length(min=0, max=64, message="联系人长度必须介于 0 和 64 之间")
	@ExcelField(title="联系人", align=2, sort=30)
	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}