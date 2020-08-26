package com.mucahitsahin.kotlin_chatbox_firebase.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.mucahitsahin.kotlin_chatbox_firebase.adapter.ChatFromItem
import com.mucahitsahin.kotlin_chatbox_firebase.adapter.ChatToItem
import com.mucahitsahin.kotlin_chatbox_firebase.R
import com.mucahitsahin.kotlin_chatbox_firebase.model.ChatMessage
import com.mucahitsahin.kotlin_chatbox_firebase.model.User
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : AppCompatActivity() {


    val adapter=GroupAdapter<ViewHolder>()
    var toUser:User?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)


        toUser = intent.getParcelableExtra<User>("userkey")
        supportActionBar?.title=toUser!!.username

        buttonGonder.setOnClickListener {
            //mesaj gönderme
            mesajGonder()
        }

        recyclerViewMesaj.adapter=adapter
        mesajlariListele()

    }

    private fun mesajlariListele(){
        var fromId=FirebaseAuth.getInstance().uid
        var toId=toUser!!.uid

        val reference=FirebaseDatabase.getInstance().getReference("/user-messages/$fromId/$toId")
        reference.addChildEventListener(object :ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val chatMessage=snapshot.getValue(ChatMessage::class.java)

                if(chatMessage!=null){
                    if(chatMessage.fromId==FirebaseAuth.getInstance().uid){
                        val user=MainActivity.currentUser
                        adapter.add(ChatFromItem(chatMessage.text,user!!))
                    }
                    else{
                        adapter.add(ChatToItem(chatMessage.text,toUser!!))
                    }
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun mesajGonder(){


        var text=editTextTextMesaj.text.toString()
        var fromId=FirebaseAuth.getInstance().uid
        val user = intent.getParcelableExtra<User>("userkey")
        var toId=user!!.uid
        var sendTime=System.currentTimeMillis()/1000

        //var reference=FirebaseDatabase.getInstance().getReference("/messages").push()
        var reference=FirebaseDatabase.getInstance().getReference("/user-messages/$fromId/$toId").push()
        var toReference=FirebaseDatabase.getInstance().getReference("/user-messages/$toId/$fromId").push()

        if(fromId==null)return

        val chatMessage=ChatMessage(reference.key!!,text,fromId,toId,sendTime)
        reference.setValue(chatMessage)
        toReference.setValue(chatMessage)

        editTextTextMesaj.text.clear()
        recyclerViewMesaj.scrollToPosition(adapter.itemCount-1)
    }
}