package com.nanningzhuanqian.vscreenshot.item;

public class ContractItems extends DataStorageImpl<ContractItem> {

    private static ContractItems mThis;

    private ContractItems() {
        super();
    }

    public static ContractItems getInstance() {

        if (mThis == null)
            mThis = new ContractItems();

        return mThis;
    }

}
