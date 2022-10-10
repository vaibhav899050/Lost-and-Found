package com.example.lostandfound

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
        RecyclerView = findViewById(R.id.recview)
        RecyclerView.layoutManager = LinearLayoutManager(this)
        RecyclerView.setHasFixedSize(true)



        itemarraylist = arrayListOf()
        myadapter = adapter(itemarraylist)
        RecyclerView.adapter = myadapter
        EventChangeListener()








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