package com.borlanddev.notes.controller


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.borlanddev.notes.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* Находим фрагмент через айдишку контейнера, запрашивая ее у менеджера фрагментов.
               Если фрагмен уже существовал - вернется его экземпляр */
        val currentFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container)


        // создание экземпляра нашего фрагмента (если до этого он не существовал)
        if (currentFragment == null) {
            val fragment = ListFragment.newInstance()

            // создает и закрпляет транзакцию фрагмента
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container , fragment) // добавляет фрагмент в транзакцию
                .commit()
        }
    }


}