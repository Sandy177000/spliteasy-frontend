package com.example.spliteasy.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.spliteasy.R
import com.example.spliteasy.data.local.SessionManager
import com.example.spliteasy.ui.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginActivity: AppCompatActivity() {
    private lateinit var emailEdit: EditText
    private lateinit var passwordEdit: EditText
    private lateinit var errorMessage: TextView
    private lateinit var loginBtn: Button

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        val sessionManager = SessionManager(this)
        if(sessionManager.isLoggedIn()) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }
        initViews()
        observers()
    }

    private fun initViews() {
        setContentView(R.layout.activity_login)
        emailEdit = findViewById(R.id.editEmail)
        passwordEdit = findViewById(R.id.editPassword)
        loginBtn = findViewById(R.id.btnLogin)
        errorMessage = findViewById(R.id.errorMessage)

        loginBtn.setOnClickListener {
            val email = emailEdit.text.toString()
            val password = passwordEdit.text.toString()
            loginBtn.isEnabled = false
            loginViewModel.login(email, password)
        }

    }

    private fun observers() {
        loginViewModel.error.observe(this) { error ->
            loginBtn.isEnabled = true
            errorMessage.text = error ?: ""
        }

        loginViewModel.user.observe(this) { user ->
            loginBtn.isEnabled = true
            user?.let {
                val sessionManager = SessionManager(this) // 'this' is Activity context

                sessionManager.saveSession(
                    name = it.name,
                    email = it.email,
                    profileImage = it.profileImage,
                    token = it.accessToken
                )

                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }

    }


}