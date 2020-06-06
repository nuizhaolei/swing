package com.thinkgem.jeesite.modules.vehicle.web.common;

import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.security.SystemAuthorizingRealm;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

public class CommonUtil {

    public static User getUser() {
        SystemAuthorizingRealm.Principal principal = UserUtils.getPrincipal();
        User user = new User(principal.getId());
        if("1".equals(user.getId())) {
            return new User();
        }
        return user;
    }
}
