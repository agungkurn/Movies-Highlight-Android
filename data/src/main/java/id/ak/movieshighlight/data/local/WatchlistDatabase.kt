package id.ak.movieshighlight.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import id.ak.movieshighlight.data.model.entity.MovieEntity
import id.ak.movieshighlight.data.model.entity.TvSerialEntity

@Database(
    entities = [MovieEntity::class, TvSerialEntity::class],
    version = 1,
    exportSchema = false
)
abstract class WatchlistDatabase : RoomDatabase() {
    abstract fun watchlistDao(): WatchlistDao

    companion object {
        const val DATABASE_NAME = "watchlist_db"
    }
}