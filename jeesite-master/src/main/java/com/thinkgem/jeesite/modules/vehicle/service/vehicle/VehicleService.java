/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.vehicle.service.vehicle;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.vehicle.entity.vehicle.Vehicle;
import com.thinkgem.jeesite.modules.vehicle.dao.vehicle.VehicleDao;

/**
 * vehicleService
 * @author vehicle
 * @version 2020-03-31
 */
@Service
@Transactional(readOnly = true)
public class VehicleService extends CrudService<VehicleDao, Vehicle> {

	public Vehicle get(String id) {
		return super.get(id);
	}
	
	public List<Vehicle> findList(Vehicle vehicle) {
		return super.findList(vehicle);
	}
	
	public Page<Vehicle> findPage(Page<Vehicle> page, Vehicle vehicle) {
		return super.findPage(page, vehicle);
	}
	
	@Transactional(readOnly = false)
	public void save(Vehicle vehicle) {
		super.save(vehicle);
	}
	
	@Transactional(readOnly = false)
	public void delete(Vehicle vehicle) {
		super.delete(vehicle);
	}
	
}