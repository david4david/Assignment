package com.example.assignment

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment.R

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)

        val btnSignup: Button = findViewById(R.id.btnSignup)
        val etFullName: EditText = findViewById(R.id.etUsername)
        val etEmail: EditText = findViewById(R.id.etEmail)
        val etPassword: EditText = findViewById(R.id.etPassword)

        btnSignup.setOnClickListener {
            // Handle signup button click
            val fullName = etFullName.text.toString()
            val email = etEmail.text.toString()
            // Implement signup logic here
            // Store email and password locally
            val userCredentials = Pair(email, etPassword)
            saveUserCredentialsLocally(userCredentials)
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun saveUserCredentialsLocally(credentials: Pair<String, EditText>) {
        // Implement local storage logic here
        // For simplicity, you can use SharedPreferences or Room Database to store user credentials
        val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("email", credentials.first)
        editor.putString("password", credentials.second.toString())
        editor.apply()
    }
}
