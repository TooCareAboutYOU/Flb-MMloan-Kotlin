package com.mmkj.mmloan.view.main

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.mmkjflb.lib.base.annotation.AnnoActivityLayout
import com.mmkjflb.lib.base.annotation.AnnoToolBarLayout
import com.mmkjflb.lib.base.base.DataBindingActivity
import com.mmkj.mmloan.ArouterPath
import com.mmkj.mmloan.BR
import com.mmkj.mmloan.R
import com.mmkj.mmloan.databinding.ActivityMainBinding
import com.mmkjflb.lib.base.base.BaseActivity
import com.mmkjflb.lib.base.base.BaseViewModel
import org.koin.android.viewmodel.ext.android.viewModel

@Route(path = ArouterPath.PATH_MAIN)
@AnnoActivityLayout(layoutResId = R.layout.activity_main)
@AnnoToolBarLayout(
    layoutResId = R.layout.base_toolbar,
    toolbarBgColor = R.color.colorRed,
    centerText = R.string.app_name
)
class MainActivity : BaseActivity<ActivityMainBinding>() {

    external fun stringFromJNI(): String

    companion object {
        init {
            System.loadLibrary("native-lib")
        }
    }

    override fun bindViewModel(): BaseViewModel? {
        val model: MainViewModel by viewModel()
        model.setVariableId(this, BR.mVModel)
        return model
    }

    override fun initView(savedInstanceState: Bundle?) {

    }


}
