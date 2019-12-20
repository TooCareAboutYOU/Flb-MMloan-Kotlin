package com.mmkj.mmloan.viewmodel

import android.annotation.SuppressLint
import android.view.View
import androidx.databinding.ObservableField
import com.mmkj.mmloan.aop.annotation.SingleClick
import com.mmkj.mmloan.app.MmloanApplication
import com.mmkj.mmloan.base.BaseViewModelmpl
import com.mmkjflb.lib.base.base.BaseViewModel
import com.mmkj.mmloan.dispatchDefault
import com.mmkj.mmloan.model.entity.AppVersionBean
import com.mmkj.mmloan.model.entity.BaseBean
import com.mmkj.mmloan.repository.ApiRepository
import com.trello.rxlifecycle3.android.ActivityEvent
import org.jetbrains.anko.toast

/**
 * @author zhangshuai
 * @date 2019-12-18 17:44
 * @description  加载 LoadingView等
 */
class SplashViewModel(private val repo: ApiRepository) : BaseViewModelmpl() {


    val text = ObservableField<String>()


    @SuppressLint("CheckResult")
    @SingleClick
    fun login(view: View) {
        addSubscribe(
            repo.getInfo("android", MmloanApplication.application.packageName)
                .compose(getLifecycleProvider()?.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe {
                    if (isSuccessUnToast(it)) {
                        text.set(it.toString())
                    }
                }
        )
    }
}