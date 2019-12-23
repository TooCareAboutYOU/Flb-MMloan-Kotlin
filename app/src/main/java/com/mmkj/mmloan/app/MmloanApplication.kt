package com.mmkj.mmloan.app

import com.mmkj.mmloan.BuildConfig
import com.mmkj.mmloan.di.appModule
import com.mmkjflb.lib.base.Init
import com.mmkjflb.lib.base.app.BaseApplication
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
        startKoin(this, appModule, logger = AndroidLogger(showDebug = BuildConfig.DEBUG))

    }


}