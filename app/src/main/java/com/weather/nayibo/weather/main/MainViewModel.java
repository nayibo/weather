package com.weather.nayibo.weather.main;

import android.databinding.ObservableField;
import android.util.Log;

import com.weather.nayibo.weather.base.BaseViewModel;
import com.weather.nayibo.weather.base.MvvmComponent;
import com.weather.nayibo.weather.homepage.Homepage;
import com.weather.nayibo.weather.stack.StackAction;
import com.weather.nayibo.weather.stack.StackManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by nayibo on 2018/1/24.
 */

public class MainViewModel extends BaseViewModel {
    public final ObservableField<MvvmComponent> mainComponent = new ObservableField<>();

    @Override
    public void onAttach() {
        super.onAttach();
        EventBus.getDefault().register(this);
//        mainComponent.set(new Homepage());
        StackManager.getInstance().startNewUI(new Homepage(), StackAction.ADD);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onMessageEvent(MvvmComponent component) {
        Log.d("nayibo", "onMessageEvent： " + component.toString());
        mainComponent.set(component);
    }
}
