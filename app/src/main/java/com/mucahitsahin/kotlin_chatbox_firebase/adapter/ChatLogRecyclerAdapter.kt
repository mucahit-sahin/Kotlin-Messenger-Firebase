package com.mucahitsahin.kotlin_chatbox_firebase.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mucahitsahin.kotlin_chatbox_firebase.R
import com.mucahitsahin.kotlin_chatbox_firebase.model.ChatMessage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_chat.view.*
import kotlinx.android.synthetic.main.chat_me_recycler_row.view.*
import kotlinx.android.synthetic.main.new_message_recycler_row.view.*

class ChatLogRecyclerAdapter(val mesajlar:ArrayList<ChatMessage>): RecyclerView.Adapter<ChatLogRecyclerAdapter.ChatLogHolder>() {
    class ChatLogHolder(itemView:View):RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatLogHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.chat_me_recycler_row,parent,false)
        return ChatLogHolder(view)
    }

    override fun onBindViewHolder(holder: ChatLogHolder, position: Int) {
        holder.itemView.meChatTextView.text=mesajlar[position].text
    }

    override fun getItemCount(): Int {
        return mesajlar.size
    }
}