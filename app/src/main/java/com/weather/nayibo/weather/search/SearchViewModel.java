package com.weather.nayibo.weather.search;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.text.TextUtils;

import com.weather.nayibo.weather.base.BaseViewModel;
import com.weather.nayibo.weather.utils.Constant;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * Created by nayibo on 2018/2/9.
 */

public class SearchViewModel extends BaseViewModel {
    public final ObservableField<Boolean> suggestVisibility = new ObservableField<>();
    private final ObservableList<SearchSuggestItemViewModel> mData = new ObservableArrayList<>();

    public SearchViewModel() {
        suggestVisibility.set(false);
    }

    public void textChanged(String text) {
        if (TextUtils.isEmpty(text)) {
            suggestVisibility.set(true);
        } else {
            suggestVisibility.set(false);
            mData.clear();
            addSuggestCity(text, new Consumer<CityBean>() {
                @Override
                public void accept(CityBean cityBean) throws Exception {
                    mData.add(new SearchSuggestItemViewModel(cityBean));
                }
            });
        }
    }

    public ObservableList<SearchSuggestItemViewModel> items() {
        return mData;
    }

    public void onItemBound(int position) {
    }

    public void addSuggestCity(final String str, final Consumer<CityBean> consumer) {
        Observable.fromIterable(Constant.getCityBeans())
                .filter(new Predicate<CityBean>() {
                    @Override
                    public boolean test(@NonNull CityBean cityBean) throws Exception {
                        return cityBean.getCityNameCN().contains(str) || cityBean.getCityNameEN().contains(str);
                    }
                })
                .subscribe(consumer);
    }
}
