package com.weather.nayibo.weather.download;

import com.weather.nayibo.weather.App;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by nayibo on 2018/2/5.
 */

public class DownloadManager {
    private static final AtomicReference<DownloadManager> INSTANCE = new AtomicReference<>();
    private HashMap<String, Call> mDownloadCalls;
    private OkHttpClient mClient;

    public static DownloadManager getInstance() {
        for (; ; ) {
            DownloadManager current = INSTANCE.get();
            if (current != null) {
                return current;
            }
            current = new DownloadManager();
            if (INSTANCE.compareAndSet(null, current)) {
                return current;
            }
            return current;
        }
    }

    private DownloadManager() {
        mDownloadCalls = new HashMap<>();
        OkHttpClient.Builder mBuilder = new OkHttpClient.Builder();
        mBuilder.sslSocketFactory(TrustAllCerts.createSSLSocketFactory());
        mBuilder.hostnameVerifier(new TrustAllCerts.TrustAllHostnameVerifier());
        mClient = mBuilder.build();
    }

    public void startDownload(String url, DownloadObserver downloadObserver) {
        Observable.just(url)
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(@NonNull String s) throws Exception {
                        return !mDownloadCalls.containsKey(s);
                    }
                })
                .flatMap(new Function<String, ObservableSource<DownloadInfo>>() {
                    @Override
                    public ObservableSource<DownloadInfo> apply(@NonNull String s) throws Exception {
                        return Observable.just(createDownloadInfo(s));
                    }
                })
                .map(new Function<DownloadInfo, DownloadInfo>() {
                    @Override
                    public DownloadInfo apply(@NonNull DownloadInfo downloadInfo) throws Exception {
                        return getRealFileName(downloadInfo);
                    }
                })
                .flatMap(new Function<DownloadInfo, ObservableSource<DownloadInfo>>() {
                    @Override
                    public ObservableSource<DownloadInfo> apply(@NonNull DownloadInfo downloadInfo) throws Exception {
                        return Observable.create(new DownloadSubscribe(downloadInfo));
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(downloadObserver);

    }

    public void cancel(String url) {
        Call down = mDownloadCalls.get(url);
        if (down != null) {
            down.cancel();
        }
        mDownloadCalls.remove(url);
    }

    private DownloadInfo createDownloadInfo(String url) {
        DownloadInfo downloadInfo = new DownloadInfo(url);
        long contentLength = getContentLength(url);
        downloadInfo.setTotal(contentLength);
        String fileName = url.substring(url.lastIndexOf("/"));
        downloadInfo.setFileName(fileName);
        return downloadInfo;
    }

    private DownloadInfo getRealFileName(DownloadInfo downloadInfo) {
        String fileName = downloadInfo.getFileName();
        long downloadLength = 0, contentLength = downloadInfo.getTotal();
        File file = new File(App.context.getFilesDir(), fileName);
        if (file.exists()) {
            downloadLength = file.length();
        }
        int i = 1;
        while (downloadLength != 0 && downloadLength >= contentLength) {
            int dotIndex = fileName.lastIndexOf(".");
            String fileNameOther;
            if (dotIndex == -1) {
                fileNameOther = fileName + "(" + i + ")";
            } else {
                fileNameOther = fileName.substring(0, dotIndex)
                        + "(" + i + ")" + fileName.substring(dotIndex);
            }
            File newFile = new File(App.context.getFilesDir(), fileNameOther);
            file = newFile;
            downloadLength = newFile.length();
            i++;
        }
        downloadInfo.setProgress(downloadLength);
        downloadInfo.setFileName(file.getName());
        return downloadInfo;
    }

    private long getContentLength(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = mClient.newCall(request).execute();
            if (response != null && response.isSuccessful()) {
                long contentLength = response.body().contentLength();
                response.close();
                return contentLength == 0 ? DownloadInfo.TOTAL_ERROR : contentLength;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return DownloadInfo.TOTAL_ERROR;
    }

    public class DownloadSubscribe implements ObservableOnSubscribe<DownloadInfo> {
        DownloadInfo mDownloadInfo;

        public DownloadSubscribe(DownloadInfo downloadInfo) {
            this.mDownloadInfo = downloadInfo;
        }

        @Override
        public void subscribe(@NonNull ObservableEmitter<DownloadInfo> emitter) throws Exception {
            String url = mDownloadInfo.getUrl();
            long downProgress = mDownloadInfo.getProgress();
            long contentLength = mDownloadInfo.getTotal() >= 0 ? mDownloadInfo.getTotal() : 0;

            emitter.onNext(mDownloadInfo);

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Call call = mClient.newCall(request);
            mDownloadCalls.put(url, call);//把这个添加到call里,方便取消
            Response response = call.execute();

            File file = new File(App.context.getExternalCacheDir(), mDownloadInfo.getFileName());
            InputStream is = null;
            FileOutputStream fileOutputStream = null;

            try {
                is = response.body().byteStream();
                fileOutputStream = new FileOutputStream(file, true);
                byte[] buffer = new byte[2048];//缓冲数组2kB
                int len;
                while ((len = is.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, len);
                    downProgress += len;
                    mDownloadInfo.setProgress(downProgress);
                    emitter.onNext(mDownloadInfo);
                }
                fileOutputStream.flush();
                mDownloadCalls.remove(url);
            } finally {
                //关闭IO流
                fileOutputStream.close();
                is.close();
            }
            emitter.onComplete();//完成
        }
    }
}
