package com.weather.nayibo.weather.list;

import com.weather.nayibo.weather.R;
import com.weather.nayibo.weather.base.BaseUI;
import com.weather.nayibo.weather.base.MvvmComponent;
import com.weather.nayibo.weather.base.ViewModel;

/**
 * Created by nayibo on 2018/1/25.
 */

public class ListPage extends BaseUI implements MvvmComponent {
    public ListPage() {
        super(null);
    }

    @Override
    public int getLayoutResID() {
        return R.layout.listpage_layout;
    }

    @Override
    public ViewModel getViewModel() {
        return new ListViewModel();
    }
}
