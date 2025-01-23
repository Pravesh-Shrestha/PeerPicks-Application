package com.example.peerpicks.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.peerpicks.R
import com.example.peerpicks.databinding.ActivityAddProductBinding
import com.example.peerpicks.model.ProductModel
import com.example.peerpicks.repository.ProductRepositoryImpl
import com.example.peerpicks.viewModel.ProductViewModel

class AddProductActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddProductBinding
    lateinit var productViewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var repo = ProductRepositoryImpl()
        productViewModel = ProductViewModel(repo)
        binding.button.setOnClickListener {
            var productName = binding.editproductName.text.toString()
            var productDesc = binding.editproductDesc.text.toString()
            var productRating = binding.editproductRatings.text.toString().toInt()

            var model = ProductModel("",productName,
                productDesc,productRating)

            productViewModel.addProduct(model){
                    success,message->
                if(success){
                    Toast.makeText(this@AddProductActivity,
                        message,Toast.LENGTH_LONG).show()
                    finish()
                }else{
                    Toast.makeText(this@AddProductActivity,
                        message,Toast.LENGTH_LONG).show()
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