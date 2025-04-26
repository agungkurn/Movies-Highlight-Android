package id.ak.movieshighlight.data.local.converters

import androidx.room.TypeConverter

class StringListConverter {
    @TypeConverter
    fun fromStringList(list: List<String>): String {
        return list.joinToString(separator = ",")
    }

    @TypeConverter
    fun toStringList(string: String): List<String> {
        return string.takeIf { it.isNotBlank() }?.split(",").orEmpty()
    }
}