package com.example.lostandfound

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val usernametop = findViewById<TextView>(R.id.utxt)
        val intent = intent
        val str = intent.getStringExtra("username")
        usernametop.setText(str)

    }
}