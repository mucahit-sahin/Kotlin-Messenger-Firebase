package com.mucahitsahin.kotlin_chatbox_firebase.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mucahitsahin.kotlin_chatbox_firebase.R
import com.mucahitsahin.kotlin_chatbox_firebase.model.User
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_new_message.view.*
import kotlinx.android.synthetic.main.new_message_recycler_row.view.*

class NewMessageRecyclerAdapter(val userList:ArrayList<User>): RecyclerView.Adapter<NewMessageRecyclerAdapter.UserHolder>() {
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
    }

    override fun getItemCount(): Int {
        return userList.size
    }

}