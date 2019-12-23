package com.mmkjflb.lib.base.base

import androidx.databinding.ViewDataBinding

/**
 * @author zhangshuai
 * @date 2019-12-19 10:59
 * @description 数据与UI 绑定
 */
abstract class BaseActivity<B : ViewDataBinding> : DataBindingActivity<B>() {

    private var mViewModel: BaseViewModel? = null

    abstract fun bindViewModel(): BaseViewModel?

    override fun initViewModel(binding: ViewDataBinding) {
        super.initViewModel(binding)
        if (bindViewModel() != null) {
            mViewModel = bindViewModel()
            binding.setVariable(mViewModel!!.getVariableId(), mViewModel)
            mViewModel?.injectLifecycleProvider(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bindViewModel()?.clearSubscribe()
    }

}