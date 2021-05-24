package com.pekon.im_hx.register.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pekon.im_hx.register.model.RegisterDataSource
import com.pekon.im_hx.register.model.RegisterRepository
import com.pekon.im_hx.register.model.RegisterViewModel

@Suppress("UNCHECKED_CAST")
class RegisterViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(
                registerRepository = RegisterRepository(
                    registerData = RegisterDataSource()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}