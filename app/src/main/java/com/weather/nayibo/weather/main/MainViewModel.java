package com.weather.nayibo.weather.main;

import android.databinding.ObservableField;

import com.weather.nayibo.weather.App;
import com.weather.nayibo.weather.base.BaseViewModel;
import com.weather.nayibo.weather.base.MvvmComponent;
import com.weather.nayibo.weather.citylist.CityListPage;
import com.weather.nayibo.weather.download.DownloadInfo;
import com.weather.nayibo.weather.download.DownloadManager;
import com.weather.nayibo.weather.download.DownloadObserver;
import com.weather.nayibo.weather.search.CityBean;
import com.weather.nayibo.weather.stack.StackAction;
import com.weather.nayibo.weather.stack.StackManager;
import com.weather.nayibo.weather.utils.Constant;
import com.weather.nayibo.weather.utils.ReadFileUtils;
import com.weather.nayibo.weather.utils.SharepreferenceUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by nayibo on 2018/1/24.
 */

public class MainViewModel extends BaseViewModel {
    public final ObservableField<MvvmComponent> mainComponent = new ObservableField<>();

    @Override
    public void onAttach() {
        super.onAttach();
        EventBus.getDefault().register(this);
        startDownloadCity();
        getCityName();
        StackManager.getInstance().startNewUI(new CityListPage(), StackAction.ADD);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onMessageEvent(MvvmComponent component) {
        mainComponent.set(component);
//        component.onResume();
    }

    private void startDownloadCity() {
        if ((Boolean) SharepreferenceUtil.get(App.context, Constant.CITY_LIST_DOWNLOAD_COMPLETE, false)) {
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
