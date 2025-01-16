package com.example.peerpicks.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.peerpicks.R
import com.example.peerpicks.databinding.ActivityProductDashboardBinding
import com.example.peerpicks.databinding.ActivityRegisterBinding
import com.example.peerpicks.databinding.FragmentMessageBinding

class ProductDashboardActivity : AppCompatActivity() {
    lateinit var binding: ActivityProductDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityProductDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.floatingActionButton.setOnClickListener{
            val intent=Intent(this@ProductDashboardActivity,AddProductActivity::class.java)
            startActivity(intent)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}