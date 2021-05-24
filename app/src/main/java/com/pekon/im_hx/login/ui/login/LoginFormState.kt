package com.pekon.im_hx.login.ui.login

/**
 * Data validation state of the login form.
 * 登录表单的数据验证状态。
 */
data class LoginFormState(
    val usernameError: Int? = null,
    val passwordError: Int? = null,
    val isDataValid: Boolean = false
)