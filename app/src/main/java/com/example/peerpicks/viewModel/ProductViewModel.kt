package com.example.peerpicks.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.peerpicks.model.ProductModel
import com.example.peerpicks.repository.ProductRepository

class ProductViewModel (val repo:ProductRepository) {
    fun addProduct(productModel: ProductModel,
                   callback:(Boolean,String) -> Unit){
        repo.addProduct(productModel,callback)

    }



    fun updateProduct(productId:String,data:MutableMap<String,Any>,
                      callback: (Boolean, String) -> Unit){
        repo.updateProduct(productId,data,callback)

    }


    fun deleteProduct(productId: String,
                      callback: (Boolean, String) -> Unit){
        repo.deleteProduct(productId,callback)

    }

    var _products=MutableLiveData<ProductModel?>()
    var products=MutableLiveData<ProductModel?>()
        get() = _products

    var _allproducts=MutableLiveData<List<ProductModel?>>()
    var allProducts=MutableLiveData<List<ProductModel?>>()
        get() = _allproducts


    fun getProductById(productId: String){
        repo.getProductById(productId){
            model,success,message->
            if(success){
                _products.value=model
            }
        }
    }

    var _loading=MutableLiveData<Boolean>()
    var loading=MutableLiveData<Boolean>()
        get() = _loading

    fun getAllProduct(){
        _loading.value=true
        repo.getAllProduct{
                product,success,message->
            if(success){
                _allproducts.value=product
            }
        }
    }
}