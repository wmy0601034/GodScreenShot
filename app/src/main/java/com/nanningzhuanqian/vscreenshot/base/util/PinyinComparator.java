package com.nanningzhuanqian.vscreenshot.base.util;

import com.nanningzhuanqian.vscreenshot.item.ContractItem;
import com.nanningzhuanqian.vscreenshot.model.SortModel;

import java.util.Comparator;

public class PinyinComparator implements Comparator<ContractItem> {

    public int compare(ContractItem o1, ContractItem o2) {
        if (o1.getLetters().equals("@")
                || o2.getLetters().equals("#")) {
            return -1;
        } else if (o1.getLetters().equals("#")
                || o2.getLetters().equals("@")) {
            return 1;
        } else {
            return o1.getLetters().compareTo(o2.getLetters());
        }
    }

}