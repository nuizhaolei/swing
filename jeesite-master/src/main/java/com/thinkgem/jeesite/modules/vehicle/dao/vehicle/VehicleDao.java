/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.vehicle.dao.vehicle;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.vehicle.entity.vehicle.Vehicle;

import java.util.List;

/**
 * vehicleDAO接口
 * @author vehicle
 * @version 2020-03-31
 */
@MyBatisDao
public interface VehicleDao extends CrudDao<Vehicle> {

    List<Vehicle> findVehicleByName(Vehicle vehicle);
	
}