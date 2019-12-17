package com.mmkjflb.mmloan.ui.splash

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.mmkjflb.lib.base.annotation.AnnoActivityLayout
import com.mmkjflb.lib.base.base.DataBindingActivity
import com.mmkjflb.lib.base.mmkv
import com.mmkjflb.lib.base.rx.RxClick
import com.mmkjflb.mmloan.PATH_MAIN
import com.mmkjflb.mmloan.PATH_SPLASH
import com.mmkjflb.mmloan.R
import com.mmkjflb.mmloan.databinding.ActivitySplashBinding
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.toast


@Route(path = PATH_SPLASH)
@AnnoActivityLayout(layoutResId = R.layout.activity_splash)
class SplashActivity : DataBindingActivity<ActivitySplashBinding>() {

    override fun createBefore() {
        super.createBefore()
        setTheme(R.style.AppTheme)
    }

    @SuppressLint("CheckResult")
    override fun init() {
        mmkv.encode("main", "哈哈哈")
        RxClick.singleClick(acTvJump).subscribe {
            toast(mmkv.decodeString("main"))
            ARouter.getInstance().build(PATH_MAIN).navigation()
        }
    }
}
