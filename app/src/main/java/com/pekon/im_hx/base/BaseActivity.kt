package com.pekon.im_hx.base

import android.app.Activity
import android.os.Bundle

abstract class BaseActivity : Activity(), IView {

    private val presentListHost = HashSet<IPresenter<*>>()

    open fun getPresentList(): MutableList<IPresenter<*>> {
        return mutableListOf(mPresenter)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        addPresenters()
        presentListHost.forEach { it.onCreate(intent) }
    }

    private fun addPresenters() {
        getPresentList().forEach { presentListHost.add(it) }
    }

    abstract fun getLayoutId(): Int


    override fun onStart() {
        super.onStart()
        presentListHost.forEach { it.onStart() }
    }

    override fun onResume() {
        super.onResume()
        presentListHost.forEach { it.onResume() }
    }

    override fun onPause() {
        super.onPause()
        presentListHost.forEach { it.onPause() }
    }

    override fun onStop() {
        super.onStop()
        presentListHost.forEach { it.onStop() }
    }

    override fun onDestroy() {
        super.onDestroy()
        presentListHost.forEach { it.onDestroy() }
    }

    override fun showProgressDialog() {

    }

    override fun dismissProgressDialog() {
        TODO("Not yet implemented")
    }
}