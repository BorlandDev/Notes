package com.borlanddev.notes.model

import androidx.room.PrimaryKey
import java.util.*

data class Note (@PrimaryKey val id: UUID = UUID.randomUUID(),
                 var title: String = "",
                 var description: String = "",
                 var date: Date = Date() )

