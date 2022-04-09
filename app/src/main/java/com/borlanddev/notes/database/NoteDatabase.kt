package com.borlanddev.notes.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.borlanddev.notes.model.Note

// Указываем какие сущности использовать Базе и начальную версию
@Database (entities = [ Note::class], version = 1 )
@TypeConverters (NoteTypeConverters::class)

abstract class NoteDatabase: RoomDatabase() {


    abstract fun noteDao (): NoteDao


}