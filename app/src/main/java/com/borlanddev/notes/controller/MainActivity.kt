package com.borlanddev.notes.controller


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.borlanddev.notes.R
import java.util.*

class MainActivity : AppCompatActivity()  {









    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* Находим фрагмент через айдишку контейнера, запрашивая ее у менеджера фрагментов.
               Если фрагмен уже существовал - вернется его экземпляр */
        val currentFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container)


        // создание экземпляра нашего фрагмента (если до этого он не существовал)
        if (currentFragment == null) {
            val fragment = NoteListFragment.newInstance()       //NoteDetailsFragment.newInstance()

            // создает и закрпляет транзакцию фрагмента
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container , fragment) // добавляет фрагмент в транзакцию
                .commit()
        }
    }


}



    /*

    , NoteListFragment.Callbacks { // ВРЕМЕННАЯ РЕАЛИЗАЦИЯ НАВИГАЦИИ


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* Находим фрагмент через айдишку контейнера, запрашивая ее у менеджера фрагментов.
               Если фрагмен уже существовал - вернется его экземпляр */
        val currentFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container)


        // создание экземпляра нашего фрагмента (если до этого он не существовал)
        if (currentFragment == null) {
            val fragment = NoteListFragment.newInstance()

            // создает и закрпляет транзакцию фрагмента
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container , fragment) // добавляет фрагмент в транзакцию
                .commit()
        }
    }



    // ВРЕМЕННАЯ РЕАЛИЗАЦИЯ НАВИГАЦИИ
    override fun onNoteSelected(noteId: UUID) {

        // передаем информацию о выбранной заметке в списке
        val fragment = NoteDetailsFragment.newInstance(noteId)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack("Реализация кнопки назад - вернет к предыдущему фрагменту")
            .commit()
    }



}

     */