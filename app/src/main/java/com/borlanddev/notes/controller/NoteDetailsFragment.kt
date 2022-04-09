package com.borlanddev.notes.controller

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.borlanddev.notes.R
import com.borlanddev.notes.model.Note
import com.borlanddev.notes.model.NoteDetailsViewModel
import java.util.*

class NoteDetailsFragment: Fragment(R.layout.fragment_details_note) {

    private lateinit var note: Note
    private lateinit var noteTitle: EditText
    private lateinit var noteText: EditText

    private val noteDetailsViewModel by lazy {
        ViewModelProviders.of(this).get(NoteDetailsViewModel::class.java)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        note = Note()

        noteTitle = view.findViewById(R.id.note_title) as EditText
        noteText = view.findViewById(R.id.note_text) as EditText

        // Явно указываем фрагмент менеджеру вызвывать функию обртаного вызова
        setHasOptionsMenu(true)

        val args = arguments?.getSerializable(ARG_NOTE_ID) as UUID

        // Загружаем заметку по переданному noteID
        noteDetailsViewModel.loadNote(args)

        updateUI()

        //******************************************************************************************

            /* Функция регестрирует наблюдателя за экземпляром LiveData и связи наблюдателя с
          жизненным циклом другого компонента */

            noteDetailsViewModel.noteLiveData.observe (

                viewLifecycleOwner)   /* Первый параметр функции observe - Владелец ЖЦ.
            (наблюдатель будет (жить) получать обновления данных столько,сколько живет Fragment) */

            /* Второй параметр, реализация Observer - наблюдатель (преобразовали SAM в лямбду).
                    Он отвечает за реакцию на новые данные из LiveData. Блок кода выполняется всякий раз
                        когда обновляется список в LiveData */
            { notes ->
                notes?.let {

                    updateUI()

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
            override fun afterTextChanged(sequence: Editable?) {} }

        // Добавление слушателя на Заголовок
        noteTitle.addTextChangedListener(titleWatcher)




        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(
                sequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int) {

                // преобразует ввод пользователя из CharSequence в String
                note.description = sequence.toString()
            }

            override fun afterTextChanged(sequence: Editable?) {}
        }

        // Добавление слушателя на Текст заметки
        noteText.addTextChangedListener(textWatcher)


    }



        // Вызывается когда возникает необходимость в меню
        override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
            super.onCreateOptionsMenu(menu, inflater)

            // запонялем меню
            inflater.inflate(R.menu.fragment_details_action, menu)
        }

        // Когда пользователь выбирает команду в меню фрагмент получает обратный вызов этой функции
        override fun onOptionsItemSelected(item: MenuItem): Boolean {

            // реагируем в зависимости от выбора команды в меню
            return when (item.itemId) {

                R.id.save_note_button -> {

                    noteDetailsViewModel.saveNote(note) // добавляем заметку в базу данных

                    updateUI()

                    Toast.makeText(context, "Saved note with title: ${note.title}",
                            Toast.LENGTH_SHORT).show()

                    true
                } // флаг - дальнейшая обработка менюшки не требуется

                else -> return super.onOptionsItemSelected(item)
            }
        }

        override fun onStop() {
            super.onStop()

            // При закрытии фрагмента сохраняем введенный текст
            noteDetailsViewModel.saveNote(note)
        }



    private fun updateUI() {

        noteTitle.setText(note.title)
        noteText.setText(note.description)
        // !!! Добавить реализацию Даты
    }



    companion object {
           const val ARG_NOTE_ID = "noteId"

        }
    }






