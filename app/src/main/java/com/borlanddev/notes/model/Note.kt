package com.borlanddev.notes.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Note (@PrimaryKey var id: UUID = UUID.randomUUID(),
                 var title: String = "",
                 var description: String = "",
                 var date: String = "") // ВРЕМЕННЫЙ ФОРМАТ ДАТЫ


