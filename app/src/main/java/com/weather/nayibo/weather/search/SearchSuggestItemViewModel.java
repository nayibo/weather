package com.weather.nayibo.weather.search;

import android.databinding.ObservableField;

import com.weather.nayibo.weather.base.BaseViewModel;
import com.weather.nayibo.weather.utils.Constant;

/**
 * Created by nayibo on 2018/2/9.
 */

public class SearchSuggestItemViewModel extends BaseViewModel {
    public final ObservableField<String> cityName = new ObservableField<>();
    private CityBean cityBean;

    public SearchSuggestItemViewModel(CityBean model) {
        this.cityBean = model;
        cityName.set(model.getCityNameCN());
    }

    public void selectItem() {
        Constant.getCityList().add(cityBean);
    }
}
