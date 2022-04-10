package com.borlanddev.notes.database

import androidx.room.TypeConverter
import java.util.*

class NoteTypeConverters {

/* Учим базу работать (сериализовать) с нашими типами данных, т.к. по дефолту она работает только
с примитивами. */


    @TypeConverter
    fun fromDate (date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun toDate (millisSinceEpoch: Long?): Date? {

        return millisSinceEpoch?.let {
            Date(it)
        }
    }

    @TypeConverter // конвертируем данные для сохранения в базе
    fun fromUUID(uuid: UUID?): String? = uuid?.toString()


    @TypeConverter   // конвертируем данные из базы обратно в исходный формат
    fun toUUID(uuid: String?): UUID? = UUID.fromString(uuid)

}


