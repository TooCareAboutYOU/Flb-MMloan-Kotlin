package com.mmkj.mmloan.api

import com.mmkjflb.lib.base.BuildConfig
import com.mmkjflb.lib.base.Init
import com.mmkj.mmloan.app.MmloanApplication
import com.mmkj.mmloan.model.entity.AppVersionBean
import com.mmkj.mmloan.model.entity.BaseBean
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * @author zhangshuai
 * @date 2019-12-17 18:39
 * @description 网络接口
 */

val apiService: ApiService by lazy {
    val url: String =
        if (BuildConfig.DEBUG) "http://114.55.90.111:8433/" else "https://flbapi.358fintech.com/"
    Init.getInstance(MmloanApplication.application).initRetrofit(url, ApiService::class.java)
}

interface ApiService {

    @FormUrlEncoded
    @POST("app/getAppVersion")
    fun getInfo(@Field("platformType") platformType: String, @Field("packageType") packageType: String): Observable<BaseBean<Any?>>

}