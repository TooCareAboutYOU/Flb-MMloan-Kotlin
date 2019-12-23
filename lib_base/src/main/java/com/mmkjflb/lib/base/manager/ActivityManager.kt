package com.mmkjflb.lib.base.manager

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import java.util.*
import kotlin.system.exitProcess

/**
 * @author zhangshuai
 * @date 2019-12-23 13:45
 * @description
 */
class ActivityManager private constructor() {

    private val activityStack: Stack<Activity> = Stack()

    companion object {
        val INSTANCE: ActivityManager by lazy { ActivityManager() }
    }

    /**
     * add Activity to ActivityStack
     */
    fun add(activity: Activity) {
        activityStack.add(activity)
    }

    /**
     * remove activity from ActivityStack
     */
    fun remove(activity: Activity) {
        with(activity) {
            if (activityStack.contains(this)) {
                this.finish()
                activityStack.remove(this)
            }
        }
    }


    /**
     * Remove specified activity
     */
    fun <T : Activity> removeByClass(vararg clas: Class<T>) {
        activityStack.forEach { it ->
            clas.forEach { clazz ->
                if (it::class.java.simpleName == clazz.simpleName) {
                    if (!it.isFinishing) {
                        it.finish()
                        activityStack.remove(it)
                        return
                    }
                }
            }
        }
    }


    /**
     * clear ActivityStack
     */
    fun clear() {
        activityStack.forEach { it.finish() }
        activityStack.clear()
        System.gc()
    }

    /**
     * exit app with clear ActivityStack
     */
    @SuppressLint("MissingPermission")
    fun exitApp(context: Context) {
        clear()
        val activityManager =
            context.getSystemService(Context.ACTIVITY_SERVICE) as android.app.ActivityManager
        activityManager.killBackgroundProcesses(context.packageName)
        exitProcess(0)
    }
}