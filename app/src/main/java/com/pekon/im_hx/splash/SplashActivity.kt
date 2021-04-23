package com.pekon.im_hx.splash

import com.pekon.im_hx.R
import com.pekon.im_hx.base.BaseActivity

class SplashActivity : BaseActivity(), SplashContract.ISplashView {
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun splashAction() {
        mPresenter.splashActionPresent("从View发起")
    }


    override val mPresenter: SplashPresenter
        get() = SplashPresenter(this)

    override fun initView() {

    }
}