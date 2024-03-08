package com.example.assignment

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)

        val btnSignup: Button = findViewById(R.id.btnSignup)
        val etUsername: EditText = findViewById(R.id.etUsername)
        val etPassword: EditText = findViewById(R.id.etPassword)

        btnSignup.setOnClickListener {
            // Handle signup button click
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            // Store username and password in the SQLite database
            val databaseHelper = DatabaseHelper(this)
            databaseHelper.insertCredentials(username, password)

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}