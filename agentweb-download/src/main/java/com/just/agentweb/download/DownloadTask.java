package com.just.agentweb.download;

import android.content.Context;

import com.just.agentweb.DefaultMsgConfig;
import com.just.agentweb.DownloadListener;

import java.io.File;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by cenxiaozhong on 2017/5/13.
 * source code  https://github.com/Justson/AgentWeb
 */

public class DownloadTask implements Serializable {


    private int id;
    /**
     * 下载的地址
     */
    private String url;
    /**
     * 是否强制下载不管网络类型
     */
    private boolean isForce;

    /**
     * 如否需要需要指示器
     */
    private boolean enableIndicator = true;
    /**
     * Context
     */
    private Context mContext;
    /**
     * 下载的文件
     */
    private File mFile;
    /**
     * 文件的总大小
     */
    private long length;
    /**
     * 通知的icon
     */
    private int drawableRes;

    private WeakReference<DownloadListener> mDownloadWR = null;
    private DefaultMsgConfig.DownloadMsgConfig mDownloadMsgConfig;
    /**
     * 表示当前任务是否被销毁了。
     */
    private AtomicBoolean isDestroy = new AtomicBoolean(false);


    private volatile boolean isParallelDownload = false;

    private volatile boolean isShutdown = false;

    public DownloadTask(int id, String url,
                        DownloadListener downloadListeners,
                        boolean isForce, boolean enableIndicator,
                        Context context, File file, long length,
                        DefaultMsgConfig.DownloadMsgConfig downloadMsgConfig,
                        int drawableRes, boolean isParallelDownload) {
        this.id = id;
        this.url = url;
        this.isForce = isForce;
        this.enableIndicator = enableIndicator;
        mContext = context;
        mFile = file;
        this.length = length;
        this.drawableRes = drawableRes;
        mDownloadWR = new WeakReference<DownloadListener>(downloadListeners);
        this.mDownloadMsgConfig = downloadMsgConfig;
        this.isParallelDownload = isParallelDownload;
    }

    public boolean isParallelDownload() {
        return isParallelDownload;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isForce() {
        return isForce;
    }

    public void setForce(boolean force) {
        isForce = force;
    }

    public boolean isEnableIndicator() {
        return enableIndicator;
    }

    public void setEnableIndicator(boolean enableIndicator) {
        this.enableIndicator = enableIndicator;
    }

    public WeakReference<DownloadListener> getDownloadWR() {
        return mDownloadWR;
    }

    public void setDownloadWR(WeakReference<DownloadListener> downloadWR) {
        mDownloadWR = downloadWR;
    }

    public DefaultMsgConfig.DownloadMsgConfig getDownloadMsgConfig() {
        return mDownloadMsgConfig;
    }

    public void setDownloadMsgConfig(DefaultMsgConfig.DownloadMsgConfig downloadMsgConfig) {
        mDownloadMsgConfig = downloadMsgConfig;
    }


    public void setShutdown(boolean shutdown) {
        isShutdown = shutdown;
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        mContext = context.getApplicationContext();
    }

    public File getFile() {
        return mFile;
    }

    public void setFile(File file) {
        mFile = file;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public int getDrawableRes() {
        return drawableRes;
    }

    public DownloadListener getDownloadListener() {
        return mDownloadWR.get();
    }


    public void setDrawableRes(int drawableRes) {
        this.drawableRes = drawableRes;
    }


    public void destroy() {
        isDestroy.set(true);
        this.id = -1;
        this.url = null;
        this.isForce = false;
        this.enableIndicator = false;
        mContext = null;
        mFile = null;
        this.length = -1;
        this.drawableRes = -1;
        mDownloadWR = null;
        this.mDownloadMsgConfig = null;
        this.isParallelDownload = false;
    }

    public boolean isDestroy() {
        return isDestroy.get();
    }
}
