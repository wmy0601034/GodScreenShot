package com.nanningzhuanqian.vscreenshot.base.util;

import com.nanningzhuanqian.vscreenshot.item.DiscoveryItem;

import java.util.Comparator;

public class TypeComparator  implements Comparator<DiscoveryItem> {

    public int compare(DiscoveryItem o1, DiscoveryItem o2) {

            return String.valueOf(o1.getType()).compareTo( String.valueOf(o2.getType()));

    }

}