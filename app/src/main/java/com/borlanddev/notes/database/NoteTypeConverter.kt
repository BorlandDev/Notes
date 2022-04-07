package com.borlanddev.notes.database

import androidx.room.TypeConverter
import java.util.*

class NoteTypeConverter {

/* Учим базу работать (сериализовать) с нашими типами данных, т.к. по дефолту она работает только
с примитивами. */


    @TypeConverter // конвертируем данные для сохранения в базе
    fun toUUID(uuid: String?): UUID? = UUID.fromString(uuid)

    @TypeConverter // конвертируем данные из базы обратно в исходный формат
    fun fromUUID(uuid: UUID?): String? = uuid?.toString()


}


