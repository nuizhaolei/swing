/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.vehicle.web.vehicle;

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
import com.thinkgem.jeesite.modules.vehicle.entity.vehicle.Vehicle;
import com.thinkgem.jeesite.modules.vehicle.service.vehicle.VehicleService;
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
 * vehicleController
 *
 * @author vehicle
 * @version 2020-03-31
 */
@Controller
@RequestMapping(value = "${adminPath}/vehicle/vehicle/vehicle")
public class VehicleController extends BaseController {

    @Autowired
    private VehicleService vehicleService;

    @ModelAttribute
    public Vehicle get(@RequestParam(required = false) String id) {
        Vehicle entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = vehicleService.get(id);
        }
        if (entity == null) {
            entity = new Vehicle();
        }
        return entity;
    }

    @RequestMapping(value = {"list", ""})
    public String list(Vehicle vehicle, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Vehicle> page = vehicleService.findPage(new Page<Vehicle>(request, response), vehicle);
        model.addAttribute("page", page);
        return "modules/vehicle/vehicle/vehicleList";
    }

    @RequestMapping(value = "export", method = RequestMethod.POST)
    public String export(Vehicle vehicle, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            String fileName = "车辆数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
            Page<Vehicle> page = vehicleService.findPage(new Page<Vehicle>(request, response), vehicle);
            new ExportExcel("车辆数据", Vehicle.class).setDataList(page.getList()).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            addMessage(redirectAttributes, "导出车辆数据！失败信息：" + e.getMessage());
        }
        return "redirect:" + adminPath + "/vehicle/vehicle/vehicleList?repage";
    }

    @RequestMapping(value = "import", method = RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
        try {
            int successNum = 0;
            int failureNum = 0;
            StringBuilder failureMsg = new StringBuilder();
            ImportExcel ei = new ImportExcel(file, 1, 0);
            List<Vehicle> list = ei.getDataList(Vehicle.class);
            SystemAuthorizingRealm.Principal principal = UserUtils.getPrincipal();
            User user = null;
            if (UserUtils.isAuthority(principal) && principal.getId() != null) {
                user = new User(principal.getId());
            }
            BigDecimal bigDecimal = null;
            for (Vehicle vehicle : list) {
                try {
                    if (vehicle.getName() != null && vehicle.getName().trim().length() > 0
                            && vehicle.getTelephone() != null && vehicle.getTelephone().trim().length() > 0
                            && vehicle.getVehicleType() != null && vehicle.getVehicleType().trim().length() > 0) {

                        bigDecimal = new BigDecimal(vehicle.getTelephone());
                        vehicle.setTelephone(bigDecimal.toPlainString());
                        vehicle.setUser(user);
                        if(isDuplicate(vehicle)) {
                            failureMsg.append("<br/>姓名" + vehicle.getName() + " 已经导入; ");
                            failureNum++;
                            continue;
                        }
                        vehicleService.save(vehicle);
                        successNum++;
                    } else {
                        failureMsg.append("<br/>姓名" + vehicle.getName() + " 导入失败; ");
                        failureNum++;
                    }
                } catch (ConstraintViolationException ex) {
                    ex.printStackTrace();
                    failureMsg.append("<br/>姓名 " + vehicle.getName() + " 导入失败：");
                    List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
                    for (String message : messageList) {
                        failureMsg.append(message + "; ");
                        failureNum++;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    failureMsg.append("<br/>姓名 " + vehicle.getName() + " 导入失败：" + ex.getMessage());
                }
            }
            if (failureNum > 0) {
                failureMsg.insert(0, "，失败 " + failureNum + " 条，导入信息如下：");
            }
            addMessage(redirectAttributes, "已成功导入 " + successNum + " 条用户" + failureMsg);
        } catch (Exception e) {
            addMessage(redirectAttributes, "导入失败！失败信息：" + e.getMessage());
        }
        return "redirect:" + adminPath + "/vehicle/vehicle/vehicle";
    }

    @RequestMapping(value = "form")
    public String form(Vehicle vehicle, Model model) {
        model.addAttribute("vehicle", vehicle);
        return "modules/vehicle/vehicle/vehicleForm";
    }

    @RequestMapping(value = "save")
    public String save(Vehicle vehicle, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, vehicle)) {
            return form(vehicle, model);
        }
        String regex = "^((1[0-9][0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
        if (vehicle.getTelephone() == null || !vehicle.getTelephone().matches(regex)) {
            model.addAttribute("customer", vehicle);
            addMessage(redirectAttributes, "保存信息失败，电话号不符合要求，请检查！");
            return "redirect:" + Global.getAdminPath() + "/vehicle/vehicle/vehicle/?repage";
        }
        SystemAuthorizingRealm.Principal principal = UserUtils.getPrincipal();
        if (UserUtils.isAuthority(principal) && principal.getId() != null) {
            vehicle.setUser(new User(principal.getId()));
        } else {
            addMessage(redirectAttributes, "保存失败！");
            return "redirect:" + Global.getAdminPath() + "/vehicle/vehicle/vehicle/?repage";
        }
        if (isDuplicate(vehicle)) {
            addMessage(redirectAttributes, "保存失败，该用户已经上传！");
            return "redirect:" + Global.getAdminPath() + "/vehicle/vehicle/vehicle/?repage";
        }
        vehicleService.save(vehicle);
        addMessage(redirectAttributes, "保存成功");
        return "redirect:" + Global.getAdminPath() + "/vehicle/vehicle/vehicle/?repage";
    }

    public boolean isDuplicate(Vehicle vehicle) {
        boolean flag = false;
        List<Vehicle> vehicles = vehicleService.findVehicles(vehicle);
        if(vehicles != null  && vehicles.size() > 0) {
            flag = true;
        }
        return flag;
    }

    @RequestMapping(value = "delete")
    public String delete(Vehicle vehicle, RedirectAttributes redirectAttributes) {
        try {
            SystemAuthorizingRealm.Principal principal = UserUtils.getPrincipal();
            if(principal.getId() != null && !principal.getId().equals(vehicle.getUser().getId())) {
                addMessage(redirectAttributes, "删除失败，只能删除自己添加的");
                return "redirect:" + Global.getAdminPath() + "/vehicle/vehicle/vehicle/?repage";
            }
            vehicleService.delete(vehicle);
            addMessage(redirectAttributes, "删除成功");
            return "redirect:" + Global.getAdminPath() + "/vehicle/vehicle/vehicle/?repage";
        }catch (Exception e) {
            addMessage(redirectAttributes, "删除失败!");
            return "redirect:" + Global.getAdminPath() + "/vehicle/vehicle/vehicle/?repage";
        }
    }

}