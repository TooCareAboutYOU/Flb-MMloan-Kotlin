package com.mmkjflb.lib.base.base

import androidx.lifecycle.ViewModel
import com.trello.rxlifecycle3.LifecycleProvider
import com.trello.rxlifecycle3.android.ActivityEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.lang.ref.WeakReference

/**
 * @author zhangshuai
 * @date 2019-12-19 10:29
 * @description
 */
open class BaseViewModel : ViewModel() {

    //弱引用持有
    private lateinit var mLifeCycle: WeakReference<LifecycleProvider<ActivityEvent>>

    //管理RxJava，主要针对RxJava异步操作造成的内存泄漏
    private var mCompositeDisposable: CompositeDisposable? = null

    /**
     * 注入RxLifeCycle生命周期
     */
    fun injectLifecycleProvider(lifecycle: LifecycleProvider<ActivityEvent>) {
        this.mLifeCycle = WeakReference(lifecycle)
    }

    fun getLifecycleProvider(): LifecycleProvider<ActivityEvent>? = this.mLifeCycle.get()


    fun addSubscribe(disposable: Disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = CompositeDisposable()
        }
        mCompositeDisposable?.add(disposable)
    }


    fun removeSubscribe() {
        mCompositeDisposable?.clear()
    }


}