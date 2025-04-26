package id.ak.movieshighlight.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.ak.movieshighlight.data.model.entity.MovieEntity
import id.ak.movieshighlight.data.model.entity.TvSerialEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WatchlistDao {
    @Query("SELECT * FROM MovieEntity")
    fun getMovies(): PagingSource<Int, MovieEntity>

    @Query("SELECT * FROM TvSerialEntity")
    fun getTvSeries(): PagingSource<Int, TvSerialEntity>

    @Query("SELECT * FROM MovieEntity WHERE id = :id")
    fun getMovieById(id: Int): Flow<MovieEntity?>

    @Query("SELECT * FROM TvSerialEntity WHERE id = :id")
    fun geTvSeriesById(id: Int): Flow<TvSerialEntity?>

    @Query("DELETE FROM MovieEntity WHERE id = :id")
    suspend fun deleteMovie(id: Int)

    @Query("DELETE FROM TvSerialEntity WHERE id = :id")
    suspend fun deleteTvSerial(id: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWatchlistMovie(movieEntity: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWatchlistTvSeries(tvSerialEntity: TvSerialEntity)
}