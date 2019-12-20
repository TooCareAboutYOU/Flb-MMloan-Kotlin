package com.mmkjflb.lib.base.http

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.core.graphics.red
import com.google.gson.Gson
import com.mmkjflb.lib.base.BuildConfig
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager
import javax.net.ssl.TrustManager as TrustManager1

/**
 * @author zhangshuai
 * @date 2019-12-17 17:55
 * @description 配置网络框架
 */
class HttpModule {

    var trustAllCerts: Array<X509TrustManager>? = null

    //前提申明 私有无参的类构造函数
    companion object {
        val instance: HttpModule by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            HttpModule()
        }
    }

    fun init(context: Context,baseUrl: String): Retrofit {
        val httpLoggingInterceptor = HttpLoggingInterceptor(NetworkInterceptor())
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val cacheData = Cache(File(context.filesDir.absolutePath), 1024 * 1024 * 10)

        val allHostValid = HostnameVerifier { _, _ -> true }

        val okHttpClient: OkHttpClient = OkHttpClient.Builder().apply {
            cache(cacheData)
            connectTimeout(120, TimeUnit.SECONDS)
            readTimeout(120, TimeUnit.SECONDS)
            writeTimeout(120, TimeUnit.SECONDS)
            if (BuildConfig.DEBUG) {
                addNetworkInterceptor(httpLoggingInterceptor)
            }
            sslSocketFactory(createIgnoreVerifySSL(), trustAllCerts!![0])
            hostnameVerifier(allHostValid)
        }.build()

        return Retrofit.Builder().apply {
            baseUrl(baseUrl)
            client(okHttpClient)
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            addConverterFactory(GsonConverterFactory.create())
        }.build()
    }

    /**
     * 打印网络交互
     */
    class NetworkInterceptor :HttpLoggingInterceptor.Logger{
        override fun log(message: String) {

            if (message.startsWith("{") && message.endsWith("}")) {
                Logger.json(message)
            }else{
                Log.i("》》》",message)
            }
        }
    }


    //绕过证书
    private fun createIgnoreVerifySSL(): SSLSocketFactory {
        val sc = SSLContext.getInstance("TLS")
        trustAllCerts = arrayOf(object : X509TrustManager {
            @SuppressLint("TrustAllX509TrustManager")
            override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {
            }

            @SuppressLint("TrustAllX509TrustManager")
            override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {
            }

            override fun getAcceptedIssuers(): Array<X509Certificate?> {
                return arrayOfNulls(0)
            }
        })
        sc!!.init(null, trustAllCerts, SecureRandom())
        return sc.socketFactory
    }

}