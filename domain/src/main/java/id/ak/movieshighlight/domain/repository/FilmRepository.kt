package id.ak.movieshighlight.domain.repository

import androidx.paging.PagingSource
import id.ak.movieshighlight.domain.entity.Movie
import id.ak.movieshighlight.domain.entity.TvSerial

interface FilmRepository {
    fun getMoviePagingSource(): PagingSource<Int, Movie>
    fun getTvPagingSource(): PagingSource<Int, TvSerial>
}