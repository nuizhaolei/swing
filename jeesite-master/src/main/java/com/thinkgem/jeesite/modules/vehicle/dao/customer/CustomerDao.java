/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.vehicle.dao.customer;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.vehicle.entity.customer.Customer;

/**
 * customerDAO接口
 * @author customer
 * @version 2020-04-04
 */
@MyBatisDao
public interface CustomerDao extends CrudDao<Customer> {
	
}