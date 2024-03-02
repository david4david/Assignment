package com.example.assignment

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        val btnLogin: Button = findViewById(R.id.btnLogin)
        val etUsername: EditText = findViewById(R.id.etUsername)
        val etPassword: EditText = findViewById(R.id.etPassword)

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()
            // Retrieve stored credentials and validate login
            val storedCredentials = getUserCredentialsLocally()
            if (storedCredentials.first == username && storedCredentials.second == password) {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            } else {
                val alertDialogBuilder = AlertDialog.Builder(this)
                alertDialogBuilder.setTitle("Wrong password!")
                val alertDialog = alertDialogBuilder.create()
            }
        }
    }

    private fun getUserCredentialsLocally(): Pair<String, String> {
        return Pair("storedEmail", "storedPassword")
    }
}
