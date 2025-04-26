package id.ak.movieshighlight.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import id.ak.movieshighlight.data.local.converters.IntListConverter
import id.ak.movieshighlight.data.local.converters.StringListConverter
import id.ak.movieshighlight.data.model.entity.MovieEntity
import id.ak.movieshighlight.data.model.entity.TvSerialEntity

@Database(
    entities = [MovieEntity::class, TvSerialEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(value = [StringListConverter::class, IntListConverter::class])
abstract class WatchlistDatabase : RoomDatabase() {
    abstract fun watchlistDao(): WatchlistDao

    companion object {
        const val DATABASE_NAME = "watchlist_db"
    }
}