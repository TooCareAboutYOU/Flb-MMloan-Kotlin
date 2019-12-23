package com.mmkjflb.lib.base.app

import android.app.Application
import com.mmkjflb.lib.base.Init

/**
 * @author zhangshuai
 * @date 2019-12-09 10:56
 */

open class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Init.getInstance(this).initArouter().initMmkv().initFont()
    }
}