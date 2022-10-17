package com.example.lostandfound

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ImageView
import androidx.core.view.isVisible
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase



class home : AppCompatActivity() {
    public lateinit var handler: Handler
    private lateinit var RecyclerView: RecyclerView
    private lateinit var myadapter: adapter
    private lateinit var itemarraylist: ArrayList<item>
    private lateinit var database: DatabaseReference
    private lateinit var firebase: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val usernametop = findViewById<TextView>(R.id.utxt)
        database = Firebase.database.reference
        firebase = Firebase.auth


        val intent = intent


        val linear: LinearLayout = findViewById<LinearLayout>(R.id.l5)
        val addimg = findViewById<ImageView>(R.id.plus)
        val handler = Handler()
        val handler1 = Handler()
        var params: ViewGroup.LayoutParams = linear.layoutParams
        RecyclerView = findViewById(R.id.recview)
        RecyclerView.layoutManager = LinearLayoutManager(this)
        RecyclerView.setHasFixedSize(true)
        val youitem = findViewById<TextView>(R.id.youritmtxt)
        val allitem = findViewById<TextView>(R.id.allitemtxt)

        val userid = firebase.currentUser!!.uid

        database.child("user").child(userid).get().addOnSuccessListener {
            val name = it.child("name").value.toString()
            usernametop.setText(name)
        }



        itemarraylist = arrayListOf()
        myadapter = adapter(itemarraylist, this)
        RecyclerView.adapter = myadapter
        EventChangeListener()
        addimg.setOnClickListener {
            val intent = Intent(this, additem::class.java)
            startActivity(intent)
        }










    }

    private fun EventChangeListener() {
        val db = Firebase.firestore
        db.collection("items").addSnapshotListener(object: EventListener<QuerySnapshot>{
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {

                for (dc: DocumentChange in value?.documentChanges!!){
                    if(dc.type==DocumentChange.Type.ADDED){
                        itemarraylist.add(dc.document.toObject(item::class.java))
                    }
                }
                myadapter.notifyDataSetChanged()
            }

        })
    }
}