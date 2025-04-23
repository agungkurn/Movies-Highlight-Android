package id.ak.movieshighlight.data.repository

import androidx.paging.PagingSource
import id.ak.movieshighlight.data.pagingsource.MoviePagingSource
import id.ak.movieshighlight.data.pagingsource.TvPagingSource
import id.ak.movieshighlight.data.remote.FilmApi
import id.ak.movieshighlight.domain.entity.Movie
import id.ak.movieshighlight.domain.entity.TvSerial
import id.ak.movieshighlight.domain.repository.FilmRepository
import javax.inject.Inject

class FilmRepositoryImpl @Inject constructor(private val filmApi: FilmApi) : FilmRepository {
    override fun getMoviePagingSource(): PagingSource<Int, Movie> {
        return MoviePagingSource(filmApi)
    }

    override fun getTvPagingSource(): PagingSource<Int, TvSerial> {
        return TvPagingSource(filmApi)
    }
}