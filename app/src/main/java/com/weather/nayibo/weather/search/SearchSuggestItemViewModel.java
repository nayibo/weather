package com.weather.nayibo.weather.search;

import android.databinding.ObservableField;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.weather.nayibo.weather.App;
import com.weather.nayibo.weather.base.BaseViewModel;
import com.weather.nayibo.weather.stack.StackManager;
import com.weather.nayibo.weather.utils.Constant;
import com.weather.nayibo.weather.utils.SharepreferenceUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by nayibo on 2018/2/9.
 */

public class SearchSuggestItemViewModel extends BaseViewModel {
    public final ObservableField<String> cityName = new ObservableField<>();
    private CityBean cityBean;

    public SearchSuggestItemViewModel(CityBean model) {
        this.cityBean = model;
        cityName.set(model.getCityNameCN());
    }

    public void selectItem() {
        String cityListJson = (String) SharepreferenceUtil.get(App.context, Constant.CITY_LIST_BEAN, "");

        ArrayList<CityBean> array = new ArrayList<>();

        if (!TextUtils.isEmpty(cityListJson)) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<CityBean>>() {
            }.getType();
            array = gson.fromJson(cityListJson, type);
        }

        boolean flag = false;
        for (CityBean city : array) {
            if (city.getCityCode().equals(cityBean.getCityCode())) {
                flag = true;
            }
        }

        if (!flag) {
            array.add(cityBean);
        }

        Gson gson = new Gson();
        String json = gson.toJson(array);
        SharepreferenceUtil.put(App.context, Constant.CITY_LIST_BEAN, json);

        StackManager.getInstance().back();
    }
}
