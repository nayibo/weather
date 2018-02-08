package com.weather.nayibo.weather.homepage;

import com.weather.nayibo.weather.R;
import com.weather.nayibo.weather.base.BaseUI;
import com.weather.nayibo.weather.base.MvvmComponent;
import com.weather.nayibo.weather.base.ViewModel;

/**
 * Created by nayibo on 2018/1/24.
 */

public class Homepage extends BaseUI implements MvvmComponent {
    public Homepage() {
        super(null);
    }

    @Override
    public int getLayoutResID() {
        return R.layout.homepage_layout;
    }

    @Override
    public ViewModel getViewModel() {
        return new HomepageViewModel();
    }
}
