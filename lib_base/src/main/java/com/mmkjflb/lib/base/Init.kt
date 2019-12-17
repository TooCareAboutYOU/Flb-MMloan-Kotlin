package com.mmkjflb.lib.base

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.tencent.mmkv.MMKV

/**
 * @author zhangshuai
 * @date 2019-12-17 14:01
 * @description 全局架构初始化
 */
class Init private constructor(app: Application) {

    private var application: Application? = app

    //前提申明 类的构造函数
    companion object {

        @Volatile
        private var instance: Init? = null

        fun getInstance(application: Application): Init {
            return instance ?: synchronized(this) {
                instance ?: Init(application).also { instance = it }
            }
        }
    }

    /**
     * 初始化路由
     */
    fun initArouter(): Init {
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
            // 打印日志的时候打印线程堆栈
            ARouter.printStackTrace()
        }
        ARouter.init(application)
        return this
    }

    /**
     * 初始化轻量级数据存储
     */
    fun initMmkv(): Init {
        MMKV.initialize(application?.applicationContext)
        return this
    }
}

/**
 * 获取保存对象实例
 */
val mmkv by lazy { MMKV.defaultMMKV() }
