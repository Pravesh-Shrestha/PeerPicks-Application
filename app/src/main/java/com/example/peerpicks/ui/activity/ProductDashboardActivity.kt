package com.example.peerpicks.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.peerpicks.R
import com.example.peerpicks.adapter.ProductAdapter
import com.example.peerpicks.databinding.ActivityProductDashboardBinding
import com.example.peerpicks.repository.ProductRepositoryImpl
import com.example.peerpicks.viewModel.ProductViewModel

class ProductDashboardActivity : AppCompatActivity() {
    lateinit var binding: ActivityProductDashboardBinding
    lateinit var productViewModel: ProductViewModel
    lateinit var adapter: ProductAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityProductDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var repo = ProductRepositoryImpl()
        productViewModel = ProductViewModel(repo)
        adapter = ProductAdapter(this@ProductDashboardActivity,
            ArrayList())
        productViewModel.getAllProduct()
        productViewModel.allProducts.observe(this){products->
            products?.let {
                adapter.updateData(it)
            }
        }
        productViewModel.loading.observe(this){loading->
            if(loading){
                binding.progressBar.visibility = View.VISIBLE
            }else{
                binding.progressBar.visibility = View.GONE
            }
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)


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