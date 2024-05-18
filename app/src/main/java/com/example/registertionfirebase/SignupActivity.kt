package com.example.registertionfirebase

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.registertionfirebase.ViewModels.AuthViewModel
import com.example.registertionfirebase.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    private lateinit var viewModel: AuthViewModel
    private lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivitySignupBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        binding.signupBtn.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val password = binding.passwordEt.text.toString()
            val confirmPass = binding.conpassEt.text.toString()

            if (email.isEmpty() || password.isEmpty() || confirmPass.isEmpty()) {
                Toast.makeText(this, "Please fill up all the required fields", Toast.LENGTH_SHORT)
                    .show()
            }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() ||
                !(email.toString().endsWith("@dipti.com.bd") || email.toString().endsWith("@gmail.com"))) {
                Toast.makeText(this, "Your email isn't valid, please ensure your email is from DIPTI or Gmail", Toast.LENGTH_SHORT).show();
            }
            else if (!password.equals(confirmPass)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.signup(email, confirmPass).observe(this, { result ->
                    Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
                    if (it.equals("Sign in successful")) {
                        startActivity(Intent(this, ::class.java))
                    }
                })

            }
        }
        binding.signintxt.setOnClickListener {
            startActivity(Intent(this, SigninActivity::class.java))
        }
    }
}