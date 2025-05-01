package id.ak.movieshighlight.domain.usecase.details

import id.ak.movieshighlight.domain.entity.TvSerial
import id.ak.movieshighlight.domain.repository.FilmRepository
import id.ak.movieshighlight.domain.result.UseCaseResult
import javax.inject.Inject

class GetTvSerialDetails @Inject constructor(private val filmRepository: FilmRepository) {
    suspend operator fun invoke(id: Int): UseCaseResult<TvSerial> {
        return try {
            val result = filmRepository.getTvSerialDetails(id)
            UseCaseResult.Success(result)
        } catch (e: Exception) {
            e.printStackTrace()
            UseCaseResult.Failed(e.message)
        }
    }
}