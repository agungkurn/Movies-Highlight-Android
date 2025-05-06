package id.ak.movieshighlight.domain.usecase.details

import id.ak.movieshighlight.domain.repository.FilmRepository
import id.ak.movieshighlight.domain.result.asUseCaseResult
import javax.inject.Inject

class GetMovieDetails @Inject constructor(private val filmRepository: FilmRepository) {
    operator fun invoke(id: Int) = filmRepository.getMovieDetails(id).asUseCaseResult()
}
