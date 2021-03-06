package com.borlanddev.notes.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import java.util.*

class NoteListViewModel: ViewModel() {

    private val noteRepository = NoteRepository.get()

    // Запрашиваем список заметок у репозитория
    val noteListLiveData: LiveData<MutableList<Note>> = noteRepository.getNotes()



    // Создание новой пустой заметки
    fun newNote(note: Note) {
        noteRepository.newNote(note)
    }



    fun deleteNote(id: UUID) {
        noteRepository.deleteNote(id)
    }



}