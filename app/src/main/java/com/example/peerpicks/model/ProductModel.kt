package com.example.peerpicks.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.coroutines.internal.OpDescriptor

data class ProductModel(
    var productId: String="",
    var productName: String="",
    var productDesc: String="",
    var productRating: Int=0,
    var productAddress: String="",
    var productCatagoryId: String="",

) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString() ?:"",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?:"",
        parcel.readString() ?:""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(productId)
        parcel.writeString(productName)
        parcel.writeString(productDesc)
        parcel.writeInt(productRating)
        parcel.writeString(productAddress)
        parcel.writeString(productCatagoryId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductModel> {
        override fun createFromParcel(parcel: Parcel): ProductModel {
            return ProductModel(parcel)
        }

        override fun newArray(size: Int): Array<ProductModel?> {
            return arrayOfNulls(size)
        }
    }


}