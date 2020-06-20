/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.vehicle.dao.amt;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.vehicle.entity.amt.SysAmtD;

/**
 * amtDAO接口
 * @author amt
 * @version 2020-06-19
 */
@MyBatisDao
public interface SysAmtDDao extends CrudDao<SysAmtD> {
	
}