package com.soloviev.chat.data

import java.util.Date

data class User(
    val id:String? = null,
    val name: String? = null,
    val lastUpdate: Date? = null,
    val myContacts : MutableList<User> = ArrayList()
)
