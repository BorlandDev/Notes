package com.borlanddev.notes.helpers

import com.borlanddev.notes.model.Note
import java.util.*


 fun imitationData(): List<Note> {


    return listOf(

        Note(UUID.randomUUID(), "Написать тестовое задание для фирмы 4k Soft Житомир", "", "06-04-22 22:50"),
        Note(UUID.randomUUID(), "ДР Игоря", "Поздравить", "13-01-22 18:00"),
        Note(UUID.randomUUID(), "Поздравсь мам с др", "Написать поздравление", "22-02-22 23:55" ),
        Note(UUID.randomUUID(), "Диета", "РАСТИТЕЛЬНАЯ КЛЕТЧАТКА — овощи (свекла, морковь, цветная капуста. помидоры, огурцы) , Овощи рекомендуется употреблять не менее 1 раза в день в сыром виде, а так же готовить на пару, запекать или использовать для приготовления супов. ", "04-12-21 12:00"),
        Note(UUID.randomUUID(), "Игры", "Doom, Stalker, NFS, World of Tanks", "15-03-21 17:12"),
        Note(UUID.randomUUID(), "Подписки", "ИНТЕРНЕТ И СВЯЗЬ 100 ГРН (10 ЧИСЛА). SPOTIFY 140 ГРН (19 ЧИСЛА). YT PREMIUM 100 ГРН (22 ЧИСЛА). GOOGLE ONE 55 ГРН 100ГБ", "11-03-22 20:05"),
        Note(UUID.randomUUID(), "Создать курс", "Курс: Моя трансформация. 1) Записываем желания в тетрадь", "01-12-21 08:44"),
        Note(UUID.randomUUID(), "Команды в IDEA и Studio", "Ctrl + D , и курсор на нужной строке - копирует строку. Ctrl+Shift+Space нажать дважды - подх символы через геттеры и сеттеры в текущ контексте. ", "24-09-21 17:34"),
        Note(UUID.randomUUID(), "Список книг по праграммированию .", "1) ---> Структура и интерпретация компьютерных программ (Харольд Абельсон, Джеральд Джей Сассман) . 2) ---> Грокаем алгоритмы (Адитья Бхаргава)", "04-02-22 11:23" ),
        Note(UUID.randomUUID(), "Книги Архетиктура", " Код. Тайный язык информатики (Чарльз Петцольд) ✓", "12-08-21 03:53"),
        Note(UUID.randomUUID(), "Дизайн", "Дизайн привычных вещей (Donald Norman), Не заставляйте меня думать (Steve Krug)", "27-11-21 15:22"),
        Note(UUID.randomUUID(), "Команды в ОС", "Win + написать команду shotdown -s -t (указать время в сек до выключения комп.)", "11-10-21 10:40"),
        Note(UUID.randomUUID(), "Список продуктов", "Хлеб, вода, салат, редис, колбаса", "12-04-22 14:33"),
        Note(UUID.randomUUID(), "Кино", "Бэтмен, 500 дней лета, Ла-ла-лэнд, Начало, Назад в будущее, Дюнкерк, Петровы в Гриппе", "07-06-21 12:00"),
        Note(UUID.randomUUID(), "Аптека", "Парацетомол дарница 500гр, сироп, пластырь, мед карту", "25-10-21 09:12"))
 }