package com.mmkjflb.lib.base.annotation

import androidx.annotation.*
import com.mmkjflb.lib.base.view.toolbar.ToolbarHelper

/**
 * @author zhangshuai
 * @date 2019-12-13 11:37
 * @description 规范ToolBar基本格式
 */

@Target(AnnotationTarget.CLASS, AnnotationTarget.CONSTRUCTOR)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class AnnoToolBarLayout(
    @IdRes val layoutResId: Int = ToolbarHelper.NO_LOADED,
    @DrawableRes val leftDrawable: Int = ToolbarHelper.NO_LOADED,
    @StringRes val leftText: Int = ToolbarHelper.NO_LOADED,
    @ColorRes val leftTextColor: Int = ToolbarHelper.NO_LOADED,
    @DrawableRes val centerDrawable: Int = ToolbarHelper.NO_LOADED,
    @StringRes val centerText: Int = ToolbarHelper.NO_LOADED,
    @ColorRes val centerTextColor: Int = ToolbarHelper.NO_LOADED,
    @DrawableRes val rightDrawable: Int = ToolbarHelper.NO_LOADED,
    @StringRes val rightText: Int = ToolbarHelper.NO_LOADED,
    @ColorRes val rightTextColor: Int = ToolbarHelper.NO_LOADED,
    @ColorRes val toolbarBgColor: Int = ToolbarHelper.NO_LOADED,
    @DrawableRes val toolbarBgDrawable: Int = ToolbarHelper.NO_LOADED
)