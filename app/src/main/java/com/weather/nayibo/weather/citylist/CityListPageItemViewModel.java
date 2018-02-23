package com.weather.nayibo.weather.citylist;

import android.databinding.ObservableField;

import com.weather.nayibo.weather.base.BaseViewModel;
import com.weather.nayibo.weather.vo.WeatherBean;

/**
 * Created by nayibo on 2018/2/22.
 */

public class CityListPageItemViewModel extends BaseViewModel {
    public final ObservableField<String> cityCode = new ObservableField<>();
    public final ObservableField<String> cityName = new ObservableField<>();
    public final ObservableField<String> temperature = new ObservableField<>();
    public final ObservableField<String> date = new ObservableField<>();
    public final ObservableField<String> wind = new ObservableField<>();
    public final ObservableField<String> cond = new ObservableField<>();
    public final ObservableField<Boolean> isEdit = new ObservableField<>();
    public final ObservableField<Boolean> isChecked = new ObservableField<>(false);

    public CityListPageItemViewModel(WeatherBean model, boolean edit) {
        cityCode.set(model.getHeWeather6().get(0).getBasic().getCid());
        cityName.set(model.getHeWeather6().get(0).getBasic().getLocation());
        temperature.set(model.getHeWeather6().get(0).getDaily_forecast().get(0).getTmp_max() + " - " + model.getHeWeather6().get(0).getDaily_forecast().get(0).getTmp_min());
        date.set(model.getHeWeather6().get(0).getDaily_forecast().get(0).getDate());
        wind.set("风力:" + model.getHeWeather6().get(0).getDaily_forecast().get(0).getWind_sc());
        cond.set("白天:" + model.getHeWeather6().get(0).getDaily_forecast().get(0).getCond_txt_d() + " 夜晚:" + model.getHeWeather6().get(0).getDaily_forecast().get(0).getCond_txt_n());
        isEdit.set(edit);
    }

    public void selectItem() {
    }

    public void checkChange(boolean checked) {
        isChecked.set(checked);
    }
}
