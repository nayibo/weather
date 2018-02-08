package com.weather.nayibo.weather.download;

/**
 * Created by nayibo on 2018/2/5.
 */

public class DownloadInfo {
    public static final long TOTAL_ERROR = -1;//获取进度失败
    private String mUrl;
    private long mTotal;
    private long mProgress;
    private String mFileName;

    public DownloadInfo(String url) {
        this.mUrl = url;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getFileName() {
        return mFileName;
    }

    public void setFileName(String fileName) {
        this.mFileName = fileName;
    }

    public long getTotal() {
        return mTotal;
    }

    public void setTotal(long total) {
        this.mTotal = total;
    }

    public long getProgress() {
        return mProgress;
    }

    public void setProgress(long progress) {
        this.mProgress = progress;
    }
}
