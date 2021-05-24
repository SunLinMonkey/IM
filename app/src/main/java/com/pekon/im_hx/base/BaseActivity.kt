package com.pekon.im_hx.base

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.Unbinder

abstract class BaseActivity : AppCompatActivity() {


    private var bindHoster: Unbinder? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        bindHoster = ButterKnife.bind(this)
        supportActionBar?.hide();
    }

    override fun onStart() {
        super.onStart()

//        val window: Window = window
//        val decorView: View = window.getDecorView()
//        val option = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
//        decorView.systemUiVisibility = option
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//            window.setStatusBarColor(Color.TRANSPARENT)
//        }
        steepStatusBar()
    }

    abstract fun getLayoutId(): Int


    private fun steepStatusBar() {


//        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN) //隐藏状态栏
//        window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN) //显示状态栏

        var release = Build.MODEL
        if (release != null) {
            if (release.contains("HUAWEI")) {
                var window = window
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            }

        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.x开始需要把颜色设置透明，否则导航栏会呈现系统默认的浅灰色
            var window = window
            var decorView = window.decorView
            //两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
            var option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            decorView.systemUiVisibility = option
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        } else {
            var window = window
            var attributes = window.attributes
            var flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
            var flagTranslucentNavigation =
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION
            attributes.flags = flagTranslucentStatus
            attributes.flags = flagTranslucentNavigation
            window.attributes = attributes
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bindHoster?.unbind()
    }
}