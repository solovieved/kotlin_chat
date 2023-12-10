package com.soloviev.chat.data

import java.util.Date

data class Message( val id: String, val from: String, val to: String, val text: String, val date: Date)
