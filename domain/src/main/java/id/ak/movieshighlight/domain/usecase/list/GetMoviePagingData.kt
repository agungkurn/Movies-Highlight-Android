package id.ak.movieshighlight.domain.usecase.list

import id.ak.movieshighlight.domain.repository.FilmRepository
import javax.inject.Inject

class GetMoviePagingData @Inject constructor(filmRepository: FilmRepository) {
    val data = filmRepository.moviePagingData
}