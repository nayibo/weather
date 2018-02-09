package com.weather.nayibo.weather.utils;

/**
 * Created by nayibo on 2018/2/8.
 */

public class CityBean {
    private String cityCode;
    private String cityNameEN;
    private String cityNameCN;
    private String countryCode;
    private String CountryNameEN;
    private String CountryNameCN;
    private String provinceNameEN;
    private String provinceCN;
    private String parentCityEN;
    private String parentCityCN;
    private String latitude;
    private String longitude;

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityNameEN() {
        return cityNameEN;
    }

    public void setCityNameEN(String cityNameEN) {
        this.cityNameEN = cityNameEN;
    }

    public String getCityNameCN() {
        return cityNameCN;
    }

    public void setCityNameCN(String cityNameCN) {
        this.cityNameCN = cityNameCN;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryNameEN() {
        return CountryNameEN;
    }

    public void setCountryNameEN(String countryNameEN) {
        CountryNameEN = countryNameEN;
    }

    public String getCountryNameCN() {
        return CountryNameCN;
    }

    public void setCountryNameCN(String countryNameCN) {
        CountryNameCN = countryNameCN;
    }

    public String getProvinceNameEN() {
        return provinceNameEN;
    }

    public void setProvinceNameEN(String provinceNameEN) {
        this.provinceNameEN = provinceNameEN;
    }

    public String getProvinceCN() {
        return provinceCN;
    }

    public void setProvinceCN(String provinceCN) {
        this.provinceCN = provinceCN;
    }

    public String getParentCityEN() {
        return parentCityEN;
    }

    public void setParentCityEN(String parentCityEN) {
        this.parentCityEN = parentCityEN;
    }

    public String getParentCityCN() {
        return parentCityCN;
    }

    public void setParentCityCN(String parentCityCN) {
        this.parentCityCN = parentCityCN;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
