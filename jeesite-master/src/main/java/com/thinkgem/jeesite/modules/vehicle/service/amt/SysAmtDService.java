/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.vehicle.service.amt;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.vehicle.entity.amt.SysAmtD;
import com.thinkgem.jeesite.modules.vehicle.dao.amt.SysAmtDDao;

/**
 * amtService
 * @author amt
 * @version 2020-06-19
 */
@Service
@Transactional(readOnly = true)
public class SysAmtDService extends CrudService<SysAmtDDao, SysAmtD> {

	public SysAmtD get(String id) {
		return super.get(id);
	}
	
	public List<SysAmtD> findList(SysAmtD sysAmtD) {
		return super.findList(sysAmtD);
	}
	
	public Page<SysAmtD> findPage(Page<SysAmtD> page, SysAmtD sysAmtD) {
		return super.findPage(page, sysAmtD);
	}
	
	@Transactional(readOnly = false)
	public void save(SysAmtD sysAmtD) {
		super.save(sysAmtD);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysAmtD sysAmtD) {
		super.delete(sysAmtD);
	}
	
}