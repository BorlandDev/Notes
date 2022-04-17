package com.borlanddev.notes.controller

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.EditText
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.borlanddev.notes.R
import com.borlanddev.notes.model.Note
import com.borlanddev.notes.model.NoteDetailsViewModel
import java.text.SimpleDateFormat
import java.util.*

class NoteDetailsFragment: Fragment(R.layout.fragment_details_note) {

    private lateinit var note: Note
    private lateinit var noteTitle: EditText
    private lateinit var noteText: EditText

    private var currentTitle = ""
    private var currentText = ""

    private val noteDetailsViewModel by lazy {
        ViewModelProviders.of(this).get(NoteDetailsViewModel::class.java)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val navController = findNavController()

        val appBarConfiguration = AppBarConfiguration(navController.graph)

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)

        toolbar.setOnMenuItemClickListener {

            // Дата заметки изменятся только если изменяли ее текст
            if (currentTitle != note.title || currentText != note.description) {
                note.date = SimpleDateFormat("dd-MM-yyyy HH:mm").format(Date())
            }

            noteDetailsViewModel.saveNote(note) // добавляем заметку в базу данных
            updateUI()
            true
        }
        toolbar.setupWithNavController(navController, appBarConfiguration)




        //setHasOptionsMenu(true)


        noteTitle = view.findViewById(R.id.note_title) as EditText
        noteText = view.findViewById(R.id.note_text) as EditText

        note = Note()
        note.id = arguments?.getSerializable("noteId") as UUID

        // Загружаем заметку по переданному noteID
        noteDetailsViewModel.loadNote(note.id)


        //******************************************************************************************

        /* Функция регестрирует наблюдателя за экземпляром LiveData и связи наблюдателя с
          жизненным циклом другого компонента */

        noteDetailsViewModel.noteLiveData.observe(

            viewLifecycleOwner
        )   /* Первый параметр функции observe - Владелец ЖЦ.
            (наблюдатель будет (жить) получать обновления данных столько,сколько живет Fragment) */

        /* Второй параметр, реализация Observer - наблюдатель (преобразовали SAM в лямбду).
            Он отвечает за реакцию на новые данные из LiveData. Блок кода выполняется всякий раз
                        когда обновляется список в LiveData */
        { note ->
            note?.let {
             this.note = note
                     updateUI()

                currentTitle = note.title
                currentText = note.description

            }
        }
    }


    override fun onStart() {
        super.onStart()

        // создаем анонимный класс реализующий интерфейс TextWatcher (Слушатель/наблюдатель)
        val titleWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(
                sequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {

                // преобразует ввод пользователя из CharSequence в String
                note.title = sequence.toString()
            }

            override fun afterTextChanged(sequence: Editable?) {}
        }

        // Добавление слушателя на Заголовок
        noteTitle.addTextChangedListener(titleWatcher)

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(
                sequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {

                // преобразует ввод пользователя из CharSequence в String
                note.description = sequence.toString()
            }
            override fun afterTextChanged(sequence: Editable?) {}
        }

        // Добавление слушателя на Текст заметки
        noteText.addTextChangedListener(textWatcher)
    }



    private fun updateUI() {

        noteTitle.setText(note.title)
        noteText.setText(note.description)

    }


}






