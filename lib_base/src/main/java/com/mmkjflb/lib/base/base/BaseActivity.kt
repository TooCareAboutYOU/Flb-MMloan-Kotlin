package com.mmkjflb.lib.base.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * @author zhangshuai
 * @date 2019-12-19 10:59
 * @description 数据与UI 绑定
 */
abstract class BaseActivity<B : ViewDataBinding, VM : BaseViewModel> : DataBindingActivity<B>() {

    private lateinit var mViewModel: VM

    abstract fun bindViewModel(): VM

    abstract fun variableId(): Int


    override fun initViewModel(binding: ViewDataBinding) {
        super.initViewModel(binding)
        mViewModel = bindViewModel()
        binding.setVariable(variableId(), bindViewModel())
        mViewModel.injectLifecycleProvider(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mViewModel.removeSubscribe()
    }

}