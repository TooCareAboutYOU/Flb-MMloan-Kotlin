package com.mmkjflb.mmloan.ui.main

import com.alibaba.android.arouter.facade.annotation.Route
import com.mmkjflb.lib.base.annotation.AnnoActivityLayout
import com.mmkjflb.lib.base.annotation.AnnoToolBarLayout
import com.mmkjflb.lib.base.base.DataBindingActivity
import com.mmkjflb.mmloan.PATH_MAIN
import com.mmkjflb.mmloan.R
import com.mmkjflb.mmloan.databinding.ActivityMainBinding

@Route(path = PATH_MAIN)
@AnnoActivityLayout(layoutResId = R.layout.activity_main)
@AnnoToolBarLayout(
    layoutResId = R.layout.base_toolbar,
    toolbarBgColor = R.color.colorRed,
    centerText = R.string.app_name
)
class MainActivity : DataBindingActivity<ActivityMainBinding>() {

    external fun stringFromJNI(): String

    companion object {
        init {
            System.loadLibrary("native-lib")
        }
    }

    override fun init() {
        mDataBinding.sampleText.text = "哈哈哈哈"
    }

}
