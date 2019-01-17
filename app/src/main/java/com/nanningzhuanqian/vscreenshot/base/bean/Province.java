package com.nanningzhuanqian.vscreenshot.base.bean;

import java.util.List;

/**
 * Created by lenovo on 2019/1/18.
 */

public class Province {

    private String enName;

    private String cnName;

    private List<City> cities;

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
