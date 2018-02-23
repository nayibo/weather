package com.weather.nayibo.weather.citydetail;

import com.weather.nayibo.weather.R;
import com.weather.nayibo.weather.base.BaseUI;
import com.weather.nayibo.weather.base.MvvmComponent;
import com.weather.nayibo.weather.base.ViewModel;

/**
 * Created by nayibo on 2018/2/23.
 */

public class CityDetailPage extends BaseUI implements MvvmComponent {
    public CityDetailPage() {
        super(null);
    }

    @Override
    public int getLayoutResID() {
        return R.layout.citydetail_layout;
    }

    @Override
    public ViewModel getViewModel() {
        return new CityDetailViewModel();
    }
}
