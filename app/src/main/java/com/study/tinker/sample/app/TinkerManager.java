package com.study.tinker.sample.app;

import com.tencent.tinker.entry.ApplicationLike;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.lib.util.TinkerLog;

/**
 * <pre>
 *
 *      作 者 :        ASLai(laijianhua@ruixiude.com).
 *      日 期 :        2020/5/11
 *      版 本 :        V1.0
 *      描 述 :        description
 *
 *
 * </pre>
 *
 * @author ASLai
 */
public class TinkerManager {

    private static final String TAG = "Tinker.TinkerManager";

    private static ApplicationLike applicationLike;

    private static boolean isInstalled = false;

    public static void setTinkerApplicationLike(ApplicationLike appLike) {
        applicationLike = appLike;
    }

    public static ApplicationLike getTinkerApplicationLike() {
        return applicationLike;
    }

    public static void installTinker(ApplicationLike appLike) {
        if (isInstalled) {
            TinkerLog.w(TAG, "install tinker, but has installed, ignore");
            return;
        }

        TinkerInstaller.install(appLike);

        isInstalled = true;
    }


}
