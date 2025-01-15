package com.example.peerpicks.repository

import com.example.peerpicks.model.ProductModel

interface ProductRepository {



    fun addProduct(productModel: ProductModel,
                   callback:(Boolean,String) -> Unit)



    fun updateProduct(productId:String,data:MutableMap<String,Any>,
                      callback: (Boolean, String) -> Unit)


    fun deleteProduct(productId: String,
                      callback: (Boolean, String) -> Unit)


    fun getProductById(productId: String,
                       callback: (ProductModel?, String) -> Unit)



    fun getAllProduct(callback: (List<ProductModel>?,Boolean, String) -> Unit)

}