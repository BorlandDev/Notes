package com.borlanddev.notes.model

import androidx.lifecycle.ViewModel
import java.util.*

class ListViewModel: ViewModel() {

    val notes = mutableListOf<Note>()

    init {
        for (i in 0 until 100) {
            val note = Note()
            note.title = "Список покупок #$i"
            note.description = "Хлеб, вода, вареники, яблоки, бананы, картошка, мука, чипсы, кактусы "
            note.date = "10.07.21"
            notes += note
        }
    }

}