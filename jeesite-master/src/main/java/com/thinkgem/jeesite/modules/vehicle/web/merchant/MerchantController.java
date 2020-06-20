/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.vehicle.web.merchant;

import com.thinkgem.jeesite.common.beanvalidator.BeanValidators;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.vehicle.entity.merchant.Merchant;
import com.thinkgem.jeesite.modules.vehicle.service.merchant.MerchantService;
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
 * 商家管理
 * <p>
 * merchantController
 *
 * @author merchant
 * @version 2020-04-05
 */
@Controller
@RequestMapping(value = "${adminPath}/vehicle/merchant/merchant")
public class MerchantController extends BaseController {

    @Autowired
    private MerchantService merchantService;

    @ModelAttribute
    public Merchant get(@RequestParam(required = false) String id) {
        Merchant entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = merchantService.get(id);
        }
        if (entity == null) {
            entity = new Merchant();
        }
        return entity;
    }

    @RequestMapping(value = {"list", ""})
    public String list(Merchant merchant, HttpServletRequest request, HttpServletResponse response, Model model) {
        merchant.setUser(CommonUtil.getUser());
        Page<Merchant> page = merchantService.findPage(new Page<Merchant>(request, response), merchant);
        model.addAttribute("page", page);
        return "modules/vehicle/merchant/merchantList";
    }

    @RequestMapping(value = "form")
    public String form(Merchant merchant, Model model) {
        model.addAttribute("merchant", merchant);
        return "modules/vehicle/merchant/merchantForm";
    }

    @RequestMapping(value = "save")
    public String save(Merchant merchant, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, merchant)) {
            return form(merchant, model);
        }
        if (CommonUtil.checkTel(merchant.getTelephone())) {
            model.addAttribute("customer", merchant);
            addMessage(redirectAttributes, "保存信息失败，电话号不符合要求，请检查！");
            return "redirect:" + Global.getAdminPath() + "/vehicle/merchant/merchant?repage";
        }
        merchant.setUser(CommonUtil.getUser());
        if (merchant.getId() == null && isDuplicate(merchant)) {
            addMessage(redirectAttributes, "保存失败，该信息已经加入！");
            return "redirect:" + Global.getAdminPath() + "/vehicle/merchant/merchant?repage";
        }
        merchantService.save(merchant);
        addMessage(redirectAttributes, "保存商家成功");
        return "redirect:" + Global.getAdminPath() + "/vehicle/merchant/merchant?repage";
    }

    @RequestMapping(value = "delete")
    public String delete(Merchant merchant, RedirectAttributes redirectAttributes) {
        merchant.setUser(CommonUtil.getUser());
        merchantService.delete(merchant);
        addMessage(redirectAttributes, "删除商家成功！");
        return "redirect:" + Global.getAdminPath() + "/vehicle/merchant/merchant?repage";
    }

    @RequestMapping(value = "export", method = RequestMethod.POST)
    public String export(Merchant merchant, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            String fileName = "商家明细表" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
            List<Merchant> list = merchantService.findList(merchant);
            new ExportExcel("商家明细表", Merchant.class).setDataList(list).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            addMessage(redirectAttributes, "导出数据！失败信息：" + e.getMessage());
        }
        return "redirect:" + adminPath + "/vehicle/vehicle/merchant?repage";
    }

    @RequestMapping(value = "import", method = RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
        try {
            int successNum = 0;
            int failureNum = 0;
            StringBuilder failureMsg = new StringBuilder();
            ImportExcel ei = new ImportExcel(file, 1, 0);
            List<Merchant> list = ei.getDataList(Merchant.class);
            BigDecimal bigDecimal = null;
            for (Merchant merchant : list) {
                try {
                    if (merchant.getName() != null && merchant.getName().trim().length() > 0
                            && merchant.getTelephone() != null && merchant.getTelephone().trim().length() > 0) {

                        if (!merchant.getTelephone().contains("（") && !merchant.getTelephone().contains("-")) {
                            bigDecimal = new BigDecimal(merchant.getTelephone());
                            merchant.setTelephone(bigDecimal.toPlainString());
                        }
                        merchant.setUser(CommonUtil.getUser());
                        if (isDuplicate(merchant)) {
                            failureMsg.append("<br/>姓名" + merchant.getName() + " 已经导入; ");
                            failureNum++;
                            continue;
                        }
                        merchantService.save(merchant);
                        successNum++;
                    } else {
                        failureMsg.append("<br/>姓名" + merchant.getName() + " 导入失败; ");
                        failureNum++;
                    }
                } catch (ConstraintViolationException ex) {
                    ex.printStackTrace();
                    failureMsg.append("<br/>姓名 " + merchant.getName() + " 导入失败：");
                    List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
                    for (String message : messageList) {
                        failureMsg.append(message + "; ");
                        failureNum++;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    failureMsg.append("<br/>姓名 " + merchant.getName() + " 导入失败：" + ex.getMessage());
                }
            }
            if (failureNum > 0) {
                failureMsg.insert(0, "，失败 " + failureNum + " 条，导入信息如下：");
            }
            addMessage(redirectAttributes, "已成功导入 " + successNum + " 条用户" + failureMsg);
        } catch (Exception e) {
            addMessage(redirectAttributes, "导入失败！失败信息：" + e.getMessage());
        }
        return "redirect:" + adminPath + "/vehicle/merchant/merchant";
    }

    public boolean isDuplicate(Merchant merchant) {
        boolean flag = false;
        List<Merchant> merchants = merchantService.findMerchants(merchant);
        if (null != merchants && merchants.size() > 0) {
            flag = true;
        }
        return flag;
    }
}