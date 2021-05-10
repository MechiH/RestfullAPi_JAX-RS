package com.mechi.service;

import jakarta.ws.rs.QueryParam;

public class BeanFilter {
    private @QueryParam("param1") int param1;
    private @QueryParam("param3") int param3;
    private @QueryParam("param2") int param2;

    public int getParam1() {
        return this.param1;
    }

    public void setParam1(int param1) {
        this.param1 = param1;
    }

    public int getParam2() {
        return this.param2;
    }

    public void setParam2(int param2) {
        this.param2 = param2;
    }

    public int getParam3() {
        return this.param3;
    }

    public void setParam3(int param3) {
        this.param3 = param3;
    }

}
