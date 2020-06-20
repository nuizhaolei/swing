/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.vehicle.web.amt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.vehicle.entity.amt.SysAmtD;
import com.thinkgem.jeesite.modules.vehicle.service.amt.SysAmtDService;

/**
 * amtController
 * @author amt
 * @version 2020-06-19
 */
@Controller
@RequestMapping(value = "${adminPath}/vehicle/amt/sysAmtD")
public class SysAmtDController extends BaseController {

	@Autowired
	private SysAmtDService sysAmtDService;
	
	@ModelAttribute
	public SysAmtD get(@RequestParam(required=false) String id) {
		SysAmtD entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysAmtDService.get(id);
		}
		if (entity == null){
			entity = new SysAmtD();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(SysAmtD sysAmtD, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SysAmtD> page = sysAmtDService.findPage(new Page<SysAmtD>(request, response), sysAmtD); 
		model.addAttribute("page", page);
		return "modules/vehicle/amt/sysAmtDList";
	}

	@RequestMapping(value = "form")
	public String form(SysAmtD sysAmtD, Model model) {
		model.addAttribute("sysAmtD", sysAmtD);
		return "modules/vehicle/amt/sysAmtDForm";
	}

	@RequestMapping(value = "save")
	public String save(SysAmtD sysAmtD, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysAmtD)){
			return form(sysAmtD, model);
		}
		sysAmtDService.save(sysAmtD);
		addMessage(redirectAttributes, "保存账目明细成功");
		return "redirect:"+Global.getAdminPath()+"/vehicle/amt/sysAmtD/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(SysAmtD sysAmtD, RedirectAttributes redirectAttributes) {
		sysAmtDService.delete(sysAmtD);
		addMessage(redirectAttributes, "删除账目明细成功");
		return "redirect:"+Global.getAdminPath()+"/vehicle/amt/sysAmtD/?repage";
	}

}