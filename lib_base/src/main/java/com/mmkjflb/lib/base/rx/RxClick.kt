package com.mmkjflb.lib.base.rx

import android.view.View
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.Observable
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * @author zhangshuai
 * @date 2019-12-17 15:00
 * @description
 */
class RxClick {

    companion object {
        fun singleClick(view: View): Observable<Unit> {
            return view.clicks().throttleFirst(1, TimeUnit.SECONDS)
        }
    }
}