package com.borlanddev.notes.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.util.*

class NoteDetailsViewModel: ViewModel() {

    private val noteRepository = NoteRepository.get()

    // Храним заметку полученную из БД возвращенную функцией loadCrime
    private val noteIdLiveData = MutableLiveData<UUID>()


    var noteLiveData: LiveData<Note?> =
            Transformations.switchMap(noteIdLiveData) { noteId ->

                noteRepository.getNote(noteId)
            }


    // Определяет какую заметку нужно вывести на экран
    fun loadNote (noteId: UUID) { noteIdLiveData.value = noteId }

    // Сохраняем текст введенный в EditText, в базу данных
    fun saveNote (note: Note) { noteRepository.updateNote(note) }

}