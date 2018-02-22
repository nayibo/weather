package com.weather.nayibo.weather.citylist;

import com.weather.nayibo.weather.BR;
import com.weather.nayibo.weather.R;

import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;

/**
 * Created by nayibo on 2018/2/22.
 */

public class CityListPageItemBinding implements OnItemBind<CityListPageItemViewModel> {
    public static CityListPageItemBinding INSTANCE;

    @Override
    public void onItemBind(ItemBinding itemBinding, int position, CityListPageItemViewModel item) {
        itemBinding.set(BR.vm, R.layout.citylistpage_item);
    }

    public static CityListPageItemBinding get() {
        if (INSTANCE == null) {
            INSTANCE = new CityListPageItemBinding();
        }
        return INSTANCE;
    }
}
