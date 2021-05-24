package com.pekon.im_hx.register.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import butterknife.BindView
import butterknife.OnClick
import com.pekon.im_hx.R
import com.pekon.im_hx.base.BaseActivity
import com.pekon.im_hx.register.data.RegisterFormState
import com.pekon.im_hx.register.model.RegisterViewModel

class RegisterActivity : BaseActivity() {

    @BindView(R.id.edt_password)
    lateinit var edt_password: EditText

    @BindView(R.id.edt_username)
    lateinit var edt_username: EditText

    @BindView(R.id.edt_password_confirm)
    lateinit var edt_password_confirm: EditText

    @BindView(R.id.btn_register)
    lateinit var btn_register: Button

    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        registerViewModel =
            ViewModelProvider(this, RegisterViewModelFactory()).get(RegisterViewModel::class.java)

        textChangeWatcher()

        createObserve()
    }

    private fun createObserve() {
        registerViewModel.registerFromState.observe(this, Observer {
            val registerFrom = it ?: return@Observer

            btn_register.isEnabled = registerFrom.dataValid

            if (registerFrom.userNameState != null) {
                edt_username.error = getErrorMessage(registerFrom.userNameState)
            }

            if (registerFrom.pwdState != null) {
                edt_password.error = getErrorMessage(registerFrom.pwdState)
            }

            if (registerFrom.pwdConfirmState != null) {
                edt_password_confirm.error = getErrorMessage(registerFrom.pwdConfirmState)
            }
        })
    }

    /**
     * 获取显示错误信息
     */
    private fun getErrorMessage(userNameState: Int): CharSequence? {

        when (userNameState) {
            RegisterFormState.errorType_rule -> {
                return "密码至少包含 数字和英文"
            }

            RegisterFormState.errorType_length -> {
                return "内容长度必须在6-20"
            }

            RegisterFormState.errorType_compare -> {
                return "两次输入密码不一致"
            }
        }

        return ""
    }

    private fun textChangeWatcher() {
        edt_password.afterTextChanged {
            registerViewModel.registerDataChanged(
                edt_username.text.toString(),
                edt_password.text.toString(),
                edt_password_confirm.text.toString()
            )
        }

        edt_username.afterTextChanged {
            registerViewModel.registerDataChanged(
                edt_username.text.toString(),
                edt_password.text.toString(),
                edt_password_confirm.text.toString()
            )
        }

        edt_password_confirm.afterTextChanged {
            registerViewModel.registerDataChanged(
                edt_username.text.toString(),
                edt_password.text.toString(),
                edt_password_confirm.text.toString()
            )
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_reigster
    }


    @OnClick(R.id.btn_register)
    fun onClick(view: View) {
        when (view.id) {
            R.id.btn_register -> {
                registerViewModel.register(
                    edt_username.text.toString(),
                    edt_password.text.toString()
                )
            }
        }
    }
}


/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 * 扩展功能可简化将afterTextChanged操作设置为EditText组件的过程。
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}