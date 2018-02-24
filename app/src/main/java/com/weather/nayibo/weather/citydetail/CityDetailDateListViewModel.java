package com.weather.nayibo.weather.citydetail;

import android.databinding.ObservableField;

import com.weather.nayibo.weather.base.BaseViewModel;
import com.weather.nayibo.weather.vo.WeatherBean;

/**
 * Created by nayibo on 2018/2/24.
 */

public class CityDetailDateListViewModel extends BaseViewModel {
    public ObservableField<String> date = new ObservableField<>();
    public ObservableField<String> tmp = new ObservableField<>();
    public final ObservableField<String> cond = new ObservableField<>();
    public final ObservableField<String> wind = new ObservableField<>();

    public CityDetailDateListViewModel(WeatherBean.HeWeather6Bean.DailyForecastBean bean) {
        date.set(bean.getDate());
        tmp.set(" " + bean.getTmp_max() + " ~ " + bean.getTmp_min());
        cond.set("白天:" + bean.getCond_txt_d() + " 夜晚:" + bean.getCond_txt_n());
        wind.set("风力:" + bean.getWind_sc());
    }

    public void selectItem() {

    }
}
