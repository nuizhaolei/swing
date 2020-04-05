/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.vehicle.service.merchant;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.vehicle.entity.merchant.Merchant;
import com.thinkgem.jeesite.modules.vehicle.dao.merchant.MerchantDao;

/**
 * merchantService
 * @author merchant
 * @version 2020-04-05
 */
@Service
@Transactional(readOnly = true)
public class MerchantService extends CrudService<MerchantDao, Merchant> {

	public Merchant get(String id) {
		return super.get(id);
	}
	
	public List<Merchant> findList(Merchant merchant) {
		return super.findList(merchant);
	}
	
	public Page<Merchant> findPage(Page<Merchant> page, Merchant merchant) {
		return super.findPage(page, merchant);
	}
	
	@Transactional(readOnly = false)
	public void save(Merchant merchant) {
		super.save(merchant);
	}
	
	@Transactional(readOnly = false)
	public void delete(Merchant merchant) {
		super.delete(merchant);
	}
	
}