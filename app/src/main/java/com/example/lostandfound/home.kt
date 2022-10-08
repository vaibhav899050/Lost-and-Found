package com.example.lostandfound

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ImageView
import androidx.core.view.isVisible


class home : AppCompatActivity() {
    public lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val usernametop = findViewById<TextView>(R.id.utxt)
        val intent = intent
        val str = intent.getStringExtra("username")
        usernametop.setText(str)
        val linear: LinearLayout = findViewById<LinearLayout>(R.id.l5)
        val addimg = findViewById<ImageView>(R.id.plus)
        val handler = Handler()
        val handler1 = Handler()
        var params: ViewGroup.LayoutParams = linear.layoutParams
        addimg.setOnClickListener {
            linear.isVisible = true



        }


    }
}