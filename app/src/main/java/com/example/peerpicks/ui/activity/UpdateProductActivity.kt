package com.example.peerpicks.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.peerpicks.R
import com.example.peerpicks.databinding.ActivityAddProductBinding
import com.example.peerpicks.databinding.ActivityUpdateProductBinding
import com.example.peerpicks.model.ProductModel
import com.example.peerpicks.repository.ProductRepositoryImpl
import com.example.peerpicks.viewModel.ProductViewModel

class UpdateProductActivity : AppCompatActivity() {
    lateinit var binding: ActivityUpdateProductBinding
    lateinit var productViewModel: ProductViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityUpdateProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var repo=ProductRepositoryImpl()
        productViewModel=ProductViewModel(repo)





        var id: String=intent.getStringExtra("productId").toString()
        productViewModel.getProductById(id)

        productViewModel.products.observe(this){
            binding.updateproductName.setText(it?.productName.toString())
            binding.updateproductDesc.setText(it?.productDesc.toString())
            binding.updateproductRatings.setText(it?.productRating.toString().toInt())

        }

        binding.updatebutton.setOnClickListener{
            val newProductName=binding.updateproductName.toString()
            val newProductDesc=binding.updateproductDesc.toString()
            val newProductRating=binding.updateproductRatings.toString()

            var updatedMap= mutableMapOf<String,Any>()

            updatedMap["productName"]=newProductName
            updatedMap["productDesc"]=newProductDesc
            updatedMap["productRating"]=newProductRating


            productViewModel.updateProduct(id,updatedMap){
                success, message ->
                if(success){
                    Toast.makeText(this@UpdateProductActivity,
                        message, Toast.LENGTH_LONG).show()
                    finish()
                }else{
                    Toast.makeText(this@UpdateProductActivity,
                        message, Toast.LENGTH_LONG).show()
                }
            }

//            productViewModel.updateProduct(model){
//                    success,message->
//                if(success){
//                    Toast.makeText(this@UpdateProductActivity,
//                        message, Toast.LENGTH_LONG).show()
//                }else{
//                    Toast.makeText(this@UpdateProductActivity,
//                        message, Toast.LENGTH_LONG).show()
//                }
//            }


        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}