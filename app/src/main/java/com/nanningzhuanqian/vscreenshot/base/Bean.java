package com.nanningzhuanqian.vscreenshot.base;




import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by WeiKeting on 2016/12/19.
 */

public class Bean implements Serializable,IModel {

    private JSONObject json = new JSONObject();

    private boolean isSet  = false;
    public <T extends Bean> T  setJSONObject(JSONObject json)
    {
        if(isSet)throw new RuntimeException("setJSONObject can only call once!");
        isSet = true;
        this.json = json;
        return (T)this;
    }

    @Override
    public void onDestroy() {
        json = null;
    }

    @Override
    public <O extends Object>O valueForKey(String key) {
        Object o;
        if(json.containsKey(key)){
            o = json.get(key);
            if(o instanceof JSONObject){
                Bean b = new Bean();
                b.setJSONObject((JSONObject) o);
                o=b;
            }else if(o instanceof JSONArray){
                JSONArray array = (JSONArray) o;
                Bean b = null;
                String s;
                List<Object> ret = new ArrayList<>();
                for(int i=0;i<array.size();i++){
                    try{
                        b = new Bean();
                        b.setJSONObject(array.getJSONObject(i));
                        ret.add(b);
                    }catch (ClassCastException e){
                        ret.add(array.get(i));
                    }
                }
                o=ret;
            }
            return (O) o;
        }
        return null;
    }

    final public <O extends Object>O rawValueForKey(String key)
    {
        if(json.containsKey(key))return (O) json.get(key);
        return null;
    }

    @Override
    public void setValueForKey(String key, Object v) {
        if(v == null){
            json.remove(key);
            return;
        }
        try {
            json.put(key,v);
        } catch (JSONException e) {
        }
    }

    public <T extends Bean>T set(String k,Object v){
        setValueForKey(k,v);
        return (T)this;
    }

    @Override
    public boolean hasKey(String key) {
        return json.containsKey(key);
    }

    public Iterator<String> keys(){
        return json.keySet().iterator();
    }

    public List<String> listKeys(){
        List<String> ret = null;
        Iterator<String> iter = keys();
        if(iter!=null){
            String k;
            while (iter.hasNext()){
                if(ret == null)ret = new ArrayList<>();
                k = iter.next();
                ret.add(k);
            }
        }
        return ret;
    }

    public JSONObject innerJSON()
    {
        return json;
    }

    @Override
    public String toString()
    {
        return getClass().getSimpleName()+"@"+json;
    }

    public long longForKey(String key)
    {
        Object o = valueForKey(key);
        try{
            return Long.parseLong(String.valueOf(o));
        }catch (Throwable e){
        }
        return 0;
    }

    public int integerForKey(String key)
    {
        Object o = valueForKey(key);
        try{
            return Integer.parseInt(String.valueOf(o));
        }catch (Throwable e){
        }
        return 0;
    }

    public float floatForKey(String key)
    {
        Object o = valueForKey(key);
        try{
            return Float.parseFloat(String.valueOf(o));
        }catch (Throwable e){
        }
        return 0;
    }

    public double doubleForKey(String key)
    {
        Object o = valueForKey(key);
        try{
            return Double.parseDouble(String.valueOf(o));
        }catch (Throwable e){
        }
        return 0;
    }

    public String stringForKey(String key)
    {
        Object o = valueForKey(key);
        if(o == null)return null;
        if(o instanceof String)return (String) o;
        return String.valueOf(o);
    }

    public boolean booleanForKey(String key)
    {
        Object o = valueForKey(key);
        if(o instanceof Boolean)return (Boolean) o;
        return longForKey(key) != 0;
    }

    public Bean copy(Bean src, String ... keys)
    {
        if(keys == null)return this;
        for(String k:keys){
            set(k,src.valueForKey(k));
        }
        return this;
    }

    public Bean copyAll(Bean src)
    {
        copy(src,src.listKeys().toArray(new String[0]));
        return this;
    }

    public <T> List<T> arrayForKey(String key)
    {
        try {
            return (List<T>) valueForKey(key);
        }catch (Throwable e){
            return null;
        }
    }

    public void clear() {
        json.clear();
    }
}
