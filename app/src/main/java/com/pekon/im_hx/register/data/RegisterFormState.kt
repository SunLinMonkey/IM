package com.pekon.im_hx.register.data

data class RegisterFormState(
    val userNameState: Int? = null,
    val pwdState: Int? = null,
    val pwdConfirmState: Int? = null,
    val dataValid: Boolean = false
) {
    companion object {

        const val errorType_length = 1

        const val errorType_rule = 2

        const val errorType_compare = 3
    }
}