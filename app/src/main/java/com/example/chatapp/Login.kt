package com.example.chatapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Login : AppCompatActivity() {

    private lateinit var edtEmail:EditText
    private lateinit var edtPass:EditText
    private lateinit var btnLogin:Button
    private lateinit var signUP: Button
    private lateinit var auth: FirebaseAuth


    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth

        edtEmail = findViewById(R.id.IdInput)
        edtPass = findViewById(R.id.editTextTextPassword)
        btnLogin = findViewById(R.id.loginbtn)
        signUP = findViewById(R.id.SignUp)

        signUP.setOnClickListener {
            val intent = Intent(this,SingUp::class.java)
            startActivity(intent)
        }
        btnLogin.setOnClickListener {
          val email = edtEmail.text.toString()
          val password = edtPass.text.toString()

            login(email,password)
        }

    }

    private fun login(email: String, password: String) {

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information

                    val intent = Intent(this,MainActivity::class.java)
                    finish()
                    startActivity(intent)

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }


    }
}