package com.nanningzhuanqian.vscreenshot.m01_wechat.custom;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
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
import com.nanningzhuanqian.vscreenshot.base.Util;
import com.nanningzhuanqian.vscreenshot.base.bean.Contact;
import com.nanningzhuanqian.vscreenshot.base.bean.Tag;
import com.nanningzhuanqian.vscreenshot.base.bean.Tags;
import com.nanningzhuanqian.vscreenshot.base.bean.TagsCur;
import com.nanningzhuanqian.vscreenshot.base.util.DBManager;
import com.nanningzhuanqian.vscreenshot.base.util.MyDB;
import com.nanningzhuanqian.vscreenshot.widget.CommonContentEditDialog;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2019/1/14.
 */

public class WxContactTagSelectionActivity extends BaseActivity {

    private ListView lvTag;
    private TagAdapter tagAdapter;
    private TextView tvSelected;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wx_contact_tag_selection;
    }

    @Override
    protected void initView() {
        tvBack = (TextView)findViewById(R.id.tvBack);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvTitle.setText("选择联系人标签");
        tvRight = (TextView) findViewById(R.id.tvRight);
        tvRight.setText("新建");
        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTagNameEditDialog();
            }
        });
        tvSelected = (TextView) findViewById(R.id.tvSelected);
        lvTag = (ListView) findViewById(R.id.lvTag);
        tagAdapter = new TagAdapter();
        lvTag.setAdapter(tagAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }



    @Override
    protected void initEvent() {
        lvTag.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Tag tag = Tags.getInstance().get(i);
                if (tag.isSelected()) {
                    Tags.getInstance().get(i).setSelected(false);
                    removeTag(tag);
                } else {
                    Tags.getInstance().get(i).setSelected(true);
                    addTag(tag);
                }
                resetTagName();
                tagAdapter.notifyDataSetChanged();
            }
        });
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void resetTagName() {
        String tag = "";
        for (int i = 0; i < TagsCur.getInstance().size(); i++) {
            tag += TagsCur.getInstance().get(i).getName() + " ";
        }
        tvSelected.setText(tag);
    }

    private void removeTag(Tag tag) {
        for (int i = 0; i < TagsCur.getInstance().size(); i++) {
            if (TagsCur.getInstance().get(i).getName().equals(tag.getName())) {
                TagsCur.getInstance().remove(i);
                return;
            }
        }
    }

    private void addTag(Tag tag) {
        TagsCur.getInstance().add(tag);
    }

    private void showTagNameEditDialog() {
        final CommonContentEditDialog dialog = new CommonContentEditDialog(WxContactTagSelectionActivity.this);
        dialog.setTitle("编辑标签名称");
        dialog.setOnCancelListener(new CommonContentEditDialog.OnCancelListener() {
            @Override
            public void OnCancel() {
                dialog.dismiss();
            }
        });
        dialog.setOnOkListener(new CommonContentEditDialog.OnOkListener() {
            @Override
            public void OnOk(String mark) {
                dialog.dismiss();
                String name = mark;
                if(TextUtils.isEmpty(name)){
                    toast(getString(R.string.tag_name_not_empty));
                    return;
                }
                if(Tags.getInstance().has(name)){
                    toast(getString(R.string.tag_name_exists));
                    return;
                }
                if(!Util.checkTagFormat(name)){
                    toast(getString(R.string.tag_name_format_no_right));
                    return;
                }
                if(name.length()>6){
                    toast(getString(R.string.tag_name_length_limit));
                    return;
                }
                List<Contact> contactList = new ArrayList<>();
                Tag tag = new Tag();
                tag.setName(name);
                tag.setContactList(contactList);
                DBManager.saveTag(getApplicationContext(),tag);
//                boolean result = tag.save();
//                Log.i("wmy","tag.save() = "+result);
                tag.setSelected(true);
                Tags.getInstance().add(tag);
                TagsCur.getInstance().add(tag);
                resetTagName();
                tagAdapter.notifyDataSetChanged();
            }
        });
        dialog.show();
    }

    @Override
    protected void initData() {
        Tags.getInstance().clear();
//        List<Tag> tagList = LitePal.findAll(Tag.class);
        List<Tag> tagList = DBManager.getTags(getApplicationContext());
        Tags.getInstance().add(tagList);
        Log.i("wmy","initData Tags = "+Tags.getInstance().size());
        Tags.getInstance().resetSelection();
        tagAdapter.notifyDataSetChanged();
    }

    public class TagAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return Tags.getInstance().size();
        }

        @Override
        public Object getItem(int i) {
            return Tags.getInstance().get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getThis()).inflate(R.layout.item_wx_contact_tag, viewGroup, false);
                viewHolder = new ViewHolder();
                viewHolder.tvTag = (TextView) convertView.findViewById(R.id.tvTag);
                viewHolder.imgSelect = (ImageView) convertView.findViewById(R.id.imgSelect);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            Tag tag = Tags.getInstance().get(i);
            String name = tag.getName();
            boolean isSelected = tag.isSelected();
            viewHolder.tvTag.setText(name);
            if (isSelected) {
                viewHolder.imgSelect.setVisibility(View.VISIBLE);
            } else {
                viewHolder.imgSelect.setVisibility(View.GONE);
            }
            return convertView;
        }

        class ViewHolder {
            TextView tvTag;
            ImageView imgSelect;
        }
    }
}
