package com.weather.nayibo.weather.citylist;

import com.weather.nayibo.weather.R;
import com.weather.nayibo.weather.base.BaseUI;
import com.weather.nayibo.weather.base.MvvmComponent;
import com.weather.nayibo.weather.base.ViewModel;

/**
 * Created by nayibo on 2018/2/22.
 */

public class CityListPage extends BaseUI implements MvvmComponent {
    public CityListPage() {
        super(null);
    }

    @Override
    public int getLayoutResID() {
        return R.layout.citylist_layout;
    }

    @Override
    public ViewModel getViewModel() {
        return new CityListPageViewModel();
    }
}
