package com.mucahitsahin.kotlin_chatbox_firebase.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.mucahitsahin.kotlin_chatbox_firebase.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth=FirebaseAuth.getInstance()

        val guncelKullanici=auth.currentUser
        if(guncelKullanici!=null){
            val intent=Intent(this,
                MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        registerTextview.setOnClickListener {
            val intent=Intent(this,RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }


    }

    fun girisYap(view:View){

        val email=emailRegisterEditText.text.toString()
        val sifre=passwordRegisterEditText.text.toString()

        auth.signInWithEmailAndPassword(email,sifre).addOnCompleteListener {task->

            if(task.isSuccessful){
                val guncelKullanici=auth.currentUser?.email.toString()
                Toast.makeText(this,"Guncel Kullanici ${guncelKullanici}",Toast.LENGTH_LONG).show()
                val intent=Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.addOnFailureListener{
            Toast.makeText(this,it.localizedMessage.toString(),Toast.LENGTH_LONG).show()
        }

    }


}