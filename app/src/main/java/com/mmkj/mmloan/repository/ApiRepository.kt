package com.mmkj.mmloan.repository

import com.mmkj.mmloan.api.ApiService
import com.mmkj.mmloan.dispatchDefault
import com.mmkj.mmloan.model.entity.AppVersionBean
import com.mmkj.mmloan.model.entity.BaseBean
import io.reactivex.Observable

/**
 * @author zhangshuai
 * @date 2019-12-19 15:03
 * @description
 */
class ApiRepository(private val remote: ApiService) :ApiService{

    override fun getInfo(
        platformType: String,
        packageType: String
    ): Observable<BaseBean<Any?>> {
        return remote.getInfo(platformType, packageType).dispatchDefault()
    }


}