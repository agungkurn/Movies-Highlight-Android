package id.ak.movieshighlight.domain.usecase.list

import id.ak.movieshighlight.domain.repository.FilmRepository
import javax.inject.Inject

class GetTvPagingData @Inject constructor(filmRepository: FilmRepository) {
    val data = filmRepository.tvPagingData
}