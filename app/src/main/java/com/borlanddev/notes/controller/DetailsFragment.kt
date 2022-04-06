package com.borlanddev.notes.controller

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.borlanddev.notes.R
import com.borlanddev.notes.model.Note

class DetailsFragment: Fragment() {

    private lateinit var note: Note
    private lateinit var title: EditText
    private lateinit var textNote: EditText





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // явно указываем фрагмент менеджеру вызвывать функию обртаного вызова
        setHasOptionsMenu(true)

        note = Note()

    }










    // аналог setContentView, настраивает и возвращает готовую верстку экрана
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_details, container, false)

        title = view.findViewById(R.id.title_note) as EditText
        textNote = view.findViewById(R.id.text_note) as EditText

        return view
    }


    override fun onStart() {
        super.onStart()


        // создаем анонимный класс реализующий интерфейс TextWatcher (Слушатель/наблюдатель)
        val titleWatcher = object : TextWatcher {

            override fun beforeTextChanged(
                s: CharSequence?, start: Int, count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                sequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                // преобразует ввод пользователя из CharSequence в String
                note.title = sequence.toString()
            }

            override fun afterTextChanged(sequance: Editable?) {}
        }
    }







    // Вызывается когда возникает необходимость в меню
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        // запонялем меню
        inflater.inflate(R.menu.fragment_details_action, menu)

    }

    companion object { // Инкапуслируем получение нашего фрагмента , для активити и пр.
        fun newInstance(): DetailsFragment = DetailsFragment()
    }

}