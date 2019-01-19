package com.nanningzhuanqian.vscreenshot.base.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;
import android.util.Log;

import com.nanningzhuanqian.vscreenshot.base.bean.City;
import com.nanningzhuanqian.vscreenshot.base.bean.Country;
import com.nanningzhuanqian.vscreenshot.base.bean.Province;
import com.nanningzhuanqian.vscreenshot.item.AliPayItems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2019/1/19.
 */

public class WXRegionManager {

    private String TAG = getClass().getSimpleName();

    private static WXRegionManager mThis;

    private Context context;

    private List<Country> countries = new ArrayList<>();
    private List<Province> provinces = new ArrayList<>();
    private List<City> cities = new ArrayList<>();
    private String lastCountry = "";
    private String lastProvince = "";

    private WXRegionManager(Context context) {
        super();
        this.context = context;
    }

    public static WXRegionManager getInstance(Context context) {

        if (mThis == null)
            mThis = new WXRegionManager(context);

        return mThis;
    }

    public void init(){
        initRegionData();
    }

    public void release(){
        countries.clear();
    }

    private void initRegionData() {
        Log.i(TAG, "getCountries start");
        AssetManager am = context.getAssets();
        try {
            InputStream is = am.open("mmregioncode_zh_CN.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
            Country country = new Country();
            Province province = new Province();
            City city = new City();
            String line = br.readLine();
            while (line != null) {
                String[] names = line.split("\\|");
                String enName = names[0];
                String cnName = names[1];
                String[] regions = enName.split("_");
                int length = regions.length;
                if (length <= 1) {  //国家级
                    if (TextUtils.isEmpty(lastCountry) || !TextUtils.equals(lastCountry, cnName)) {
                        List<Province> provinceList = new ArrayList<>();
                        for (int i = 0; i < provinces.size(); i++) {
                            provinceList.add(provinces.get(i));
                        }
                        country.setProvinces(provinceList);
                        if (!TextUtils.isEmpty(lastCountry)) {
                            countries.add(country);
                            Log.i(TAG, countries.size() + "添加 country = " + lastCountry + " " + country.getProvinces().size());
                        }
                        lastCountry = cnName;
                    }
                    provinces.clear();
                    cities.clear();
                    country = new Country();
                    country.setCnName(cnName);
                    country.setEnName(regions[0]);
                } else if (length == 2) {  //省级
                    if (!TextUtils.isEmpty(cnName)) {
                        province = new Province();
                        province.setCnName(cnName);
                        province.setEnName(regions[1]);
                        if (!TextUtils.equals(lastProvince, cnName)) {
                            provinces.add(province);
                            lastProvince = cnName;
                        }
                        cities.clear();
                        cities = new ArrayList<>();
                    }
                } else { //市级
                    city = new City();
                    city.setCnName(cnName);
                    city.setEnName(regions[2]);
                    cities.add(city);
                    List<City> cityList = new ArrayList<>();
                    for (int i = 0; i < cities.size(); i++) {
                        cityList.add(cities.get(i));
                    }
                    if(provinces.size()!=0) {
                        int position = provinces.size()-1;
                        provinces.get(position).setCities(cityList);
                    }
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.i(TAG, "countries e = " + e.toString());
        }
    }

    public List<Country> getRegionData(){
        if(countries==null||countries.size()==0){
            init();
        }
        return countries;
    }

    public void setResgionData(List<Country> countries){
        this.countries = countries;
    }

}
