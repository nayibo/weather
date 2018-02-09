package com.weather.nayibo.weather.search;

import android.databinding.ObservableField;
import com.weather.nayibo.weather.base.BaseViewModel;

/**
 * Created by nayibo on 2018/2/9.
 */

public class SearchSuggestItemViewModel extends BaseViewModel {
    public final ObservableField<String> cityName = new ObservableField<>();

    public SearchSuggestItemViewModel(CityBean model) {
        cityName.set(model.getCityNameCN());
    }

    public void selectItem() {
    }
}
