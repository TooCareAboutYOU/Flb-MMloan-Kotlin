package com.mmkjflb.lib.base.base

import android.content.Context
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.alibaba.android.arouter.launcher.ARouter
import com.gyf.immersionbar.BarHide
import com.gyf.immersionbar.ktx.immersionBar
import com.mmkjflb.lib.base.HINT_EMPTY_LAYOUT
import com.mmkjflb.lib.base.R
import com.mmkjflb.lib.base.annotation.AnnoActivityLayout
import com.mmkjflb.lib.base.manager.ActivityManager
import com.mmkjflb.lib.base.view.toolbar.ToolbarHelper
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity
import org.koin.android.viewmodel.ext.android.viewModel
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper


/**
 * @author zhangshuai
 * @date 2019-12-12 16:51
 * @description
 */
abstract class DataBindingActivity<B : ViewDataBinding> : RxAppCompatActivity() {

    //ToolBar
    lateinit var toolBarView: ToolbarHelper

    //xml布局对应的DataBinding索引
    lateinit var mDataBinding: B


    /**
     * 初始化 自定义的页面布局、数据等
     */
    abstract fun initView(savedInstanceState: Bundle?)

    /**
     * ViewModel 与DataBind 绑定
     */
    open fun initViewModel(binding: ViewDataBinding) {

    }

    /**
     * 布局初始化前设置
     */
    open fun onCreateBefore() {

    }

    /**
     * 注入字体设置
     */
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    /**
     * Main 页面全局初始化
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        onCreateBefore()
        super.onCreate(savedInstanceState)
        ActivityManager.INSTANCE.add(this)
        ARouter.getInstance().inject(this)
        loadInit(savedInstanceState)

    }

    /**
     * 加载页面布局
     */
    open fun loadInit(savedInstanceState: Bundle?) {
        if (this.javaClass.isAnnotationPresent(AnnoActivityLayout::class.java)) {
            val contentView = this.javaClass.getAnnotation(AnnoActivityLayout::class.java)
                ?: throw NullPointerException(HINT_EMPTY_LAYOUT)
            mDataBinding = DataBindingUtil.setContentView(this, contentView.layoutResId)
            initViewModel(mDataBinding)
            toolBarView = ToolbarHelper(this)
            loadBarView(false)
            initView(savedInstanceState)
        }
    }


    /**
     * 默认 状态栏
     */
    open fun loadBarView(hideNav: Boolean) = immersionBar {
        //透明状态栏
        transparentStatusBar()
        //状态栏字体是深色，不写默认为亮色
        statusBarDarkFont(true)
        navigationBarColor(R.color.colorBlack)
        titleBar(R.id.baseToolbar)
        if (hideNav) {
            fullScreen(true)
            hideBar(BarHide.FLAG_HIDE_NAVIGATION_BAR)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        ActivityManager.INSTANCE.remove(this)
        mDataBinding.unbind()
        println("关闭界面-------->>>>>>>>")
    }

}