package com.nanningzhuanqian.vscreenshot.m01_wechat.wechat.person;

import android.content.res.AssetManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.nanningzhuanqian.vscreenshot.m01_wechat.custom.WxContactTagSelectionActivity;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wx_region_selection;
    }

    @Override
    protected void initView() {
        tvBack = (TextView) findViewById(R.id.tvBack);
        lvRegion = (ListView)findViewById(R.id.lvRegion);
        countryAdapter = new CountryAdapter();
        lvRegion.setAdapter(countryAdapter);
    }

    @Override
    protected void initEvent() {
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
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
        Log.i(TAG,"getCountries start");
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
                String []names = line.split("\\|");
                String enName = names[0];
                String cnName = names[1];
                Log.i(TAG,"line = "+line+ " "+code+" "+names.length+" "+enName+" "+cnName);
                String [] regions = enName.split("_");
                int length = regions.length;
                if(length<=1){  //国家级
                    if(!TextUtils.isEmpty(lastCountry)&&!TextUtils.equals(lastCountry,cnName)) {
                        country.setProvinces(provinces);
                        countries.add(country);
                        Log.i(TAG,"country enName "+country.getEnName()+" cnName "+country.getCnName()+" "+countries.size());
                    }
                    if(!TextUtils.equals(lastCountry,cnName)){
                        lastCountry = cnName;
                    }
                    provinces.clear();
                    cities.clear();
                    country = new Country();
                    country.setCnName(cnName);
                    country.setEnName(regions[0]);
                }else if(length == 2){  //省级
                    if(!TextUtils.isEmpty(lastProvince)&&!TextUtils.equals(lastProvince,cnName)){
                        province.setCities(cities);
                        provinces.add(province);
                    }
                    if(!TextUtils.equals(lastProvince,cnName)){
                        lastProvince = cnName;
                    }
                    cities.clear();
                    province.setCnName(cnName);
                    province.setEnName(regions[1]);
                    provinces.add(province);
                }else { //市级
                    city.setCnName(cnName);
                    city.setEnName(regions[2]);
                    cities.add(city);
                }
                line = br.readLine();
            }
            Log.i(TAG,"countries = "+countries.size());
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
            Log.i(TAG,"countries e = "+e.toString());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    hideLoadingDialog();
                }
            });
        }
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

    public class CountryAdapter extends BaseAdapter{

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

    public class ProvinceAdapter extends BaseAdapter{

        private List<Province> provinceList;

        public void setProvinces(List<Province> provinceList){
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

    public class CityAdapter extends BaseAdapter{

        private List<City> cityList;

        public void setCities(List<City> cityList){
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

    public class ViewHolder{

        TextView tvName;

    }

}
