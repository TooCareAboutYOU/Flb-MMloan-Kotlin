package com.mmkj.mmloan.aop.aspect

import android.view.View
import com.mmkj.mmloan.R
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import java.util.*

/**
 * @author zhangshuai
 * @date 2019-12-18 17:12
 * @description 防重复 单击事件
 */
@Aspect
class MultipleClickAspect {

    companion object {
        private val TAG: String = "MultipleClickAspect"
        private val MIN_CLICK_DELAY_TIME: Int = 2000
        internal var TIME_TAG: Int = R.id.click_time
    }

    /**
     * 方法切入点
     */
    @Pointcut("execution(@com.mmkj.mmloan.aspectj.anno.MultipleClick * *(..))")
    fun methodAnnotated() {

    }


    /**
     * 在连接点进行方法替换
     */
    @Around("methodAnnotated()")
    fun aroundJoinPoint(joinPoint: ProceedingJoinPoint) {
        var view: View? = null
        for (arg in joinPoint.args) {
            if (arg is View) {
                view = arg
            }
        }
        if (view != null) {
            val tag: Any = view.getTag(TIME_TAG)
            val lastClickTime: Long = tag as Long
            var currentTime = Calendar.getInstance().timeInMillis
            if ((currentTime.minus(lastClickTime)).compareTo(MIN_CLICK_DELAY_TIME) > 0) {
                view.setTag(TIME_TAG, currentTime)
                currentTime = Calendar.getInstance().timeInMillis
            } else {
                joinPoint.proceed()
            }
        }
    }


}