package com.mmkj.mmloan.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.mmkj.mmloan.MmkvKeys
import com.mmkjflb.lib.base.mmkv
import java.net.URLDecoder

class InstallReferrerReceiver : BroadcastReceiver() {

    private companion object {
        val ACTION_INSTALL_REFERRER: String = "com.android.vending.INSTALL_REFERRER"
    }

    override fun onReceive(context: Context, intent: Intent) {
        if (!ACTION_INSTALL_REFERRER.equals(intent.action)) {
            return
        }
        val info = intent.getStringExtra("referrer")
        if (info != null) {
            mmkv.encode(MmkvKeys.REFERRER, URLDecoder.decode(info, "UTF-8"))
        }
    }
}
