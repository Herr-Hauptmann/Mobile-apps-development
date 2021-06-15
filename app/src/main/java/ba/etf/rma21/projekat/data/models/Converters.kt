package ba.etf.rma21.projekat.data.models

import androidx.room.TypeConverter
import java.util.*

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

    @TypeConverter
    fun listToString(lista : List<String>) : String{
        if (lista == null || lista.isEmpty())
            return ""
        var str : String = "";
        for (l in lista)
        {
            str+=l;
            str+=","
        }
        return str;
    }

    @TypeConverter
    fun stringToList(value: String?): List<String> {
        if(value == null || value.isBlank())
            return emptyList()
        return value.split(",")
    }
}