package id.ak.movieshighlight.domain.usecase

import androidx.paging.PagingSource
import id.ak.movieshighlight.domain.entity.Movie
import id.ak.movieshighlight.domain.repository.FilmRepository
import javax.inject.Inject

class GetMoviePagingSource @Inject constructor(private val filmRepository: FilmRepository) {
    val pageSize = 20

    operator fun invoke(): PagingSource<Int, Movie> {
        return filmRepository.getMoviePagingSource()
    }
}