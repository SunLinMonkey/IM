package com.pekon.im_hx.contants

class RegularConstants {

    companion object {
        /**
         * 密码至少包含 数字和英文，长度6-20
         */
        const val regPwd1 = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$";

        /**
         * 密码包含 数字,英文,字符中的两种以上，长度6-20
         */
        const val regPwd2 = "^(?![0-9]+$)(?![a-z]+$)(?![A-Z]+$)(?!([^(0-9a-zA-Z)])+$).{6,20}$";

        /**
         * 至少包含数字跟字母，可以有字符
         */
        const val regPwd3 = "(?=.*([a-zA-Z].*))(?=.*[0-9].*)[a-zA-Z0-9-*/+.~!@#$%^&*()]{6,20}$";
    }
}