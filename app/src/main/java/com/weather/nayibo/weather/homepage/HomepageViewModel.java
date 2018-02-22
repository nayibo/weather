package com.weather.nayibo.weather.homepage;

import android.databinding.ObservableField;

import com.weather.nayibo.weather.search.SearchPage;
import com.weather.nayibo.weather.stack.StackAction;
import com.weather.nayibo.weather.stack.StackManager;
import com.weather.nayibo.weather.base.BaseViewModel;
import com.weather.nayibo.weather.retrofit.RetrofitHelper;
import com.weather.nayibo.weather.vo.WeatherBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nayibo on 2018/1/24.
 */

public class HomepageViewModel extends BaseViewModel {
    public final ObservableField<String> title = new ObservableField<>();

    private void getDate(long id) {
        RetrofitHelper.getInstance().getServer().getForecastCall("北京", "4586858aae93481c97f73c5ebfbccb47", "cn", "m").enqueue(new Callback<WeatherBean>() {
            @Override
            public void onResponse(Call<WeatherBean> call, Response<WeatherBean> response) {
                showUI(response.body());
            }

            @Override
            public void onFailure(Call<WeatherBean> call, Throwable t) {
            }
        });
    }

    @Override
    public void onAttach() {
        super.onAttach();
        getDate(1999);
    }

    private void showUI(WeatherBean data) {
        title.set(data.getHeWeather6().get(0).getDaily_forecast().get(0).getCond_code_d());
    }

    public void goList() {
        StackManager.getInstance().startNewUI(new SearchPage(), StackAction.ADD);
    }
}
