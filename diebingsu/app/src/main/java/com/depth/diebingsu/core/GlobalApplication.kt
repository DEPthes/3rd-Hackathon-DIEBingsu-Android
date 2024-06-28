package com.depth.diebingsu.core

import android.app.Application
import com.depth.diebingsu.BuildConfig
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication: Application() {
    override fun onCreate() {
        super.onCreate()

//        var keyHash = Utility.getKeyHash(this)
//        LoggerUtils.i(keyHash)

        KakaoSdk.init(this, BuildConfig.KAKAO_NATIVE_KEY)
    }
}