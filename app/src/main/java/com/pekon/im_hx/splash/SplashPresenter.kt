package com.pekon.im_hx.splash

import android.util.Log
import com.pekon.im_hx.base.IPresenter
import com.pekon.im_hx.listener.OnModelBackListener

class SplashPresenter(private val splashView: SplashContract.ISplashView) :
    SplashContract.ISplashPresenter,
    IPresenter<SplashContract.ISplashView>(splashView) {


    private var splashModel: SplashModel? = null

    init {
        splashModel = SplashModel()
    }


    override fun splashActionPresent(name: String) {
        Log.e("9527", "splashActionPresent: $String")
        splashModel?.excuteInModel("输入string", 9999, object : OnModelBackListener {
            override fun onModelBack() {
                Log.e("9527", "onModelBack: ")
            }
        })
    }


}