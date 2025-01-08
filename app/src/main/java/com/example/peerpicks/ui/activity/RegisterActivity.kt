package com.example.peerpicks.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.peerpicks.R
import com.example.peerpicks.databinding.ActivityRegisterBinding
import com.example.peerpicks.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {
    lateinit var binding:ActivityRegisterBinding

//    Authentication
    lateinit var auth:FirebaseAuth

//    Database
    var database:FirebaseDatabase=FirebaseDatabase.getInstance()
    var ref: DatabaseReference=database.reference.child("users")



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth=FirebaseAuth.getInstance()

        binding.signUp.setOnClickListener{
            var email=binding.registerEmail.text.toString()
            var password=binding.registerPassword.toString()
            var firstName=binding.registerFname.text.toString()
            var lastName=binding.registerLName.text.toString()
            var address=binding.registerAddress.text.toString()
            var contact=binding.registerContact.text.toString()

            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{
                if(it.isSuccessful){
                    var userId=auth.currentUser?.uid

                    var userModel=UserModel(
                        userId.toString(),
                        firstName, lastName,
                        address, contact, email
                    )
                    ref.child(userId.toString()).setValue(userModel).addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(
                                this@RegisterActivity,
                                "Register Success",
                                Toast.LENGTH_LONG
                            ).show()
                        } else {
                            Toast.makeText(
                                this@RegisterActivity,
                                it.exception?.message.toString(),
                                Toast.LENGTH_LONG).show()
                        }
                    }

                } else{
                    Toast.makeText(this@RegisterActivity,
                        it.exception?.message.toString(),
                        Toast.LENGTH_LONG).show()
                    }

            }

        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}