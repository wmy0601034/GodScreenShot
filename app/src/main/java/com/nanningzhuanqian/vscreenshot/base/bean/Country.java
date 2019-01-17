package com.nanningzhuanqian.vscreenshot.base.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2019/1/18.
 */

public class Country {

    private String enName;

    private String cnName;

    private List<Province> provinces = new ArrayList<>();

    private List<City> cities = new ArrayList<>();

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

    public List<Province> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<Province> provinces) {
        this.provinces = provinces;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
