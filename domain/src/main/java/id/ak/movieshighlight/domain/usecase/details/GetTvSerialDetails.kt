package id.ak.movieshighlight.domain.usecase.details

import id.ak.movieshighlight.domain.repository.FilmRepository
import id.ak.movieshighlight.domain.result.asUseCaseResult
import javax.inject.Inject

class GetTvSerialDetails @Inject constructor(private val filmRepository: FilmRepository) {
    operator fun invoke(id: Int) = filmRepository.getTvSerialDetails(id).asUseCaseResult()
}