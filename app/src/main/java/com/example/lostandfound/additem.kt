package com.example.lostandfound

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage


class additem : AppCompatActivity() {

    private lateinit var uri: Uri
    private lateinit var storage: FirebaseStorage
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        storage = Firebase.storage
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
        val aditmbtn = findViewById<Button>(R.id.additm)
        val imagel = findViewById<LinearLayout>(R.id.imagel)
        val itemimage = findViewById<ImageView>(R.id.itemimage)
        val addimage = findViewById<Button>(R.id.addimage)
        val itemdb = db.collection("items")
        val intent = Intent(this , home::class.java)
        var status = ""
        val gallery = registerForActivityResult(ActivityResultContracts.GetContent(),
            ActivityResultCallback {
                itemimage.setImageURI(it)
                aditmbtn.isVisible = true
                uri = it

            })


        val storageref = storage.reference


        addimage.setOnClickListener {
            gallery.launch("image/*")
        }


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
            forml.isVisible = false
            imagel.isVisible = true
        }
        val load = findViewById<TextView>(R.id.load)



        aditmbtn.setOnClickListener {
            if(location.text.toString().isNotEmpty() && date.text.toString().isNotEmpty() && title.text.toString().isNotEmpty() && desc.text.toString().isNotEmpty() && roll.text.toString().isNotEmpty()){
                load.isVisible = true
                //val item = hashMapOf("item" to title.text.toString(), "desc" to desc.text.toString(), "roll" to roll.text.toString(), "location" to location.text.toString(), "status" to status.toString(), "date" to date.text.toString())
                //itemdb.add(item)


                storageref.child(System.currentTimeMillis().toString()).putFile(uri).addOnSuccessListener { task->
                    task.metadata!!.reference!!.downloadUrl
                        .addOnSuccessListener {
                            var downloadurl = it.toString()
                            val item = hashMapOf("item" to title.text.toString(), "desc" to desc.text.toString(), "roll" to roll.text.toString(), "location" to location.text.toString(),
                                "status" to status.toString(),
                                "date" to date.text.toString(),
                            "downloadurl" to downloadurl)

                            itemdb.add(item)
                            Toast.makeText(applicationContext, "Item Added", Toast.LENGTH_SHORT).show()

                        }


                }


            }
            else{
                Toast.makeText(applicationContext, "Fill all the details", Toast.LENGTH_SHORT).show()
            }
        }

    }
}