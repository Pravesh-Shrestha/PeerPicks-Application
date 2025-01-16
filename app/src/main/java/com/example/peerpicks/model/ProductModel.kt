package com.example.peerpicks.model

import kotlinx.coroutines.internal.OpDescriptor

data class ProductModel(
    var productId: String="",
    var productName: String="",
    var productDesc: String="",
    var productRating: Int=0,
    var productAddress: String="",
    var productCatagoryId: String="",

) {
}