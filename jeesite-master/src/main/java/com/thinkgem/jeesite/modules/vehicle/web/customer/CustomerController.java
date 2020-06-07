/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.vehicle.web.customer;

import com.thinkgem.jeesite.common.beanvalidator.BeanValidators;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.security.SystemAuthorizingRealm;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.vehicle.entity.customer.Customer;
import com.thinkgem.jeesite.modules.vehicle.service.customer.CustomerService;
import com.thinkgem.jeesite.modules.vehicle.web.common.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.util.List;

/**
 * 客户管理
 * customerController
 *
 * @author customer
 * @version 2020-04-04
 */
@Controller
@RequestMapping(value = "${adminPath}/vehicle/customer/customer")
public class CustomerController extends BaseController {

    @Autowired
    private CustomerService customerService;

    @ModelAttribute
    public Customer get(@RequestParam(required = false) String id) {
        Customer entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = customerService.get(id);
        }
        if (entity == null) {
            entity = new Customer();
        }
        return entity;
    }

    @RequestMapping(value = {"list", ""})
    public String list(Customer customer, HttpServletRequest request, HttpServletResponse response, Model model) {
        customer.setUser(CommonUtil.getUser());
        Page<Customer> page = customerService.findPage(new Page<Customer>(request, response), customer);
        model.addAttribute("page", page);
        return "modules/vehicle/customer/customerList";
    }

    @RequestMapping(value = "form")
    public String form(Customer customer, Model model) {
        model.addAttribute("customer", customer);
        return "modules/vehicle/customer/customerForm";
    }

    @RequestMapping(value = "save")
    public String save(Customer customer, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, customer)) {
            return form(customer, model);
        }
        if (CommonUtil.checkTel(customer.getTelephone())) {
            model.addAttribute("customer", customer);
            addMessage(redirectAttributes, "保存客户信息失败，电话号不符合要求，请检查！");
            return "redirect:" + Global.getAdminPath() + "/vehicle/customer/customer/form?repage";
        }
        customer.setUser(CommonUtil.getUser());
        customerService.save(customer);
        addMessage(redirectAttributes, "保存客户成功");
        return "redirect:" + Global.getAdminPath() + "/vehicle/customer/customer/?repage";
    }

    @RequestMapping(value = "delete")
    public String delete(Customer customer, RedirectAttributes redirectAttributes) {
        customerService.delete(customer);
        addMessage(redirectAttributes, "删除客户成功");
        return "redirect:" + Global.getAdminPath() + "/vehicle/customer/customer/?repage";
    }

    @RequestMapping(value = "export", method = RequestMethod.POST)
    public String export(Customer customer, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            String fileName = "客户列表" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
            Page<Customer> page = customerService.findPage(new Page<Customer>(request, response), customer);
            new ExportExcel("客户列表", Customer.class).setDataList(page.getList()).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            addMessage(redirectAttributes, "导出数据！失败信息：" + e.getMessage());
        }
        return "redirect:" + adminPath + "/vehicle/customer/customer?repage";
    }

    @RequestMapping(value = "import", method = RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
        try {
            int successNum = 0;
            int failureNum = 0;
            StringBuilder failureMsg = new StringBuilder();
            ImportExcel ei = new ImportExcel(file, 1, 0);
            List<Customer> list = ei.getDataList(Customer.class);
            BigDecimal bigDecimal = null;
            SystemAuthorizingRealm.Principal principal = UserUtils.getPrincipal();
            for (Customer customer : list) {
                try {
                    if (customer.getName() != null && customer.getName().trim().length() > 0) {

                        if (!customer.getTelephone().contains("（") && !customer.getTelephone().contains("-")) {
                            bigDecimal = new BigDecimal(customer.getTelephone());
                            customer.setTelephone(bigDecimal.toPlainString());
                        }
                        customer.setUser(new User(principal.getId()));
                        customerService.save(customer);
                        successNum++;
                    } else {
                        failureMsg.append("<br/>姓名" + customer.getName() + " 导入失败; ");
                        failureNum++;
                    }
                } catch (ConstraintViolationException ex) {
                    ex.printStackTrace();
                    failureMsg.append("<br/>姓名 " + customer.getName() + " 导入失败：");
                    List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
                    for (String message : messageList) {
                        failureMsg.append(message + "; ");
                        failureNum++;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    failureMsg.append("<br/>姓名 " + customer.getName() + " 导入失败：" + ex.getMessage());
                }
            }
            if (failureNum > 0) {
                failureMsg.insert(0, "，失败 " + failureNum + " 条，导入信息如下：");
            }
            addMessage(redirectAttributes, "已成功导入 " + successNum + " 条用户" + failureMsg);
        } catch (Exception e) {
            addMessage(redirectAttributes, "导入失败！失败信息：" + e.getMessage());
        }
        return "modules/vehicle/customer/customerList";
    }

}