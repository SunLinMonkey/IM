package com.pekon.im_hx.splash

import com.pekon.im_hx.R
import com.pekon.im_hx.base.MvpBaseActivity
import com.pekon.im_hx.login.ui.login.LoginActivity
import org.jetbrains.anko.startActivity

class SplashActivity : MvpBaseActivity(), SplashContract.ISplashView {
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun splashAction() {
        mPresenter.splashActionPresent("从View发起")
    }

    override val mPresenter: SplashPresenter
        get() = SplashPresenter(this)

    override fun initView() {

//        1:正常跳转
//
//        startActivity<RegisterActivity>()
//
//        携带参数
//
//        startActivity<ResetPwdActivity>("key" to "值")
//
//        2:A页面跳到B页面,再跳到C页面,再跳到A页面时,要求清空B,C页面退出,并且不重走A的生命周期
//
//        startActivity(intentFor<MainActivity>().singleTop().clearTop())
//
//        3:A页面跳到B页面,再跳到C页面,再跳到A页面时,要求清空B,C页面退出,并且重走A的生命周期
//
//        startActivity(intentFor<MainActivity>().clearTask().newTask())
//
//        4:A页面->B页面->C页面->D页面时,销毁A.B.C这三个页面,整个栈中就D页面
//
//        startActivity(intentFor<LoginActivity>().newTask().clearTask())

        startActivity<LoginActivity>()
        finish()
    }
}