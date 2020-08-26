package com.mucahitsahin.kotlin_chatbox_firebase.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(val uid:String="",val username:String="",val profileImage:String=""):Parcelable {
}