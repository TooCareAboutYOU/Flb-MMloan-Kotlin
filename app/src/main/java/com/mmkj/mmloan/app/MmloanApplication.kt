package com.mmkj.mmloan.app

import com.mmkj.mmloan.BuildConfig
import com.mmkj.mmloan.di.appModule
import com.mmkjflb.lib.base.Init
import com.mmkjflb.lib.base.app.BaseApplication
import com.mmkjflb.lib.base.uploadQiNiu
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.qiniu.android.common.FixedZone
import com.qiniu.android.storage.Configuration
import org.koin.android.ext.android.startKoin
import org.koin.android.logger.AndroidLogger

/**
 * @author zhangshuai
 * @date 2019-12-09 10:57
 */

class MmloanApplication : BaseApplication() {

    companion object {
        lateinit var application: MmloanApplication
    }

    override fun onCreate() {
        super.onCreate()
        application = this

        Init.getInstance(this).initArouter().initMmkv().initFont()

        startKoin(this, appModule, logger = AndroidLogger(showDebug = BuildConfig.DEBUG))

    }


}