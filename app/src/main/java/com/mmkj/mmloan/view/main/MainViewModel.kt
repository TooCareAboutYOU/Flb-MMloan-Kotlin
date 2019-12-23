package com.mmkj.mmloan.view.main

import android.view.View
import com.mmkj.mmloan.aop.annotation.SingleClick
import com.mmkj.mmloan.base.BaseViewModelmpl
import com.mmkj.mmloan.repository.ApiRepository
import com.mmkj.mmloan.view.splash.SplashActivity
import com.mmkjflb.lib.base.manager.ActivityManager

/**
 * @author zhangshuai
 * @date 2019-12-23 17:02
 * @description
 */
class MainViewModel(private val repo:ApiRepository):BaseViewModelmpl() {


    @SingleClick
    fun finishSplash(view : View){
        ActivityManager.INSTANCE.removeByClass(SplashActivity::class.java)
    }

}