package com.example.peerpicks.model

import kotlinx.coroutines.internal.OpDescriptor

data class ProductModel(
    var productId: String="",
    var productName: String="",
    var productDescriptor: String="",
    var productAddress: String="",
    var productRating: String="",
    var productCatagoryId: String="",

) {
}