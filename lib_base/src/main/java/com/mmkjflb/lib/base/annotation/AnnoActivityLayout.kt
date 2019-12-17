package com.mmkjflb.lib.base.annotation

import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull

/**
 * @author zhangshuai
 * @date 2019-12-12 17:20
 * @description Activity xml布局索引
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.CONSTRUCTOR)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class AnnoActivityLayout(@IdRes val layoutResId: Int)

