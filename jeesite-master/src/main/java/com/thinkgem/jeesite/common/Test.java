package com.thinkgem.jeesite.common;

public class Test {

    public static void main(String... args) {
        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
        System.out.println("15193104283".matches(regex));

        String reg = "（|-";
        System.out.println("（028）65279623".contains(reg));
    }
}
