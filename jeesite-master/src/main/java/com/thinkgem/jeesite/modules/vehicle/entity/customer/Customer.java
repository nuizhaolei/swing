/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.vehicle.entity.customer;

import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.vehicle.entity.common.Gbt22602007;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * customerEntity
 *
 * @author customer
 * @version 2020-04-04
 */
public class Customer extends DataEntity<Customer> {

    private static final long serialVersionUID = 1L;
    private String name;        // 姓名
    private Date marriageDate;        // 婚期
    private Long count;        // 数量
    private String totalPrice;        // 总价
    private String distance;        // 距离
    private String carList;        // 车队组合
    private String remark;        // 备注
    private Date createTime;        // 创建时间
    private String line;    //路线
    private String telephone;   //电话
    private User user;
    private Gbt22602007 gbt22602007;

    public Customer() {
        super();
    }

    public Customer(String id) {
        super(id);
    }

    @Length(min = 1, max = 64, message = "姓名长度必须介于 1 和 64 之间")
    @ExcelField(title="姓名", align=2, sort=20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @ExcelField(title="数量", align=2, sort=40)
    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
    @ExcelField(title="总价", align=2, sort=55)
    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
    @ExcelField(title="公里计算", align=2, sort=50)
    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    @Length(min = 0, max = 256, message = "车队组合长度必须介于 0 和 256 之间")
    @ExcelField(title="车队组合", align=2, sort=35)
    public String getCarList() {
        return carList;
    }

    public void setCarList(String carList) {
        this.carList = carList;
    }

    @Length(min = 0, max = 256, message = "备注长度必须介于 0 和 256 之间")
    @ExcelField(title="备注", align=2, sort=60)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setMarriageDate(Date marriageDate) {
        this.marriageDate = marriageDate;
    }

    @ExcelField(title="路线", align=2, sort=45)
    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }
    @ExcelField(title="电话", align=2, sort=30)
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "创建时间不能为空")
    @ExcelField(title="婚期", align=2, sort=25)
    public Date getMarriageDate() {
        return marriageDate;
    }

    public Gbt22602007 getGbt22602007() {
        return gbt22602007;
    }

    public void setGbt22602007(Gbt22602007 gbt22602007) {
        this.gbt22602007 = gbt22602007;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}