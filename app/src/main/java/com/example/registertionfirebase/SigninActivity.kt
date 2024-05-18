package com.example.registertionfirebase

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.registertionfirebase.ViewModels.AuthViewModel
import com.example.registertionfirebase.databinding.ActivitySigninBinding
import com.example.registertionfirebase.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class SigninActivity : AppCompatActivity() {
        private lateinit var viewModel: AuthViewModel
        private lateinit var binding: ActivitySigninBinding
        override fun onCreate(savedInstanceState: Bundle?) {
            binding = ActivitySigninBinding.inflate(layoutInflater)
            super.onCreate(savedInstanceState)
            setContentView(binding.root)

            viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

            binding.signinBtm.setOnClickListener {
                val email = binding.emailEt.text.toString()
                val password = binding.passwordEt.text.toString()
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(this, "Please fill up all the fields!!!", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    viewModel.signup(email, password).observe(this, {result->
                        Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
                        if(it.equals("Sign in successful")){
                            startActivity(Intent(this, SignoutActivity::class.java))
                        }
                    })
                }
            }
            binding.signupTxt.setOnClickListener {
                startActivity(Intent(this, SignupActivity::class.java))
            }

        }
    override fun onStart() {
        super.onStart()
        if (FirebaseAuth.getInstance().currentUser?.uid != null) {
            startActivity(Intent(this, SignoutActivity::class.java))
        }
    }
}