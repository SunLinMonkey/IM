package com.pekon.im_hx.register.model

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pekon.im_hx.contants.RegularConstants
import com.pekon.im_hx.register.data.RegisterFormState

class RegisterViewModel(private val registerRepository: RegisterRepository) : ViewModel() {

    private val registerFrom = MutableLiveData<RegisterFormState>()
    val registerFromState: LiveData<RegisterFormState> = registerFrom

    fun registerDataChanged(userName: String, pwd: String, pwdConfirm: String) {
        if (validUserName(userName) && validPwd(pwd) && validPwdConfirm(pwdConfirm, pwd)) {
            registerFrom.value = RegisterFormState(dataValid = true)
        }
    }

    /**
     * 确认密码是可用
     */
    private fun validPwdConfirm(pwdConfirm: String, pwd: String): Boolean {
        if (pwdConfirm.length < 6) {
            registerFrom.value =
                RegisterFormState(pwdConfirmState = RegisterFormState.errorType_length)
            return false
        }

        val r1 = Regex(RegularConstants.regPwd1)
        if (!pwdConfirm.matches(r1)) {
            registerFrom.value =
                RegisterFormState(pwdConfirmState = RegisterFormState.errorType_rule)
            return false
        }

        if (!TextUtils.equals(pwd, pwdConfirm)) {
            registerFrom.value =
                RegisterFormState(pwdConfirmState = RegisterFormState.errorType_compare)
            return false
        }

        return true
    }

    /**
     * 密码是否可用
     */
    private fun validPwd(pwd: String): Boolean {
        if (pwd.length < 6) {
            registerFrom.value = RegisterFormState(pwdState = RegisterFormState.errorType_length)
            return false
        }

        val r1 = Regex(RegularConstants.regPwd1)
        if (!pwd.matches(r1)) {
            registerFrom.value = RegisterFormState(pwdState = RegisterFormState.errorType_rule)
            return false
        }

        return true
    }

    /**
     * 用户名是否可用
     */
    private fun validUserName(userName: String): Boolean {
        if (userName.length < 6) {
            registerFrom.value =
                RegisterFormState(userNameState = RegisterFormState.errorType_length)
            return false
        }
        return true
    }

    fun register(userName: String, pwd: String) {
        registerRepository.register(userName, pwd);
    }

}