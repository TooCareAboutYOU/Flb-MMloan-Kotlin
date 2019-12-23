package com.mmkj.mmloan.base

import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import com.mmkj.mmloan.app.MmloanApplication
import com.mmkjflb.lib.base.base.BaseViewModel
import com.mmkj.mmloan.model.entity.BaseBean
import org.jetbrains.anko.toast

/**
 * @author zhangshuai
 * @date 2019-12-19 16:26
 * @description
 */
open class BaseViewModelmpl : BaseViewModel(){

    fun isSuccessToast(baseBean: BaseBean<Any?>): Boolean {
        when (baseBean.code) {
            1 -> {
                MmloanApplication.application.toast(baseBean.msg.toString())
                return true
            }
            9999 -> {
                showHintLoginDialog()
            }
            else -> {
                MmloanApplication.application.toast(baseBean.msg.toString())
            }
        }
        return false
    }

    fun isSuccessUnToast(baseBean: BaseBean<Any?>): Boolean {
        when (baseBean.code) {
            1 -> {
                return true
            }
            9999 -> {
                showHintLoginDialog()
            }
            else -> {
                MmloanApplication.application.toast(baseBean.msg.toString())
            }
        }
        return false
    }

    private var builder: AlertDialog.Builder? = null
    fun showHintLoginDialog() {
        if (builder == null) {
            builder = AlertDialog.Builder(MmloanApplication.application).apply {
                setTitle("Login Error")
                setMessage("Login again")
                setPositiveButton("Confirm", DialogInterface.OnClickListener { dialog, which ->

                })
                setCancelable(false)
                create().setCanceledOnTouchOutside(false)
                show()
            }
        }
    }

}