package com.pekon.im_hx.register.model

class RegisterRepository(private val registerData: RegisterDataSource) {
    fun register(userName: String, pwd: String) {
        registerData.register(userName, pwd)
    }
}