package com.thinkgem.jeesite.modules.vehicle.entity.common;

import java.io.Serializable;

/**
 * @author 15692
 */
public class Gbt22602007 implements Serializable {

    /**
     * 行政区划ID
     */
    private String id;
    /**
     * 行政区划父阶代码
     */
    private String parentId;
    /**
     * 行政区划代码
     */
    private String sXzqhDm;
    /**
     * 行政区划中文名称
     */
    private String sXzqhCmc;
    /**
     * 行政区划英文名称
     */
    private String sXzqhEmc;
    /**
     * 罗马字母拼写
     */
    private String sXzqhLmzm;
    /**
     * 字母码
     */
    private String sXzqhZmm;
    /**
     * 拼音简码
     */
    private String sPyjm;
    /**
     * 备注
     */
    private String sBz;

    private Double childCount;
    /**
     * 是否有效:
     * 1  是
     * 2  否
     */
    private char sSfyx;

    private String postCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getsXzqhDm() {
        return sXzqhDm;
    }

    public void setsXzqhDm(String sXzqhDm) {
        this.sXzqhDm = sXzqhDm;
    }

    public String getsXzqhCmc() {
        return sXzqhCmc;
    }

    public void setsXzqhCmc(String sXzqhCmc) {
        this.sXzqhCmc = sXzqhCmc;
    }

    public String getsXzqhEmc() {
        return sXzqhEmc;
    }

    public void setsXzqhEmc(String sXzqhEmc) {
        this.sXzqhEmc = sXzqhEmc;
    }

    public String getsXzqhLmzm() {
        return sXzqhLmzm;
    }

    public void setsXzqhLmzm(String sXzqhLmzm) {
        this.sXzqhLmzm = sXzqhLmzm;
    }

    public String getsXzqhZmm() {
        return sXzqhZmm;
    }

    public void setsXzqhZmm(String sXzqhZmm) {
        this.sXzqhZmm = sXzqhZmm;
    }

    public String getsPyjm() {
        return sPyjm;
    }

    public void setsPyjm(String sPyjm) {
        this.sPyjm = sPyjm;
    }

    public String getsBz() {
        return sBz;
    }

    public void setsBz(String sBz) {
        this.sBz = sBz;
    }

    public Double getChildCount() {
        return childCount;
    }

    public void setChildCount(Double childCount) {
        this.childCount = childCount;
    }

    public char getsSfyx() {
        return sSfyx;
    }

    public void setsSfyx(char sSfyx) {
        this.sSfyx = sSfyx;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
}
