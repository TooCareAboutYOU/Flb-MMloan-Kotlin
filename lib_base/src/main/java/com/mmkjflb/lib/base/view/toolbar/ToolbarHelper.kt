package com.mmkjflb.lib.base.view.toolbar

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.mmkjflb.lib.base.HINT_EMPTY_TOOLBAR
import com.mmkjflb.lib.base.R
import com.mmkjflb.lib.base.annotation.AnnoToolBarLayout

/** StatusBar   +  ToolBar
 * @author zhangshuai
 * @date 2019 2019-12-13 14:19
 * @description 默认 加载 base_toolbar.xml 布局
 *
 */
open class ToolbarHelper(context: AppCompatActivity) {

    private var mContext: AppCompatActivity = context

    companion object {
        const val NO_LOADED: Int = -1
    }

    init {
        initToolBar()
    }




    /**
     * 初始实例化对象
     */
    //ToolBar
    private lateinit var helper: AnnoToolBarLayout

    //ToolBar
    var toolbar: Toolbar? = null
    //ToolBar左侧图片
    var toolBarLeftImgView: AppCompatImageView? = null
    //ToolBar中间图片
    var toolBarCenterImgView: AppCompatImageView? = null
    //ToolBar又侧图片
    var toolBarTightImgView: AppCompatImageView? = null
    //ToolBar左侧文字标题
    var toolBarLeftTxtView: AppCompatTextView? = null
    //ToolBar中间文字标题
    var toolBarCenterTxtView: AppCompatTextView? = null
    //ToolBar右侧文字标题
    var toolBarRightTxtView: AppCompatTextView? = null

    /**
     * 初始化工具栏
     */
    private fun initToolBar() {
        if (mContext.javaClass.isAnnotationPresent(AnnoToolBarLayout::class.java)) {
            helper = mContext.javaClass.getAnnotation(AnnoToolBarLayout::class.java)
                ?: throw NullPointerException(HINT_EMPTY_TOOLBAR)
            if (helper.layoutResId != NO_LOADED && helper.layoutResId == R.layout.base_toolbar) {
                injectToolBarLayout()
            }
        }
    }

    /**
     *  初始化控件
     */
    private fun injectToolBarLayout() {
        toolbar = mContext.findViewById(R.id.baseToolbar)
        mContext.setSupportActionBar(toolbar)
        toolBarLeftImgView = mContext.findViewById(R.id.acIv_ToolBar_Left)
        toolBarCenterImgView = mContext.findViewById(R.id.acIv_ToolBar_Center)
        toolBarTightImgView = mContext.findViewById(R.id.acIv_ToolBar_Right)
        toolBarLeftTxtView = mContext.findViewById(R.id.acTv_ToolBar_LeftTxt)
        toolBarCenterTxtView = mContext.findViewById(R.id.acTv_ToolBar_CenterTxt)
        toolBarRightTxtView = mContext.findViewById(R.id.acTv_ToolBar_RightTxt)

        setToolBarBgColor(helper.toolbarBgColor)
        setToolBarBgDrawable(helper.toolbarBgDrawable)

        setLeftDrawable(helper.leftDrawable)
        setLeftText(helper.leftText)
        setLeftTextColor(helper.leftTextColor)

        setCenterDrawable(helper.centerDrawable)
        setCenterText(helper.centerText)
        setCenterTextColor(helper.centerTextColor)

        setRightDrawable(helper.rightDrawable)
        setRightText(helper.rightText)
        setRightTextColor(helper.rightTextColor)
    }

    /** ----------------------------- 自定义扩展属性，动态设置各个属性值 ---------------------------  **/

    //ToolBar背景色
    open fun setToolBarBgColor(@ColorRes color: Int)  {
        if (color != NO_LOADED) {
            toolbar?.setBackgroundColor(ContextCompat.getColor(mContext,color))
        }
    }

    //ToolBar背景图片
    open fun setToolBarBgDrawable(@DrawableRes img: Int){
        if (img != NO_LOADED) {
            toolbar?.setBackgroundResource(img)
        }
    }

    //左侧图标
    open fun setLeftDrawable(@DrawableRes img:Int) {
        if (img != NO_LOADED) {
            toolBarLeftImgView?.apply {
                visibility = View.VISIBLE
                setImageResource(img)
            }
        }
    }

    //左侧文字
    open fun setLeftText(@StringRes str:Int) {
        if (str != NO_LOADED) {
            toolBarLeftTxtView?.apply {
                visibility = View.VISIBLE
                text = mContext.getText(str)
            }
        }
    }

    //左侧颜色
    open fun setLeftTextColor(@ColorRes color:Int) {
        if (color != NO_LOADED) {
            toolBarLeftTxtView?.apply {
                if (text.isNotEmpty()){
                    setTextColor(ContextCompat.getColor(mContext,color))
                }
            }
        }
    }

    //中间图标
    open fun setCenterDrawable(@DrawableRes img:Int) {
        if (img != NO_LOADED) {
            toolBarCenterImgView?.apply {
                visibility = View.VISIBLE
                setImageResource(helper.centerDrawable)
            }
        }
    }

    //中间文字
    open fun setCenterText(@StringRes str:Int) {
        if (str != NO_LOADED) {
            toolBarCenterTxtView?.apply {
                visibility = View.VISIBLE
                text = mContext.getText(helper.centerText)
            }
        }
    }

    //中间颜色
    open fun setCenterTextColor(@ColorRes color:Int) {
        if (color != NO_LOADED) {
            toolBarCenterTxtView?.apply {
                if (text.isNotEmpty()){
                    setTextColor(ContextCompat.getColor(mContext,color))
                }
            }
        }
    }

    //右侧图标
    open fun setRightDrawable(@DrawableRes img:Int) {
        if (img != NO_LOADED) {
            toolBarTightImgView?.apply {
                visibility = View.VISIBLE
                setImageResource(helper.rightDrawable)
            }
        }
    }

    //右侧文字
    open fun setRightText(@StringRes str:Int) {
        if (str != NO_LOADED) {
            toolBarRightTxtView?.apply {
                visibility = View.VISIBLE
                text = mContext.getText(helper.rightText)
            }
        }
    }

    //右侧颜色
    open fun setRightTextColor(@ColorRes color:Int) {
        if (color != NO_LOADED) {
            toolBarRightTxtView?.apply {
                if (text.isNotEmpty()){
                    setTextColor(ContextCompat.getColor(mContext,color))
                }
            }
        }
    }
}