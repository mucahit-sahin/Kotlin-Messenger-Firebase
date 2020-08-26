package com.mucahitsahin.kotlin_chatbox_firebase.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.mucahitsahin.kotlin_chatbox_firebase.R
import com.mucahitsahin.kotlin_chatbox_firebase.adapter.NewMessageRecyclerAdapter
import com.mucahitsahin.kotlin_chatbox_firebase.model.User
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_new_message.*

class NewMessageActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database:FirebaseDatabase
    private lateinit var recyclerViewAdapter: NewMessageRecyclerAdapter

    //val adapter= GroupAdapter<ViewHolder>()

    val userList=ArrayList<User>()
    lateinit var layoutManager:LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_message)

        auth=FirebaseAuth.getInstance()
        database= FirebaseDatabase.getInstance()

        supportActionBar?.title="Kişi Seç"

        //recyclerViewNewMessage.adapter=adapter
        verileriAl()

    }

    private fun verileriAl(){

        userList.clear()
        val ref=database.getReference("/users")
        ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                snapshot.children.forEach {
                    val user=it.getValue(User::class.java)
                    userList.add(user!!)
                }
                for (va in userList){
                    Log.d("deneme",va.username)
                    Log.d("deneme",va.profileImage)
                }

                val adapter=NewMessageRecyclerAdapter(userList,this@NewMessageActivity)
                recyclerViewNewMessage.layoutManager = LinearLayoutManager(this@NewMessageActivity)
                recyclerViewNewMessage.adapter = adapter

                Log.d("yeniMesaj","kisiler geldi")

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }


}