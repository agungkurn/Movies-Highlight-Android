package id.ak.movieshighlight.domain.repository

import androidx.paging.PagingSource
import id.ak.movieshighlight.domain.entity.Movie
import id.ak.movieshighlight.domain.entity.MovieListItem
import id.ak.movieshighlight.domain.entity.TvSerial
import id.ak.movieshighlight.domain.entity.TvSerialListItem

interface FilmRepository {
    fun getMoviePagingSource(): PagingSource<Int, MovieListItem>
    fun getTvPagingSource(): PagingSource<Int, TvSerialListItem>
    suspend fun getMovieDetails(id: Int): Movie
    suspend fun getTvSerialDetails(id: Int): TvSerial
}