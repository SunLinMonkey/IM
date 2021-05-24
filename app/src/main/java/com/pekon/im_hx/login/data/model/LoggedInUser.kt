package com.pekon.im_hx.login.data.model

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 * 用于捕获从LoginRepository中检索到的已登录用户的用户信息
 */
data class LoggedInUser(
    val userId: String,
    val displayName: String
)