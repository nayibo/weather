package com.weather.nayibo.weather.citylist;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.weather.nayibo.weather.App;
import com.weather.nayibo.weather.base.BaseViewModel;
import com.weather.nayibo.weather.retrofit.RetrofitHelper;
import com.weather.nayibo.weather.search.CityBean;
import com.weather.nayibo.weather.search.SearchPage;
import com.weather.nayibo.weather.stack.StackAction;
import com.weather.nayibo.weather.stack.StackManager;
import com.weather.nayibo.weather.utils.Constant;
import com.weather.nayibo.weather.utils.SharepreferenceUtil;
import com.weather.nayibo.weather.vo.WeatherBean;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by nayibo on 2018/2/22.
 */

public class CityListPageViewModel extends BaseViewModel {
    private final ObservableList<CityListPageItemViewModel> mData = new ObservableArrayList<>();
    public final ObservableField<Boolean> isEdit = new ObservableField<>(false);
    public final ObservableField<String> op = new ObservableField<>();

    public CityListPageViewModel() {
        addCity();
        if (isEdit.get()) {
            op.set("完成");
        } else {
            op.set("编辑");
        }
    }

    public ObservableList<CityListPageItemViewModel> items() {
        return mData;
    }

    public void goSearch() {
        StackManager.getInstance().startNewUI(new SearchPage(), StackAction.ADD);
    }

    public void edit() {
        if (isEdit.get()) {
            isEdit.set(false);
            op.set("编辑");
            deleteData();
            refreshData(false);
        } else {
            isEdit.set(true);
            op.set("完成");
            refreshData(true);
        }
    }

    public void onItemBound(int position) {
    }

    private void addCity() {
        mData.clear();
        Observable.just((String) SharepreferenceUtil.get(App.context, Constant.CITY_LIST_BEAN, ""))
                .flatMap(new Function<String, ObservableSource<CityBean>>() {
                    @Override
                    public ObservableSource<CityBean> apply(@NonNull String s) throws Exception {
                        ArrayList<CityBean> array = new ArrayList<>();
                        if (!TextUtils.isEmpty(s)) {
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<CityBean>>() {
                            }.getType();
                            array = gson.fromJson(s, type);
                        }
                        return Observable.fromIterable(array);
                    }
                })
                .flatMap(new Function<CityBean, ObservableSource<WeatherBean>>() {
                    @Override
                    public ObservableSource<WeatherBean> apply(@NonNull CityBean cityBean) throws Exception {
                        return RetrofitHelper.getInstance().getServer().getForecast(cityBean.getCityNameCN(), "4586858aae93481c97f73c5ebfbccb47", "cn", "m");
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WeatherBean>() {
                    @Override
                    public void accept(WeatherBean weatherBean) throws Exception {
                        mData.add(new CityListPageItemViewModel(weatherBean, isEdit.get()));
                    }
                });
    }

    private void refreshData(boolean edit) {
        for (CityListPageItemViewModel model : mData) {
            model.isEdit.set(edit);
        }
    }

    private void deleteData() {
        String json = (String) SharepreferenceUtil.get(App.context, Constant.CITY_LIST_BEAN, "");
        ArrayList<CityBean> array = new ArrayList<>();
        if (!TextUtils.isEmpty(json)) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<CityBean>>() {
            }.getType();
            array = gson.fromJson(json, type);
        }

        for (CityListPageItemViewModel model : mData) {
            if (model.isChecked.get()) {
                for (CityBean cityBean : array) {
                    if (cityBean.getCityCode().equals(model.cityCode.get())) {
                        array.remove(cityBean);
                        break;
                    }
                }
            }
        }

        Gson gson = new Gson();
        String jsonNew = gson.toJson(array);
        SharepreferenceUtil.put(App.context, Constant.CITY_LIST_BEAN, jsonNew);

        addCity();
    }
}
