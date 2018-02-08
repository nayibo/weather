package com.weather.nayibo.weather.list;

import com.weather.nayibo.weather.BR;
import com.weather.nayibo.weather.R;

import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;

/**
 * Created by nayibo on 2018/1/26.
 */

public class ListItemBinding implements OnItemBind<ListPageItemViewModel> {
    private static ListItemBinding INSTANCE;

    @Override
    public void onItemBind(ItemBinding itemBinding, int position, ListPageItemViewModel item) {
        itemBinding.set(BR.vm, R.layout.listpage_item);
    }

    public static ListItemBinding get() {
        if (INSTANCE == null) {
            INSTANCE = new ListItemBinding();
        }
        return INSTANCE;
    }
}
