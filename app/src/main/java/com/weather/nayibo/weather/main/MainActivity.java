package com.weather.nayibo.weather.main;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.weather.nayibo.weather.BR;
import com.weather.nayibo.weather.R;
import com.weather.nayibo.weather.stack.StackManager;


public class MainActivity extends AppCompatActivity {

    private MainViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewDataBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        vm = new MainViewModel();
        binding.setVariable(BR.vm, vm);
        binding.executePendingBindings();
    }

    @Override
    public void onBackPressed() {
        if (!StackManager.getInstance().back()) {
            super.onBackPressed();
        }
    }
}