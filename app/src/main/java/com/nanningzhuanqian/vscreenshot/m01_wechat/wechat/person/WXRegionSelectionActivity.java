package com.nanningzhuanqian.vscreenshot.m01_wechat.wechat.person;

import android.content.Intent;
import android.content.res.AssetManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nanningzhuanqian.vscreenshot.R;
import com.nanningzhuanqian.vscreenshot.base.BaseActivity;
import com.nanningzhuanqian.vscreenshot.base.bean.City;
import com.nanningzhuanqian.vscreenshot.base.bean.Country;
import com.nanningzhuanqian.vscreenshot.base.bean.Province;
import com.nanningzhuanqian.vscreenshot.base.bean.Tag;
import com.nanningzhuanqian.vscreenshot.base.bean.Tags;
import com.nanningzhuanqian.vscreenshot.base.util.WXRegionManager;
import com.nanningzhuanqian.vscreenshot.common.Constant;
import com.nanningzhuanqian.vscreenshot.m01_wechat.custom.WxContactTagSelectionActivity;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lenovo on 2019/1/18.
 */

public class WXRegionSelectionActivity extends BaseActivity {

    private TextView tvBack;
    private ListView lvRegion;
    private CountryAdapter countryAdapter;
    private ProvinceAdapter provinceAdapter;
    private CityAdapter cityAdapter;
    private int level = 0;//有3档 0是国家 1 是省份 2是城市
    private Country selectedCountry;
    private Province selectedProvince;
    private City selectedCity;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wx_region_selection;
    }

    @Override
    protected void initView() {
        tvBack = (TextView) findViewById(R.id.tvBack);
        lvRegion = (ListView) findViewById(R.id.lvRegion);
        countryAdapter = new CountryAdapter();
        lvRegion.setAdapter(countryAdapter);
    }

    @Override
    protected void initEvent() {
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (level == 0) {
                    setResult(Constant.RESULT_CODE_CANCEL);
                    finish();
                } else if (level == 1) {
                    level = 0;
                    selectedProvince = null;
                    selectCountry();
                } else if (level == 2) {
                    level = 1;
                    selectedCity = null;
                    selectProvince(provinces);
                }
            }
        });
        lvRegion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (level == 0) {
                    level = 1;
                    selectedCountry = countries.get(position);
                    provinces = selectedCountry.getProvinces();
                    Log.i("wmy", "onItemClick country " + selectedCountry.getCnName() + " " + (provinces != null ? provinces.size() : "null"));
                    selectProvince(provinces);
                } else if (level == 1) {
                    level = 2;
                    selectedProvince = provinces.get(position);
                    cities = selectedProvince.getCities();
                    selectCity(cities);
                } else if (level == 2) {
                    selectedCity = cities.get(position);
                    selectFinish();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(Constant.RESULT_CODE_CANCEL);
        finish();
    }

    @Override
    protected void initData() {
        getCountries();
    }

    List<Country> countries = new ArrayList<>();
    List<Province> provinces = new ArrayList<>();
    List<City> cities = new ArrayList<>();
    String lastCountry = "";
    String lastProvince = "";

    private void getCountries() {
        String path = "file:///android_asset/mmregioncode_zh_CN.txt";
        Log.i(TAG, "getCountries start");
        showLoadingDialog();
        AssetManager am = getAssets();
        try {
            InputStream is = am.open("mmregioncode_zh_CN.txt");

            String code = getCode(is);
            is = am.open("mmregioncode_zh_CN.txt");
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
            WXRegionManager.getInstance(getApplicationContext()).setResgionData(countries);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    hideLoadingDialog();
                    countryAdapter.notifyDataSetChanged();
                }
            });
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.i(TAG, "countries e = " + e.toString());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    hideLoadingDialog();
                }
            });
        }
    }

    private void selectCountry() {
        if (countries == null || countries.size() == 0) {
            return;
        }
        if (countryAdapter == null) {
            countryAdapter = new CountryAdapter();
        }
        lvRegion.setAdapter(countryAdapter);
    }

    private void selectProvince(List<Province> provinces) {
        //省份数量为0的话 就可以返回了
        if (provinces == null || provinces.size() == 0) {
            selectFinish();
            return;
        }
        provinceAdapter = new ProvinceAdapter();
        provinceAdapter.setProvinces(provinces);
        lvRegion.setAdapter(provinceAdapter);
    }

    private void selectCity(List<City> cities) {
        if (cities == null || cities.size() == 0) {
            selectFinish();
            return;
        }
        cityAdapter = new CityAdapter();
        cityAdapter.setCities(cities);
        lvRegion.setAdapter(cityAdapter);
    }

    private void selectFinish() {
        String countryName = selectedCountry.getCnName();
        String provinceName = selectedProvince==null?"":selectedProvince.getCnName();
        String cityName = selectedCity == null?"":selectedCity.getCnName();
        String address;
        if(TextUtils.isEmpty(cityName)){
            address = countryName+" "+provinceName;
        }else{
            address = provinceName+" "+cityName;
        }
        Intent intent = new Intent();
        intent.putExtra(Constant.INTENT_KEY_ADDRESS,address);
        setResult(Constant.RESULT_CODE_SUCCESS,intent);
        finish();
    }

    public String getCode(InputStream is) {
        try {
            BufferedInputStream bin = new BufferedInputStream(is);
            int p;

            p = (bin.read() << 8) + bin.read();

            String code = null;

            switch (p) {
                case 0xefbb:
                    code = "UTF-8";
                    break;
                case 0xfffe:
                    code = "Unicode";
                    break;
                case 0xfeff:
                    code = "UTF-16BE";
                    break;
                default:
                    code = "GBK";
            }
            is.close();
            return code;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public class CountryAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return countries.size();
        }

        @Override
        public Object getItem(int i) {
            return countries.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getThis()).inflate(R.layout.item_wx_region, viewGroup, false);
                viewHolder = new ViewHolder();
                viewHolder.tvName = (TextView) convertView.findViewById(R.id.tvName);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            Country country = countries.get(i);
            String name = country.getCnName();
            viewHolder.tvName.setText(name);
            return convertView;
        }
    }

    public class ProvinceAdapter extends BaseAdapter {

        private List<Province> provinceList;

        public void setProvinces(List<Province> provinceList) {
            this.provinceList = provinceList;
        }

        @Override
        public int getCount() {
            return provinceList.size();
        }

        @Override
        public Object getItem(int i) {
            return provinceList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getThis()).inflate(R.layout.item_wx_region, viewGroup, false);
                viewHolder = new ViewHolder();
                viewHolder.tvName = (TextView) convertView.findViewById(R.id.tvName);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            Province province = provinceList.get(i);
            String name = province.getCnName();
            viewHolder.tvName.setText(name);
            return convertView;
        }
    }

    public class CityAdapter extends BaseAdapter {

        private List<City> cityList;

        public void setCities(List<City> cityList) {
            this.cityList = cityList;
        }

        @Override
        public int getCount() {
            return cityList.size();
        }

        @Override
        public Object getItem(int i) {
            return cityList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getThis()).inflate(R.layout.item_wx_region, viewGroup, false);
                viewHolder = new ViewHolder();
                viewHolder.tvName = (TextView) convertView.findViewById(R.id.tvName);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            City city = cityList.get(i);
            String name = city.getCnName();
            viewHolder.tvName.setText(name);
            return convertView;
        }
    }

    public class ViewHolder {

        TextView tvName;

    }

}
