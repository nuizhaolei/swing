/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.vehicle.dao.merchant;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.vehicle.entity.merchant.Merchant;

/**
 * merchantDAO接口
 * @author merchant
 * @version 2020-04-05
 */
@MyBatisDao
public interface MerchantDao extends CrudDao<Merchant> {
	
}