package id.ak.movieshighlight.domain.usecase.details

import id.ak.movieshighlight.domain.repository.FilmRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsInWatchlist @Inject constructor(private val repository: FilmRepository) {
    fun movie(id: Int): Flow<Boolean> {
        return repository.isMovieInWatchlist(id = id)
    }

    fun tvSerial(id: Int): Flow<Boolean> {
        return repository.isTvInWatchlist(id = id)
    }
}