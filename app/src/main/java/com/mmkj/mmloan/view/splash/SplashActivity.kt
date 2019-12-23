package com.mmkj.mmloan.view.splash

import android.annotation.SuppressLint
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.mmkjflb.lib.base.annotation.AnnoActivityLayout
import com.mmkj.mmloan.ArouterPath
import com.mmkj.mmloan.BR
import com.mmkj.mmloan.R
import com.mmkj.mmloan.databinding.ActivitySplashBinding
import com.mmkjflb.lib.base.base.BaseActivity
import org.koin.android.viewmodel.ext.android.viewModel

@Route(path = ArouterPath.PATH_SPLASH)
@AnnoActivityLayout(layoutResId = R.layout.activity_splash)
class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override fun onCreateBefore() {
        setTheme(R.style.AppTheme)
    }

    @SuppressLint("CheckResult")
    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun bindViewModel(): SplashViewModel {
        val model: SplashViewModel by viewModel()
        model.setVariableId(this, BR.model)
        return model
    }


}
