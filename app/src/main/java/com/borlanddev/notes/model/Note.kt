package com.borlanddev.notes.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Note (@PrimaryKey val id: UUID = UUID.randomUUID(),
                 var title: String = "",
                 var description: String = "",
                 var date: String = "09.07.21" ) // ВРЕМЕННЫЙ ФОРМАТ ДАТЫ

// Date = Date()
// DateFormat()
