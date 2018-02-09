package com.weather.nayibo.weather.homepage;

import android.databinding.ObservableField;

import com.weather.nayibo.weather.App;
import com.weather.nayibo.weather.search.SearchPage;
import com.weather.nayibo.weather.stack.StackAction;
import com.weather.nayibo.weather.stack.StackManager;
import com.weather.nayibo.weather.utils.CityBean;
import com.weather.nayibo.weather.utils.Constant;
import com.weather.nayibo.weather.utils.ReadFileUtils;
import com.weather.nayibo.weather.base.BaseViewModel;
import com.weather.nayibo.weather.download.DownloadInfo;
import com.weather.nayibo.weather.download.DownloadManager;
import com.weather.nayibo.weather.download.DownloadObserver;
import com.weather.nayibo.weather.retrofit.RetrofitHelper;
import com.weather.nayibo.weather.utils.SharepreferenceUtil;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
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
            }
        });
        startDownloadCity();
        getCityName();
    }

    private void startDownloadCity() {
        if ((boolean) SharepreferenceUtil.get(App.context, Constant.CITY_LIST_DOWNLOAD_COMPLETE, false)) {
            return;
        }

        DownloadManager.getInstance().startDownload(Constant.CITY_LIST_URL, new DownloadObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                super.onSubscribe(d);
            }

            @Override
            public void onNext(@NonNull DownloadInfo downloadInfo) {
                super.onNext(downloadInfo);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                super.onError(e);
            }

            @Override
            public void onComplete() {
                super.onComplete();
                SharepreferenceUtil.put(App.context, Constant.CITY_LIST_DOWNLOAD_COMPLETE, true);
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
        StackManager.getInstance().startNewUI(new SearchPage(), StackAction.ADD);
    }

    private void getCityName() {
        Observable
                .create(new ReadFileUtils(App.context.getExternalCacheDir().getPath() + File.separator + Constant.CITY_LIST_FILE_NAME))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        String[] a = s.split("\\t");
                        if (a.length == 12) {
                            setCityInfo(a);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Constant.getCityBeans().remove(0);
                    }
                });
    }

    private void setCityInfo(String[] cityInfo) {
        CityBean cityBean = new CityBean();
        cityBean.setCityCode(cityInfo[0]);
        cityBean.setCityNameEN(cityInfo[1]);
        cityBean.setCityNameCN(cityInfo[2]);
        cityBean.setCountryCode(cityInfo[3]);
        cityBean.setCountryNameEN(cityInfo[4]);
        cityBean.setCountryNameCN(cityInfo[5]);
        cityBean.setProvinceNameEN(cityInfo[6]);
        cityBean.setProvinceCN(cityInfo[7]);
        cityBean.setParentCityEN(cityInfo[8]);
        cityBean.setParentCityCN(cityInfo[9]);
        cityBean.setLatitude(cityInfo[10]);
        cityBean.setLongitude(cityInfo[11]);

        Constant.getCityBeans().add(cityBean);
    }
}
