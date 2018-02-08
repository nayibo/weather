package com.weather.nayibo.weather.homepage;

import android.databinding.ObservableField;
import android.util.Log;

import com.weather.nayibo.weather.base.BaseViewModel;
import com.weather.nayibo.weather.download.DownloadInfo;
import com.weather.nayibo.weather.download.DownloadManager;
import com.weather.nayibo.weather.download.DownloadObserver;
import com.weather.nayibo.weather.retrofit.RetrofitHelper;
import com.weather.nayibo.weather.stack.StackManager;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nayibo on 2018/1/24.
 */

public class HomepageViewModel extends BaseViewModel {
    public final ObservableField<String> title = new ObservableField<>();

    private void getDate(long id) {
        RetrofitHelper.getInstance().getServer().getForecast("北京", "4586858aae93481c97f73c5ebfbccb47", "cn", "m").enqueue(new Callback<HomeModel>() {
            @Override
            public void onResponse(Call<HomeModel> call, Response<HomeModel> response) {
                showUI(response.body());
            }

            @Override
            public void onFailure(Call<HomeModel> call, Throwable t) {
                Log.d("nayibo", "error: " + t.toString());
            }
        });
        startDownloadCity();
    }

    private void startDownloadCity() {
        DownloadManager.getInstance().startDownload("https://cdn.heweather.com/china-city-list.txt", new DownloadObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                super.onSubscribe(d);
            }

            @Override
            public void onNext(@NonNull DownloadInfo downloadInfo) {
                super.onNext(downloadInfo);
                Log.d("nayibo", "percent: " + 100 * downloadInfo.getProgress() / downloadInfo.getTotal()+ " filename: " + downloadInfo.getFileName());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                super.onError(e);
                Log.d("nayibo", e.toString());
            }

            @Override
            public void onComplete() {
                super.onComplete();
                Log.d("nayibo", "下载完成");
            }
        });
    }

    @Override
    public void onAttach() {
        super.onAttach();
        getDate(1999);
    }

    private void showUI(HomeModel data) {
        title.set(data.getHeWeather6().get(0).getDaily_forecast().get(0).getCond_code_d());
    }

    public void goList() {
//        StackManager.getInstance().startNewUI(new ListPage(), StackAction.ADD);
        getDate(4444);
    }
}
