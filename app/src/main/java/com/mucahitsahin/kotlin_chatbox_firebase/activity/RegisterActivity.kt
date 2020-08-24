package com.mucahitsahin.kotlin_chatbox_firebase.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.mucahitsahin.kotlin_chatbox_firebase.R
import com.mucahitsahin.kotlin_chatbox_firebase.model.User
import kotlinx.android.synthetic.main.activity_register.*
import java.util.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth=FirebaseAuth.getInstance()

        registerButton.setOnClickListener {
            kayitOl()
        }

        loginTextview.setOnClickListener {
            val intent= Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }


        selectPhotoImageview.setOnClickListener {
            //resim seçme ekranı açılır
            val intent=Intent(Intent.ACTION_PICK)
            intent.type="image/*"
            startActivityForResult(intent,0)
        }
    }

    var selectedPhotoUri:Uri?=null
    //resim secildikten sonra yakalama
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode==0&&resultCode== Activity.RESULT_OK&& data!=null){
            selectedPhotoUri=data.data
            val bitmap=MediaStore.Images.Media.getBitmap(contentResolver,selectedPhotoUri)

            selectPhotoImageview.setImageBitmap(bitmap)
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun kayitOl(){
        val email=emailRegisterEditText.text.toString()
        val sifre=passwordRegisterEditText.text.toString()
        auth.createUserWithEmailAndPassword(email,sifre).addOnCompleteListener {
            if(it.isSuccessful){
                val guncelKullanici=auth.currentUser?.email.toString()
                Toast.makeText(this,"Guncel Kullanici ${guncelKullanici}", Toast.LENGTH_LONG).show()
                val intent=Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

                selectedPhotoUri?.let {
                    uploadImageToFirebase(it)
                }
            }

        }.addOnFailureListener {
            Toast.makeText(this,it.localizedMessage.toString(), Toast.LENGTH_LONG).show()
        }
    }

    private fun uploadImageToFirebase(uri: Uri){
        val filename=UUID.randomUUID().toString()
        val ref=FirebaseStorage.getInstance().getReference("/images/${filename}")
        ref.putFile(uri).addOnSuccessListener {
            ref.downloadUrl.addOnSuccessListener {
                saveUserToFirebaseDataBase(it.toString())
            }
        }
    }

    private fun saveUserToFirebaseDataBase(profileImageUrl:String){
        val uid=FirebaseAuth.getInstance().uid?:""
        val ref=FirebaseDatabase.getInstance().getReference("/users/${uid}")

        val user=User(uid.toString(),kullaniciAdiEditText.text.toString(),profileImageUrl)
        ref.setValue(user).addOnSuccessListener {
            //başarılı şekilde kaydoldu
        }
    }

}