package com.weather.nayibo.weather.download;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by nayibo on 2018/2/5.
 */
public abstract class DownloadObserver implements Observer<DownloadInfo> {
    private Disposable mDisposable;
    private DownloadInfo mDownloadInfo;

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        this.mDisposable = d;
    }

    @Override
    public void onNext(@NonNull DownloadInfo downloadInfo) {
        this.mDownloadInfo = downloadInfo;
    }

    @Override
    public void onError(@NonNull Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
