package com.example.peerpicks.repository

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
    fun addUserToDatabase()
    fun forgetPassword()
    fun getCurrentPaasword()
}