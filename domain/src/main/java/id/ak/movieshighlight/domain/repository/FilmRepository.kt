package id.ak.movieshighlight.domain.repository

import androidx.paging.PagingData
import id.ak.movieshighlight.domain.entity.Movie
import id.ak.movieshighlight.domain.entity.MovieListItem
import id.ak.movieshighlight.domain.entity.TvSerial
import id.ak.movieshighlight.domain.entity.TvSerialListItem
import id.ak.movieshighlight.domain.entity.Watchlist
import kotlinx.coroutines.flow.Flow

interface FilmRepository {
    fun getMoviePagingData(): Flow<PagingData<MovieListItem>>
    fun getTvPagingData(): Flow<PagingData<TvSerialListItem>>
    suspend fun getMovieDetails(id: Int): Movie
    suspend fun getTvSerialDetails(id: Int): TvSerial
    fun getMovieWatchlistPagingData(): Flow<PagingData<Watchlist>>
    fun getTvWatchlistPagingData(): Flow<PagingData<Watchlist>>
    suspend fun addToWatchlist(watchlist: Watchlist)
    suspend fun removeFromWatchlist(id: Int, type: Watchlist.Type)
    fun isMovieInWatchlist(id: Int): Flow<Boolean>
    fun isTvInWatchlist(id: Int): Flow<Boolean>

    companion object {
        const val PAGE_SIZE = 20
    }
}