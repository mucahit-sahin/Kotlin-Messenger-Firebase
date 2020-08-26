package com.mucahitsahin.kotlin_chatbox_firebase.model

class ChatMessage(val id:String="",
                  val text:String="",
                  val fromId:String="",
                  val toId:String="",
                  val sendTime:Long=0) {
}