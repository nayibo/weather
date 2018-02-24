package com.weather.nayibo.weather.citydetail;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;

import com.weather.nayibo.weather.base.BaseViewModel;
import com.weather.nayibo.weather.vo.WeatherBean;

/**
 * Created by nayibo on 2018/2/23.
 */

public class CityDetailViewModel extends BaseViewModel {
    private final ObservableList<CityDetailDateListViewModel> mData = new ObservableArrayList<>();
    public ObservableField<String> cityName = new ObservableField<>();

    public CityDetailViewModel(WeatherBean bean) {
        cityName.set(bean.getHeWeather6().get(0).getBasic().getLocation());
        for(WeatherBean.HeWeather6Bean.DailyForecastBean forecastBean : bean.getHeWeather6().get(0).getDaily_forecast()) {
            mData.add(new CityDetailDateListViewModel(forecastBean));
        }
    }

    public ObservableList<CityDetailDateListViewModel> items() {
        return mData;
    }

    public void onItemBound(int position) {
    }
}
