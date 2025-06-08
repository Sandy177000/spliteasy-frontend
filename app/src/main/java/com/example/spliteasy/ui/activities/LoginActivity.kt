package com.example.spliteasy.ui.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.spliteasy.R
import com.example.spliteasy.ui.login.LoginViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginActivity: AppCompatActivity() {
    private lateinit var emailEdit: EditText
    private lateinit var passwordEdit: EditText
    private lateinit var loginBtn: Button
    private lateinit var googleLoginBtn: Button

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var loginViewModel: LoginViewModel

    companion object {
        private const val RC_SIGN_IN = 9001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        FirebaseApp.initializeApp(this)
        setupGoogleLogin()
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        observeViewModel()
    }

    private fun setupGoogleLogin() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id)) // from google-services.json
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    private fun initViews() {
        setContentView(R.layout.activity_login)
        emailEdit = findViewById(R.id.editEmail)
        passwordEdit = findViewById(R.id.editPassword)
        loginBtn = findViewById(R.id.btnLogin)
        googleLoginBtn = findViewById(R.id.btnGoogleLogin)

        loginBtn.setOnClickListener {
            Toast.makeText(this, "This flow is not supported", Toast.LENGTH_SHORT).show()
        }

        googleLoginBtn.setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }

    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?,
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN && resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.result
                val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                FirebaseAuth.getInstance().signInWithCredential(credential)
                    .addOnCompleteListener { authTask ->
                        if (authTask.isSuccessful) {
                            loginViewModel.loginWithFirebaseToken() // backend verification
                        } else {
                            Toast.makeText(this, "Firebase sign in failed", Toast.LENGTH_SHORT).show()
                        }
                    }
            } catch (e: Exception) {
                Toast.makeText(this, "Google sign-in failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun observeViewModel() {
        loginViewModel.user.observe(this, Observer { user ->
            user?.let {
                Toast.makeText(this, "Welcome ${it.name}", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        })

        loginViewModel.error.observe(this, Observer { error ->
            error?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        })
    }


}