package com.mmkjflb.lib.base.base

import android.content.Context
import androidx.lifecycle.ViewModel
import com.alibaba.android.arouter.launcher.ARouter
import com.trello.rxlifecycle3.LifecycleProvider
import com.trello.rxlifecycle3.android.ActivityEvent
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.lang.ref.WeakReference

/**
 * @author zhangshuai
 * @date 2019-12-19 10:29
 * @description
 */
open class BaseViewModel : ViewModel() {

    private var mContext: Context? = null

    private var variableId: Int = -1

    //弱引用持有
    private lateinit var mLifeCycle: WeakReference<LifecycleProvider<ActivityEvent>>

    //管理RxJava，主要针对RxJava异步操作造成的内存泄漏
    private var mCompositeDisposable: CompositeDisposable? = null


    /**
     * 注入RxLifeCycle生命周期
     * 方式一
     */
    fun injectLifecycleProvider(lifecycle: LifecycleProvider<ActivityEvent>) {
        this.mLifeCycle = WeakReference(lifecycle)
    }

    fun getLifecycleProvider(): LifecycleProvider<ActivityEvent>? = this.mLifeCycle.get()

    /**
     * Rx内存释放
     * 方式二
     */
    fun addSubscribe(disposable: Disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = CompositeDisposable()
        }
        mCompositeDisposable?.add(disposable)
    }

    fun clearSubscribe() {
        mCompositeDisposable?.clear()
        mContext = null
    }

    fun getContext() = mContext as RxAppCompatActivity

    /**
     * 设置部署绑定的ViewModel
     */
    fun setVariableId(context: Context, id: Int) {
        this.variableId = id
        mContext = context
    }

    /**
     * DataBing与model绑定
     */
    fun getVariableId(): Int {
        if (variableId == -1) {
            throw NullPointerException("the viewModel is not set for XXX.xml file")
        }
        return variableId
    }


    /**
     * 关闭当前Activity
     */
    fun finish() {
        getContext().finish()
    }

    fun jumpActivity(path: String) {
        ARouter.getInstance().build(path).navigation()
    }


}