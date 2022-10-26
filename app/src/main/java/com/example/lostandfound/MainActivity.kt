package com.example.lostandfound

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage


class MainActivity : AppCompatActivity() {
    private lateinit var firebase: FirebaseAuth
    private lateinit var reason: FirebaseAuthException
    private lateinit var storage: FirebaseStorage
    private lateinit var uri: Uri
    private lateinit var database: DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db = Firebase.firestore
        firebase = Firebase.auth
        val intent1 = intent
        val intent = Intent(this, home::class.java)

        val emailLink = intent.data.toString()
        storage = Firebase.storage
        val storageref = storage.reference
        database = Firebase.database.reference


//        val actionCodeSettings = actionCodeSettings {
//            // URL you want to redirect back to. The domain (www.example.com) for this
//            // URL must be whitelisted in the Firebase Console.
//            url = "https://stud.iitp.ac.in/"
//            // This must be true
//            handleCodeInApp = true
//            setIOSBundleId("com.example.ios")
//            setAndroidPackageName(
//                "com.example.lostandfound",
//                true, /* installIfNotAvailable */
//                "12" /* minimumVersion */)
//        }




        val text1 = findViewById<EditText>(R.id.ul)
        val text2 = findViewById<EditText>(R.id.pl)
        val lbtn = findViewById<Button>(R.id.lbtn)
        val ltxt = findViewById<TextView>(R.id.ltxt)
        val rtxt = findViewById<TextView>(R.id.rtxt)
        val ur = findViewById<EditText>(R.id.ur)
        val pr = findViewById<EditText>(R.id.rp)
        val re = findViewById<EditText>(R.id.re)
        val rnum = findViewById<EditText>(R.id.rnum)
        val rbtn = findViewById<Button>(R.id.rbtn)
        val roll = findViewById<EditText>(R.id.roll)
        val confirmpass = findViewById<EditText>(R.id.rpc)
        val confirmtxt = findViewById<TextView>(R.id.confirmtxt)
        val profileimg = findViewById<ImageView>(R.id.profileimg)
        val addimgbtn = findViewById<Button>(R.id.addimgbtn)
        val nextbtn = findViewById<Button>(R.id.next)

        val currentUser = null
        if(currentUser != null){
            startActivity(intent)

        }
        val gallery = registerForActivityResult(ActivityResultContracts.GetContent(),
        ActivityResultCallback {
            profileimg.setImageURI(it)
            nextbtn.isVisible = true
            uri = it

        })

        addimgbtn.setOnClickListener {
            gallery.launch("image/*")
        }

        nextbtn.setOnClickListener {
            text1.isVisible = false
            text2.isVisible = false
            lbtn.isVisible = false
            ur.isVisible = true
            pr.isVisible = true
            re.isVisible = true
            rnum.isVisible = true
            rbtn.isVisible = true
            confirmpass.isVisible = true
            roll.isVisible = true
            ltxt.setTextColor(Color.DKGRAY)
            ltxt.setTextSize(22f)
            rtxt.setTextColor(Color.WHITE)
            rtxt.setTextSize(30f)
            profileimg.isVisible = false
            addimgbtn.isVisible = false
            nextbtn.isVisible = false
        }
        val load1 = findViewById<TextView>(R.id.load1)




        rtxt.setOnClickListener {
            load1.isVisible = false
            text1.isVisible = false
            text2.isVisible = false
            lbtn.isVisible = false
//            ur.isVisible = true
//            pr.isVisible = true
//            re.isVisible = true
//            rnum.isVisible = true
//            rbtn.isVisible = true
//            confirmpass.isVisible = true
//            roll.isVisible = true
            ltxt.setTextColor(Color.DKGRAY)
            ltxt.setTextSize(22f)
            rtxt.setTextColor(Color.WHITE)
            rtxt.setTextSize(30f)
            profileimg.isVisible = true
            addimgbtn.isVisible = true
            confirmtxt.isVisible = false

        }
        ltxt.setOnClickListener {
            load1.isVisible = false
            text1.isVisible = true
            text2.isVisible = true
            lbtn.isVisible = true
            ur.isVisible = false
            pr.isVisible = false
            re.isVisible = false
            rnum.isVisible = false
            rbtn.isVisible = false
            confirmpass.isVisible = false
            roll.isVisible = false
            rtxt.setTextColor(Color.DKGRAY)
            rtxt.setTextSize(22f)
            ltxt.setTextColor(Color.WHITE)
            ltxt.setTextSize(30f)
            profileimg.isVisible = false
            addimgbtn.isVisible = false
            nextbtn.isVisible = false
        }
        val txt1 = ur.text.toString()
        val userdb = db.collection("users")


        var count  = 0
        var downloadurl = ""

        rbtn.setOnClickListener {
            //if(txt1.trim().isEmpty() || pr.text.toString().trim().isEmpty() || re.text.toString().trim().isEmpty() || rnum.text.toString().trim().isEmpty()){
              //  Toast.makeText(applicationContext, "Please fill the fields", Toast.LENGTH_SHORT).show()
            //}
            if(ur.text.toString().trim().isNotEmpty() && pr.text.toString().trim().isNotEmpty() && re.text.toString().trim().isNotEmpty() && rnum.text.toString().trim().isNotEmpty()
                && roll.text.toString().trim().isNotEmpty() && confirmpass.text.toString().trim().isNotEmpty()){

                val users = hashMapOf("uesrname" to ur.text.toString(), "password" to pr.text.toString(), "email" to re.text.toString(), "phone" to rnum.text.toString(),
                "rollnumber" to roll.text.toString())

//                userdb.get().addOnCompleteListener {
//                    if(it.isSuccessful){
//                        count = 0
//                        for(document in it.result){
//
//                            if(rnum.text.toString()==document.data.getValue("phone")){
//                                count+=1
//                            }
//
//                        }
//                    }
//                }
//                if(count!=0){
//                    Toast.makeText(applicationContext, "Phone number is already registered", Toast.LENGTH_LONG).show()
//                }
//                if(count==0){
//
//
//
//
//                }
                if(pr.text.toString() ==  confirmpass.text.toString()){
                    if(re.text.toString().contains("@iitp.ac.in")){
                        if(pr.text.toString().length>6){
                            load1.isVisible = true

                            storageref.child(System.currentTimeMillis().toString()).putFile(uri).addOnSuccessListener {
                                task->
                                task.metadata!!.reference!!.downloadUrl
                                    .addOnSuccessListener {
                                        downloadurl = it.toString()
                                        firebase.createUserWithEmailAndPassword(re.text.toString(), pr.text.toString())
                                            .addOnCompleteListener(this) { task ->
                                                if (task.isSuccessful) {

                                                    firebase.currentUser?.sendEmailVerification()?.addOnSuccessListener {
                                                        confirmtxt.isVisible = true
                                                    }

                                                    userdb.add(users)

                                                    val userid = firebase.currentUser!!.uid

                                                    val usersss = usermodel(ur.text.toString(), re.text.toString(), pr.text.toString(), roll.text.toString(), downloadurl, rnum.text.toString())
                                                    database.child("user").child(userid).setValue(usersss)
                                                    //Toast.makeText(applicationContext, "You are now registered", Toast.LENGTH_SHORT).show()
                                                    text1.isVisible = true
                                                    load1.isVisible = false
                                                    text2.isVisible = true
                                                    lbtn.isVisible = true
                                                    ur.isVisible = false
                                                    pr.isVisible = false
                                                    re.isVisible = false
                                                    rnum.isVisible = false
                                                    rbtn.isVisible = false
                                                    roll.isVisible = false
                                                    confirmpass.isVisible = false

                                                    rtxt.setTextColor(Color.DKGRAY)
                                                    rtxt.setTextSize(22f)
                                                    ltxt.setTextColor(Color.WHITE)
                                                    ltxt.setTextSize(30f)
                                                    // Sign in success, update UI with the signed-in user's information

                                                    val user = firebase.currentUser


//                                        Firebase.auth.sendSignInLinkToEmail(re.text.toString(), actionCodeSettings)
//                                            .addOnCompleteListener { task ->
//                                                if (task.isSuccessful) {
//                                                    confirmtxt.isVisible = true
//                                                }
//                                            }

                                                } else {
                                                    // If sign in fails, display a message to the user.
                                                    //val message = firebase.res

                                                    Toast.makeText(baseContext, "This email is already in use",
                                                        Toast.LENGTH_SHORT).show()

                                                }
                                            }

                                    }
                            }


                        }
                        else{
                            Toast.makeText(applicationContext, "Password is weak", Toast.LENGTH_SHORT).show()
                        }
                    }
                    else{
                        Toast.makeText(applicationContext, "Enter IITP email", Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    Toast.makeText(applicationContext, "Password mismatched", Toast.LENGTH_SHORT).show()
                }


            }
            else{
                Toast.makeText(applicationContext, "Please fill the details", Toast.LENGTH_SHORT).show()
            }
        }

        lbtn.setOnClickListener {
//            if(text1.text.toString().trim().isEmpty() || text2.text.toString().trim().isEmpty()){
//                Toast.makeText(applicationContext, "Please fill the details", Toast.LENGTH_SHORT).show()
//            }
            if(text1.text.toString().trim().isNotEmpty() && text2.text.toString().trim().isNotEmpty()) {
//                userdb.whereEqualTo("phone", text1.text.toString())
//                    .get()
//                    .addOnCompleteListener {
//
//                        if(it.isSuccessful){
//                            for (document in it.result){
//                                if(text2.text.toString()==document.data.getValue("password")){
//                                    val usern = document.data.getValue("uesrname")
//                                    intent.putExtra("username", usern.toString())
//                                    startActivity(intent)
//                                }
//                                else{
//
//                                    Toast.makeText(applicationContext, "User not found", Toast.LENGTH_SHORT).show()
//                                }
//                            }
//                        }
//
//                    }
                firebase.signInWithEmailAndPassword(text1.text.toString(), text2.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val verification = firebase.currentUser?.isEmailVerified
                            if(verification==true){
                                val user = firebase.currentUser
                                startActivity(intent)

                            }
                            else{
                                Toast.makeText(applicationContext, "Account not verified", Toast.LENGTH_SHORT).show()
                            }
                            // Sign in success, update UI with the signed-in user's information


                            //updateUI(user)
                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                            //updateUI(null)
                        }
                    }

            }
            else{
                Toast.makeText(applicationContext, "Please fill the details", Toast.LENGTH_SHORT).show()
            }
        }




    }
}