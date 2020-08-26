package com.mucahitsahin.kotlin_chatbox_firebase.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.mucahitsahin.kotlin_chatbox_firebase.R
import com.mucahitsahin.kotlin_chatbox_firebase.activity.ChatActivity
import com.mucahitsahin.kotlin_chatbox_firebase.model.User
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.new_message_recycler_row.view.*

class NewMessageRecyclerAdapter(val userList:ArrayList<User>,val context: Context): RecyclerView.Adapter<NewMessageRecyclerAdapter.UserHolder>() {
    class UserHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.new_message_recycler_row,parent,false)
        return UserHolder(view)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {

            holder.itemView.recycler_row_username.text=userList[position].username
            Picasso.get().load(userList[position].profileImage).into(holder.itemView.recycler_row_imageview)
            holder.itemView.setOnClickListener {
                val intent=Intent(context,ChatActivity::class.java)
                intent.putExtra("userkey",userList[position])
                context.startActivity(intent)
            }


    }

    override fun getItemCount(): Int {
        return userList.size
    }

}