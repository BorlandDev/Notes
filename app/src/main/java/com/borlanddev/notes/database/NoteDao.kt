package com.borlanddev.notes.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.borlanddev.notes.model.Note
import java.util.*

// Обьект для взаимодействий с таблицми БД (обновление, чтение, удаление, вставка)
@Dao

interface NoteDao {

    @Query("SELECT * FROM note") // Вернет список всех заметок
    fun getNotes (): LiveData<MutableList<Note>>

    @Query("SELECT * FROM note WHERE id=(:id)")  // Вернет заметку по конкретному UUID
    fun getNote (id: UUID): LiveData<Note?>

    @Update
    fun updateNote (note: Note)

    @Insert
    fun newNote (note: Note)

    @Delete
    fun deleteNote (vararg note: Note)


}


