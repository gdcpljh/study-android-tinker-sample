package com.study.tinker.sample.app;

import com.tencent.tinker.loader.TinkerLoader;
import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

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
public class SampleApplication extends TinkerApplication {

    public SampleApplication() {
        super(
                ShareConstants.TINKER_ENABLE_ALL,
                "com.study.tinker.sample.app.SampleApplicationLike",
                TinkerLoader.class.getName(),
                false
        );
    }
}
