package com.example.lostandfound

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.EditText
import androidx.core.view.isVisible


class additem : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_additem)
        val lostbtn = findViewById<Button>(R.id.lostbtn)
        val foundbtn = findViewById<Button>(R.id.foundbtn)
        val headtxt = findViewById<TextView>(R.id.headtxt)
        lostbtn.setOnClickListener {
            headtxt.setText("Item Name")
            lostbtn.isVisible =false
            foundbtn.isVisible = false
        }
        foundbtn.setOnClickListener {
            headtxt.setText("Item Name")
            lostbtn.isVisible =false
            foundbtn.isVisible = false
        }

    }
}