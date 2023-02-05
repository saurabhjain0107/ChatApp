package com.example.chatapp

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.chatapp.databinding.ActivitySingUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class SingUp : AppCompatActivity() {


    lateinit var binding : ActivitySingUpBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var mdbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.btnRegister.setOnClickListener {
            val firstName = binding.firstname.text.toString()
            val lastName = binding.lastname.text.toString()
            val email = binding.IdInput.text.toString()
            val password = binding.editPassword.text.toString()
            signUp(firstName,lastName ,email,password)
        }

    }

    private fun signUp(firstName: String, lastName: String ,email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    addUserToDatabase(firstName,lastName,email,password,auth.currentUser?.uid!!)
                    val intent = Intent(this,Login::class.java)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()

                }
            }

    }

    private fun addUserToDatabase(firstName: String, lastName: String, email: String, password: String, uid: String) {

        mdbRef = FirebaseDatabase.getInstance().getReference()

        mdbRef.child("User").child(uid).setValue(User(firstName,lastName, email, password,uid))



    }


}