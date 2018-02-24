package com.weather.nayibo.weather.citydetail;

import com.weather.nayibo.weather.BR;
import com.weather.nayibo.weather.R;

import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;

/**
 * Created by nayibo on 2018/2/24.
 */

public class CityDetailDateListItemBinding implements OnItemBind<CityDetailDateListViewModel> {
    public static CityDetailDateListItemBinding INSTANCE;

    @Override
    public void onItemBind(ItemBinding itemBinding, int position, CityDetailDateListViewModel item) {
        itemBinding.set(BR.vm, R.layout.citydetail_date_item);
    }

    public static CityDetailDateListItemBinding get() {
        if (INSTANCE == null) {
            INSTANCE = new CityDetailDateListItemBinding();
        }
        return INSTANCE;
    }
}
