package com.borlanddev.notes.helpers

import android.app.Application
import com.borlanddev.notes.model.NoteRepository

/*   Получаем контекст всего приложения - информация о жизненном цикле самого приложения что бы
  наш синглтон репозитория был готов со старта приложения. */


// Экземпляр (обьект всего приложения) его Instance
class NoteApplication : Application () {


    // Вызывается системой когда приложение впервые загружается в память.
    override fun onCreate() {
        super.onCreate()

        // Иницализируем репозиторий при запуске приложения (жизненного цикла)
        NoteRepository.initialize(this)
    }

}