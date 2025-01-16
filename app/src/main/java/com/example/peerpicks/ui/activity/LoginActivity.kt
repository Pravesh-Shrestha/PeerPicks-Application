package com.example.peerpicks.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.peerpicks.R
import com.example.peerpicks.databinding.ActivityLoginBinding
import com.example.peerpicks.repository.UserRepositoryImpl
import com.example.peerpicks.viewModel.UserViewModel
import com.google.firebase.database.FirebaseDatabase

class LoginActivity : AppCompatActivity() {

    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var database: FirebaseDatabase

    lateinit var binding:ActivityLoginBinding
    lateinit var userViewModel:UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var repo= UserRepositoryImpl()
        userViewModel= UserViewModel(repo)

        binding.btnLogin.setOnClickListener{
            var email=binding.editEmail.text.toString()
            var password=binding.editPassword.text.toString()


            userViewModel.login(email,password){
                    success,message ->
                if (success){
                    var intent=Intent(this@LoginActivity,
                        NavigationActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(
                        this@LoginActivity,
                        message,
                        Toast.LENGTH_LONG
                    ).show()

                }
            }
        }
        binding.btnSignupnavigate.setOnClickListener{

            val intent=Intent(this@LoginActivity,
                RegisterActivity::class.java)
            startActivity(intent)
        }
        binding.btnForget.setOnClickListener {
            val email = binding.editEmail.text.toString()

            if (email.isEmpty()) {
                Toast.makeText(
                    this@LoginActivity,
                    "Please enter your email address.",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                userViewModel.forgetPassword(email) { success, message ->
                    if (success) {
                        Toast.makeText(
                            this@LoginActivity,
                            "Password reset link has been sent to your email.",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(
                            this@LoginActivity,
                            message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}