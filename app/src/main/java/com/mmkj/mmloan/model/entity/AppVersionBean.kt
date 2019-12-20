package com.mmkj.mmloan.model.entity

import com.google.gson.Gson
import java.io.Serializable

data class AppVersionBean(
    val id: Int? = 0,
    val isForceUpdate: Boolean? = false,
    val latestVersion: String? = "",
    val platformType: String? = "",
    val paymentEnable: Any? = Any(),
    val createdTime: Long? = 0,
    val packageName: String? = "",
    val versioncode: Int? = 0,
    val downloadUrl: String? = "",
    val updateDesc: String? = ""
) {
    override fun toString(): String {
        return Gson().toJson(this)
    }
}