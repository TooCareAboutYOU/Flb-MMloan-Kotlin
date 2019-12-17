package com.mmkjflb.mmloan.app

import com.mmkjflb.lib.base.Init
import com.mmkjflb.lib.base.app.BaseApplication

/**
 * @author zhangshuai
 * @date 2019-12-09 10:57
 */

class MmloanApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        Init.getInstance(this).initArouter().initMmkv()
    }
}