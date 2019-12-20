package com.mmkj.mmloan.view.splash

import android.annotation.SuppressLint
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.mmkjflb.lib.base.annotation.AnnoActivityLayout
import com.mmkj.mmloan.ArouterPath
import com.mmkj.mmloan.BR
import com.mmkj.mmloan.R
import com.mmkj.mmloan.databinding.ActivitySplashBinding
import com.mmkj.mmloan.viewmodel.SplashViewModel
import com.mmkjflb.lib.base.base.BaseActivity
import org.koin.android.viewmodel.ext.android.viewModel

@Route(path = ArouterPath.PATH_SPLASH)
@AnnoActivityLayout(layoutResId = R.layout.activity_splash)
class SplashActivity : BaseActivity<ActivitySplashBinding,SplashViewModel>() {

    private val mViewModel: SplashViewModel by viewModel()

    override fun onCreateBefore() {
       setTheme(R.style.AppTheme)
    }

    @SuppressLint("CheckResult")
    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun bindViewModel(): SplashViewModel { return mViewModel
    }

    override fun variableId(): Int =BR.model


}
