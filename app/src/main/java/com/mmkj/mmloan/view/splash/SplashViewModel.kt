package com.mmkj.mmloan.view.splash

import android.annotation.SuppressLint
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.alibaba.android.arouter.launcher.ARouter
import com.mmkj.mmloan.ArouterPath
import com.mmkj.mmloan.aop.annotation.SingleClick
import com.mmkj.mmloan.app.MmloanApplication
import com.mmkj.mmloan.base.BaseViewModelmpl
import com.mmkj.mmloan.repository.ApiRepository
import com.mmkj.mmloan.view.splash.SplashActivity
import com.mmkjflb.lib.base.manager.ActivityManager
import com.trello.rxlifecycle3.android.ActivityEvent
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * @author zhangshuai
 * @date 2019-12-18 17:44
 * @description  处理数据与View交互
 */
class SplashViewModel(private val repo: ApiRepository) : BaseViewModelmpl() {

    val text = ObservableField<String>()
    val isShow = ObservableBoolean()

    @SuppressLint("CheckResult")
    @SingleClick
    fun login(view: View) {
        addSubscribe(
                repo.getInfo("android", MmloanApplication.application.packageName)
                    .compose(getLifecycleProvider()?.bindToLifecycle())
                    .subscribe({
                        if (isSuccessUnToast(it)) {
                            text.set(it.toString())
                            isShow.set(true)
                        }
                    }, {
                    }, {

                    })
                )
    }

    @SingleClick
    fun SecondPage(view: View) {
        jumpActivity(ArouterPath.PATH_MAIN)
//        finish()
    }
}