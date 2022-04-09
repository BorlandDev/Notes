package com.borlanddev.notes.model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.borlanddev.notes.database.NoteDatabase
import java.lang.IllegalStateException
import java.util.*
import java.util.concurrent.Executors

private const val DATABASE_NAME = "note_database"

class NoteRepository private constructor (context: Context) {

    // Создаем БД и ссылаемся на ее реализацию
    private val database: NoteDatabase = Room.databaseBuilder(
        context.applicationContext, // Для обращения к файловой системе нужен контекст всего приложения
        NoteDatabase::class.java, // Ссылка на класс базы которую Room должен создать
        DATABASE_NAME)
        .build()


    // Репозиторий вызывает функции через интерфейс noteDao

    // Храним ссылку на интерфейс DAO
    private val noteDao = database.noteDao()

    // Функция вернет экземпляр исполнителся указывющий на новый поток
    private val executor = Executors.newSingleThreadExecutor()



    /* Как updateNote так и addNote оборачивают вызовы в Dao внутри блока execute { }.
               Он выталкиват эти операции из основного потока чобы не блокировать работу UI  */


    fun getNotes(): LiveData<MutableList<Note>> = noteDao.getNotes() // Вернет список всех заметок

    fun getNote(id: UUID): LiveData<Note?> = noteDao.getNote(id) // Вернет заметку по конкретному UUID

    fun deleteNote(note: Note) {
        executor.execute {
            noteDao.deleteNote(note)
        }
    }


        // Обновит переданную заметку
        fun updateNote(note: Note) {
            executor.execute { noteDao.updateNote(note) }
        }

        // Создаст новую пустую заметку
        fun newNote(note: Note) {
            executor.execute { noteDao.newNote(note) }
        }




        companion object {

        private var INSTANCE: NoteRepository? = null

        // Инициализация синглтона если он еще не был создан
        fun initialize (context: Context) {
            if (INSTANCE == null) INSTANCE = NoteRepository(context)
        }

        // Обеспечивает доступ к синглтону репозитория
        fun get (): NoteRepository {
            return INSTANCE ?:
            throw IllegalStateException("NoteRepository must be initialized")
        }
    }



}