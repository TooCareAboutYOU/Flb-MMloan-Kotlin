package com.mmkjflb.lib.base

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.mmkjflb.lib.base.http.HttpModule
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.qiniu.android.common.FixedZone
import com.qiniu.android.storage.Configuration
import com.qiniu.android.storage.UploadManager
import com.tencent.mmkv.MMKV
import retrofit2.Retrofit
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

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
            //日志初始化
            Logger.addLogAdapter(AndroidLogAdapter())
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

    /**
     * 初始化接口类
     */
    fun <T> initRetrofit(baseUrl: String, clazz: Class<T>): T {
        return HttpModule.instance.init(application!!.applicationContext, baseUrl).create(clazz)
    }

    /**
     * 初始化字体
     */
    fun initFont(): Init {
        CalligraphyConfig.initDefault(
            CalligraphyConfig.Builder().let {
                it.setDefaultFontPath("fonts/DroidSansFallback.ttf")
                it.setFontAttrId(R.attr.fontPath)
                it.build()
            }
        )
        return this
    }
}

/**
 * 必须先调用全局初始化
 * 获取保存对象实例
 */
val mmkv by lazy { MMKV.defaultMMKV() }

val uploadQiNiu by lazy {
    val configuration = Configuration.Builder().let {
        it.connectTimeout(30)
        it.useHttps(true)
        it.responseTimeout(30)
        it.zone(FixedZone.zoneAs0)
        it.build()
    }
    UploadManager(configuration)
}