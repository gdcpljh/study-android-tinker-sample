package com.study.tinker.bugly.app;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.study.tinker.bugly.util.BuglyManager;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.download.DownloadListener;
import com.tencent.bugly.beta.download.DownloadTask;
import com.tencent.bugly.beta.upgrade.UpgradeStateListener;
import com.tencent.bugly.crashreport.CrashReport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * <pre>
 *
 *      作 者 :        ASLai(laijianhua@ruixiude.com).
 *      日 期 :        2020/5/14
 *      版 本 :        V1.0
 *      描 述 :        description
 *
 *
 * </pre>
 *
 * @author ASLai
 */
public class SampleBuglyApplication extends Application {

    private static final String TAG = "Study.Application";

    @Override
    public void onCreate() {
        super.onCreate();

        initBugly();
    }

    private void initBugly() {
        Context context = getApplicationContext();
        // 获取当前包名
        String packageName = context.getPackageName();
        // 获取当前进程名
        String processName = getProcessName(android.os.Process.myPid());
        // 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));

        //监听安装包下载状态
        Beta.downloadListener = new DownloadListener() {
            @Override
            public void onReceive(DownloadTask downloadTask) {
                Log.d(TAG,"downloadListener receive apk file");
            }

            @Override
            public void onCompleted(DownloadTask downloadTask) {
                Log.d(TAG,"downloadListener download apk file success");
            }

            @Override
            public void onFailed(DownloadTask downloadTask, int i, String s) {
                Log.d(TAG,"downloadListener download apk file fail");
            }
        };

        //监听APP升级状态
        Beta.upgradeStateListener = new UpgradeStateListener(){
            @Override
            public void onUpgradeFailed(boolean b) {
                Log.d(TAG,"upgradeStateListener upgrade fail");
            }

            @Override
            public void onUpgradeSuccess(boolean b) {
                Log.d(TAG,"upgradeStateListener upgrade success");
            }

            @Override
            public void onUpgradeNoVersion(boolean b) {
                Log.d(TAG,"upgradeStateListener upgrade has no new version");
            }

            @Override
            public void onUpgrading(boolean b) {
                Log.d(TAG,"upgradeStateListener upgrading");
            }

            @Override
            public void onDownloadCompleted(boolean b) {
                Log.d(TAG,"upgradeStateListener download apk file success");
            }
        };

        // 初始化Bugly
        Bugly.init(this, BuglyManager.APP_ID, true);
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }
}
