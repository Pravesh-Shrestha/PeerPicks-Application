package com.example.peerpicks.repository

import com.example.peerpicks.model.ProductModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ProductRepositoryImpl : ProductRepository {
    val database:FirebaseDatabase= FirebaseDatabase.getInstance()
    val reference:DatabaseReference=database.reference.child("Products")

    override fun addProduct(productModel: ProductModel, callback: (Boolean, String) -> Unit) {

    }

    override fun updateProduct(
        productId: String,
        data: MutableMap<String, Any>,
        callback: (Boolean, String) -> Unit
    ) {

    }

    override fun deleteProduct(productId: String, callback: (Boolean, String) -> Unit) {

    }

    override fun getProductById(productId: String, callback: (ProductModel?, String) -> Unit) {

    }

    override fun getAllProduct(callback: (List<ProductModel>?, Boolean, String) -> Unit) {

    }
}