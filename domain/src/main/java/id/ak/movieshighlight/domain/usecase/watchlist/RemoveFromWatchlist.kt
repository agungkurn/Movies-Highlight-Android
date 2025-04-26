package id.ak.movieshighlight.domain.usecase.watchlist

import id.ak.movieshighlight.domain.entity.Watchlist
import id.ak.movieshighlight.domain.repository.FilmRepository
import javax.inject.Inject

class RemoveFromWatchlist @Inject constructor(private val repository: FilmRepository) {
    suspend operator fun invoke(id: Int, type: Watchlist.Type) {
        repository.removeFromWatchlist(id = id, type = type)
    }
}