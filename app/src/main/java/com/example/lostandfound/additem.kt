package com.example.lostandfound

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.view.isVisible
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class additem : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_additem)
        val db = Firebase.firestore
        val lostbtn = findViewById<Button>(R.id.lostbtn)
        val foundbtn = findViewById<Button>(R.id.foundbtn)
        //val headtxt = findViewById<TextView>(R.id.headtxt)
        val nextbtn = findViewById<Button>(R.id.nextbtn)
        val catl = findViewById<LinearLayout>(R.id.catl)
        val forml = findViewById<LinearLayout>(R.id.forml)
        val location = findViewById<EditText>(R.id.location)
        val date = findViewById<EditText>(R.id.date)
        val desc = findViewById<EditText>(R.id.descedt)
        val title = findViewById<EditText>(R.id.itemname)
        val roll = findViewById<EditText>(R.id.roll)
        val itemdb = db.collection("items")
        val intent = Intent(this , home::class.java)
        var status = ""
        lostbtn.setOnClickListener {
            catl.isVisible = false
            forml.isVisible = true
            status = "Lost"

        }
        foundbtn.setOnClickListener {
            catl.isVisible = false
            forml.isVisible = true
            status = "Found"

        }
        nextbtn.setOnClickListener {
            if(location.text.toString().isNotEmpty() && date.text.toString().isNotEmpty() && title.text.toString().isNotEmpty() && desc.text.toString().isNotEmpty() && roll.text.toString().isNotEmpty()){
                val item = hashMapOf("item" to title.text.toString(), "desc" to desc.text.toString(), "roll" to roll.text.toString(), "location" to location.text.toString(), "status" to status.toString(), "date" to date.text.toString())
                itemdb.add(item)
                Toast.makeText(applicationContext, "Item Added", Toast.LENGTH_SHORT).show()


            }
            else{
                Toast.makeText(applicationContext, "Fill all the details", Toast.LENGTH_SHORT).show()
            }
        }

    }
}