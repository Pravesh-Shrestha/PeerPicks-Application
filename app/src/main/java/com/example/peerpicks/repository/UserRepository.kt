package com.example.peerpicks.repository

import com.example.peerpicks.model.UserModel
import com.google.firebase.auth.FirebaseUser

interface UserRepository {
    //{
//    "success":true
//    "message" : "Login sucess"
    // }

    fun login(email:String,password:String,callback:(Boolean,String)->Unit)

//    {
//        "success":true
//
//        "message" :"Registration Successful"
//
//        "user Id": "12345pkp12345"
//    }
    fun signup(email: String,password: String,callback: (Boolean, String, String) -> Unit)

    fun addUserToDatabase(userId:String,userModel: UserModel,callback: (Boolean, String) -> Unit)

    fun forgetPassword(email:String,callback: (Boolean, String) -> Unit)

    fun getCurrentUser():FirebaseUser?


}