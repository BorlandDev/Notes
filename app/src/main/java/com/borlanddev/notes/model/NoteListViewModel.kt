package com.borlanddev.notes.model

import androidx.lifecycle.ViewModel
import java.util.*

class NoteListViewModel: ViewModel() {

    private val noteRepository = NoteRepository.get()

    // Запрашиваем список заметок у репозитория
    val noteListLiveData = noteRepository.getNotes()


    fun saveNote (note: Note) { noteRepository.updateNote(note) }

    // Создание новой пустой заметки
    fun newNote(note: Note) {
        noteRepository.newNote(note)
    }



}