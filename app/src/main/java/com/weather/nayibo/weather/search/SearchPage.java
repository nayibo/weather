package com.weather.nayibo.weather.search;

import com.weather.nayibo.weather.R;
import com.weather.nayibo.weather.base.BaseUI;
import com.weather.nayibo.weather.base.MvvmComponent;
import com.weather.nayibo.weather.base.ViewModel;

/**
 * Created by nayibo on 2018/2/9.
 */

public class SearchPage extends BaseUI implements MvvmComponent {
    public SearchPage() {
        super(null);
    }

    @Override
    public int getLayoutResID() {
        return R.layout.search_layout;
    }

    @Override
    public ViewModel getViewModel() {
        return new SearchViewModel();
    }
}
