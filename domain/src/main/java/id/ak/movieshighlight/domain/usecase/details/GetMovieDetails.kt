package id.ak.movieshighlight.domain.usecase.details

import id.ak.movieshighlight.domain.entity.Movie
import id.ak.movieshighlight.domain.repository.FilmRepository
import id.ak.movieshighlight.domain.result.UseCaseResult
import javax.inject.Inject

class GetMovieDetails @Inject constructor(private val filmRepository: FilmRepository) {
    suspend operator fun invoke(id: Int): UseCaseResult<Movie> {
        return try {
            val result = filmRepository.getMovieDetails(id)
            UseCaseResult.Success(result)
        } catch (e: Exception) {
            UseCaseResult.Failed(e.message)
        }
    }
}