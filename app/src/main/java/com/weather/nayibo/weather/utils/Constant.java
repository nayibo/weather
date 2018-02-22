package com.weather.nayibo.weather.utils;

import com.weather.nayibo.weather.search.CityBean;

import java.util.ArrayList;

/**
 * Created by nayibo on 2018/2/9.
 */

public class Constant {
    private static ArrayList<CityBean> cityBeans = new ArrayList<>();
    private static ArrayList<CityBean> cityList = new ArrayList<>();
    public static String CITY_LIST_DOWNLOAD_COMPLETE = "city_list_download_complete";
    public static String CITY_LIST_URL = "https://cdn.heweather.com/china-city-list.txt";
    public static String CITY_LIST_FILE_NAME = "china-city-list.txt";

    public static ArrayList<CityBean> getCityBeans() {
        return cityBeans;
    }

    public static ArrayList<CityBean> getCityList() {
        return cityList;
    }
}
