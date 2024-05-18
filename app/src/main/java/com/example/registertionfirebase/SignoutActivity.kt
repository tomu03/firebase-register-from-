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
import com.example.registertionfirebase.databinding.ActivitySignoutBinding
import com.example.registertionfirebase.databinding.ActivitySignupBinding

class SignoutActivity : AppCompatActivity() {
    private lateinit var viewModel: AuthViewModel
    private lateinit var binding: ActivitySignoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignoutBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        binding.signoutBtn.setOnClickListener {
            viewModel.signout()
            startActivity(Intent(this, SigninActivity::class.java))
        }

    }
}