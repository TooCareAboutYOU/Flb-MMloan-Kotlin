package com.mmkjflb.lib.base.base

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.gyf.immersionbar.BarHide
import com.gyf.immersionbar.ktx.immersionBar
import com.mmkjflb.lib.base.HINT_EMPTY_LAYOUT
import com.mmkjflb.lib.base.R
import com.mmkjflb.lib.base.annotation.AnnoActivityLayout
import com.mmkjflb.lib.base.view.toolbar.ToolbarHelper
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity


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
    abstract fun init()

    /**
     * 布局初始化前设置
     */
    open fun createBefore() {

    }

    //隐藏虚拟按键
    private fun hideBottomUi() {
        if (Build.VERSION.SDK_INT >= 19) {
            val decorView = window.decorView
            val uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            decorView.systemUiVisibility = uiOptions
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        } else {
            window.decorView.systemUiVisibility = View.GONE
        }
    }

    /**
     * Main 页面全局初始化
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        createBefore()
        super.onCreate(savedInstanceState)
        loadInit()
    }

    /**
     * 初始化布局
     */
    private fun loadInit() {
        loadContentView()
    }

    /**
     * 加载页面布局
     */
    private fun loadContentView() {
        if (this.javaClass.isAnnotationPresent(AnnoActivityLayout::class.java)) {
            val contentView = this.javaClass.getAnnotation(AnnoActivityLayout::class.java)
                ?: throw NullPointerException(HINT_EMPTY_LAYOUT)
            mDataBinding = DataBindingUtil.setContentView(this, contentView.layoutResId)

            init()
            toolBarView = ToolbarHelper(this)
            loadBarView(false)
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

    }

}