package com.mmkj.mmloan

import com.mmkjflb.lib.base.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author zhangshuai
 * @date 2019-12-18 09:25
 * @description 工具类
 */

fun <T> Observable<T>.dispatchDefault(): Observable<T> =
    this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .unsubscribeOn(Schedulers.io())
        .subscribeOn(AndroidSchedulers.mainThread())



