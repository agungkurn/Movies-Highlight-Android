package id.ak.movieshighlight.domain.repository

import androidx.paging.PagingData
import id.ak.movieshighlight.domain.entity.Movie
import id.ak.movieshighlight.domain.entity.MovieListItem
import id.ak.movieshighlight.domain.entity.TvSerial
import id.ak.movieshighlight.domain.entity.TvSerialListItem
import id.ak.movieshighlight.domain.entity.Watchlist
import kotlinx.coroutines.flow.Flow

interface FilmRepository {
    val moviePagingData: Flow<PagingData<MovieListItem>>
    val tvPagingData: Flow<PagingData<TvSerialListItem>>
    val movieWatchlistPagingData: Flow<PagingData<Watchlist>>
    val tvWatchlistPagingData: Flow<PagingData<Watchlist>>
    fun getMovieDetails(id: Int): Flow<Movie>
    fun getTvSerialDetails(id: Int): Flow<TvSerial>
    suspend fun addToWatchlist(watchlist: Watchlist)
    suspend fun removeFromWatchlist(id: Int, type: Watchlist.Type)
    fun isMovieInWatchlist(id: Int): Flow<Boolean>
    fun isTvInWatchlist(id: Int): Flow<Boolean>

    companion object {
        const val PAGE_SIZE = 20
    }
}