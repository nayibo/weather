package com.weather.nayibo.weather.citylist;

import android.databinding.ObservableField;

import com.weather.nayibo.weather.base.BaseViewModel;
import com.weather.nayibo.weather.vo.WeatherBean;

/**
 * Created by nayibo on 2018/2/22.
 */

public class CityListPageItemViewModel extends BaseViewModel {
    public final ObservableField<String> cityName = new ObservableField<>();

    public CityListPageItemViewModel(WeatherBean model) {
        cityName.set(model.getHeWeather6().get(0).getBasic().getLocation());
    }

    public void selectItem() {
    }
}
