package com.thinkgem.jeesite.modules.vehicle.web.common;

import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.security.SystemAuthorizingRealm;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

public class CommonUtil {

    public static User getUser() {
        SystemAuthorizingRealm.Principal principal = UserUtils.getPrincipal();
        User user = new User(principal.getId());
        if ("1".equals(user.getId())) {
            return new User();
        }
        return user;
    }

    public static boolean checkTel(String strTel) {
        String regex = "^((1[0-9][0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
        String callRegex = "^0\\d{2}-\\d{7,8}$";
        if (null != strTel && (strTel.matches(regex) || strTel.matches(callRegex))) {
            return false;
        }
        return true;
    }
}
