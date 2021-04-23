package com.pekon.im_hx.splash

import android.util.Log
import com.pekon.im_hx.listener.OnModelBackListener

class SplashModel {

    fun excuteInModel(inputString: String, inputInt: Int, listener: OnModelBackListener) {

        Log.e("9527", "excuteInModel: $inputString   $inputInt")
        listener.onModelBack()
    }
}