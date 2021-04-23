package com.pekon.im_hx.splash

import com.pekon.im_hx.base.IView

interface SplashContract {

    interface ISplashView : IView {
        fun splashAction()
    }

    interface ISplashPresenter {
        fun splashActionPresent(name: String)
    }
}