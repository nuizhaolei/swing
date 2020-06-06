/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.vehicle.entity.vehicle;

import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 车辆管理
 * vehicleEntity
 * @author vehicle
 * @version 2020-03-31
 */
public class Vehicle extends DataEntity<Vehicle> {
	
	private static final long serialVersionUID = 1L;

	private String name;		// 姓名

	private String telephone;		// 联系电话

	private String call;		// 手机号

	private String vehicleType;		// 车型

	private String vehicleColor;		// 颜色

	private String vehicleCode;		// 车牌号码

	private String isLimitLine;		// 是否限行

	private String remark;		// 备注

	private String nh001;		// nh_001

	private String nh002;		// nh_002

	private Date createTime;		// 时间

	private Integer price;		// price

	private User user;

	private String search;
	
	public Vehicle() {
		super();
	}

	public Vehicle(String id){
		super(id);
	}

	@Length(min=1, max=128, message="姓名长度必须介于 1 和 128 之间")
	@ExcelField(title="姓名", align=2, sort=20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=20, message="联系电话长度必须介于 0 和 20 之间")
	@ExcelField(title="联系电话", align=2, sort=25)
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	@Length(min=0, max=20, message="手机号长度必须介于 0 和 20 之间")
	@ExcelField(title="手机号", align=2, sort=30)
	public String getCall() {
		return call;
	}

	public void setCall(String call) {
		this.call = call;
	}
	
	@Length(min=0, max=64, message="车型长度必须介于 0 和 64 之间")
	@ExcelField(title="车型", align=2, sort=35)
	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	
	@Length(min=0, max=64, message="颜色长度必须介于 0 和 64 之间")
	@ExcelField(title="颜色", align=2, sort=40)
	public String getVehicleColor() {
		return vehicleColor;
	}

	public void setVehicleColor(String vehicleColor) {
		this.vehicleColor = vehicleColor;
	}
	
	@Length(min=0, max=32, message="车牌号码长度必须介于 0 和 32 之间")
	@ExcelField(title=" 车牌号码", align=2, sort=45)
	public String getVehicleCode() {
		return vehicleCode;
	}

	public void setVehicleCode(String vehicleCode) {
		this.vehicleCode = vehicleCode;
	}
	
	@Length(min=0, max=128, message="是否限行长度必须介于 0 和 128 之间")
	@ExcelField(title="是否限行", align=2, sort=50)
	public String getIsLimitLine() {
		return isLimitLine;
	}

	public void setIsLimitLine(String isLimitLine) {
		this.isLimitLine = isLimitLine;
	}
	
	@Length(min=0, max=256, message="备注长度必须介于 0 和 256 之间")
	@ExcelField(title="备注", align=2, sort=55)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Length(min=0, max=256, message="nh_001长度必须介于 0 和 256 之间")
	public String getNh001() {
		return nh001;
	}

	public void setNh001(String nh001) {
		this.nh001 = nh001;
	}
	
	@Length(min=0, max=256, message="nh_002长度必须介于 0 和 256 之间")
	public String getNh002() {
		return nh002;
	}

	public void setNh002(String nh002) {
		this.nh002 = nh002;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
}