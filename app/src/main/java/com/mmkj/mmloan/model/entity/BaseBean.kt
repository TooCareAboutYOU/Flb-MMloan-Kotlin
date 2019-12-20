package com.mmkj.mmloan.model.entity

import androidx.annotation.Nullable
import com.google.gson.Gson
import java.io.Serializable

data class BaseBean<T :Any?>(
    val msg: String? = "",
    val code: Int? = -1,
    val data: T? = null
) : Serializable{
    override fun toString(): String {
        return Gson().toJson(this)
    }
}
