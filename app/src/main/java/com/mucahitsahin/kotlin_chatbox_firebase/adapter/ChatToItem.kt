package com.mucahitsahin.kotlin_chatbox_firebase.adapter

import com.mucahitsahin.kotlin_chatbox_firebase.R
import com.mucahitsahin.kotlin_chatbox_firebase.model.User
import com.squareup.picasso.Picasso
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.chat_it_recycler_row.view.*

class ChatToItem(val text:String,val user: User): Item<ViewHolder>() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.itChatTextView.text=text
        val uri=user.profileImage
        Picasso.get().load(uri).into(viewHolder.itemView.itChatImageView)
    }

    override fun getLayout(): Int {
        return R.layout.chat_it_recycler_row
    }
}