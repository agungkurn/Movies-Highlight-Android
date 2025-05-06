package id.ak.movieshighlight.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import id.ak.movieshighlight.data.local.WatchlistDatabase
import id.ak.movieshighlight.data.model.entity.toDomain
import id.ak.movieshighlight.data.model.entity.toMovieEntity
import id.ak.movieshighlight.data.model.entity.toTvSerialEntity
import id.ak.movieshighlight.data.pagingsource.MoviePagingSource
import id.ak.movieshighlight.data.pagingsource.TvPagingSource
import id.ak.movieshighlight.data.remote.FilmApi
import id.ak.movieshighlight.domain.entity.Watchlist
import id.ak.movieshighlight.domain.repository.FilmRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FilmRepositoryImpl @Inject constructor(
    private val filmApi: FilmApi,
    watchlistDatabase: WatchlistDatabase
) : FilmRepository {
    private val watchlistDao = watchlistDatabase.watchlistDao()

    override val moviePagingData = Pager(
        config = PagingConfig(FilmRepository.PAGE_SIZE),
        pagingSourceFactory = {
            MoviePagingSource(filmApi)
        }
    ).flow

    override val tvPagingData = Pager(
        config = PagingConfig(FilmRepository.PAGE_SIZE),
        pagingSourceFactory = {
            TvPagingSource(filmApi)
        }
    ).flow

    override fun getMovieDetails(id: Int) = flow {
        val response = filmApi.getMovieDetails(id)
        emit(response.toDomain())
    }.flowOn(Dispatchers.IO)

    override fun getTvSerialDetails(id: Int) = flow {
        val response = filmApi.getTvDetails(id)
        emit(response.toDomain())
    }.flowOn(Dispatchers.IO)

    override val movieWatchlistPagingData = Pager(
        config = PagingConfig(FilmRepository.PAGE_SIZE),
        pagingSourceFactory = {
            watchlistDao.getMovies()
        }
    ).flow.map {
        it.map { entity ->
            entity.toDomain()
        }
    }

    override val tvWatchlistPagingData = Pager(
        config = PagingConfig(FilmRepository.PAGE_SIZE),
        pagingSourceFactory = {
            watchlistDao.getTvSeries()
        }
    ).flow.map {
        it.map { entity ->
            entity.toDomain()
        }
    }

    override suspend fun addToWatchlist(watchlist: Watchlist) {
        when (watchlist.type) {
            Watchlist.Type.Movie -> {
                watchlistDao.insertWatchlistMovie(watchlist.toMovieEntity())
            }

            Watchlist.Type.TvSerial -> {
                watchlistDao.insertWatchlistTvSeries(watchlist.toTvSerialEntity())
            }
        }
    }

    override suspend fun removeFromWatchlist(id: Int, type: Watchlist.Type) {
        when (type) {
            Watchlist.Type.Movie -> {
                watchlistDao.deleteMovie(id)
            }

            Watchlist.Type.TvSerial -> {
                watchlistDao.deleteTvSerial(id)
            }
        }
    }

    override fun isMovieInWatchlist(id: Int): Flow<Boolean> {
        return watchlistDao.isMovieExists(id)
    }

    override fun isTvInWatchlist(id: Int): Flow<Boolean> {
        return watchlistDao.isTvSeriesExists(id)
    }
}