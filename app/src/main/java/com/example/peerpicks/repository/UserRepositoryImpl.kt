package com.example.peerpicks.repository

import android.widget.Toast
import com.example.peerpicks.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UserRepositoryImpl:UserRepository {
    //    Authentication
    lateinit var auth: FirebaseAuth

    //    Database
    var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    var ref: DatabaseReference =database.reference.child("users")

    override fun login(email: String, password: String, callback: (Boolean, String) -> Unit) {
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener{
            if (it.isSuccessful){
                callback(true,"Login Success")
            }else{
                callback(false, it.exception?.message.toString())

            }
        }
    }

    override fun signup(
        email: String,
        password: String,
        callback: (Boolean, String, String) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{
            if (it.isSuccessful){
                callback(true,"Registration Success",auth.currentUser?.uid.toString())
            }else{
                callback(false, it.exception?.message.toString(),"")

            }
        }

    }

    override fun addUserToDatabase(
        userId: String,
        userModel: UserModel,
        callback: (Boolean, String) -> Unit
    ) {
        ref.child(userId.toString()).setValue(userModel).addOnCompleteListener {
            if (it.isSuccessful) {
                callback(true,"Registration success")

            } else {
                callback(false,it.exception?.message.toString())

            }
        }

    }

    override fun forgetPassword(email: String, callback: (Boolean, String) -> Unit) {
    auth.sendPasswordResetEmail(email).addOnCompleteListener{
        if (it.isSuccessful){
            callback(true,"Reset Request Sent to $email")
        }else{
            callback(false,it.exception?.message.toString())
        }
    }
    }

    override fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }

}