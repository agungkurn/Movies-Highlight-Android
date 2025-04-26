package id.ak.movieshighlight.data.local.converters

import androidx.room.TypeConverter

class IntListConverter {
    @TypeConverter
    fun fromIntList(list: List<Int>): String {
        return list.joinToString(",")
    }

    @TypeConverter
    fun toIntList(string: String): List<Int> {
        return string.split(",").map { it.toInt() }
    }
}