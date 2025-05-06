package id.ak.movieshighlight.domain.usecase.details

import id.ak.movieshighlight.domain.repository.FilmRepository
import javax.inject.Inject

class IsInWatchlist @Inject constructor(private val repository: FilmRepository) {
    fun movie(id: Int) = repository.isMovieInWatchlist(id = id)
    fun tvSerial(id: Int) = repository.isTvInWatchlist(id = id)
}