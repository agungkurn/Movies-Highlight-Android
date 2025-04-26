package id.ak.movieshighlight.domain.usecase.watchlist

import id.ak.movieshighlight.domain.entity.Watchlist
import id.ak.movieshighlight.domain.repository.FilmRepository
import javax.inject.Inject

class AddToWatchlist @Inject constructor(private val repository: FilmRepository) {
    suspend fun asMovie(id: Int, title: String, posterUrl: String) {
        repository.addToWatchlist(
            Watchlist(
                id = id,
                title = title,
                posterUrl = posterUrl,
                type = Watchlist.Type.Movie
            )
        )
    }

    suspend fun asTvSerial(id: Int, title: String, posterUrl: String) {
        repository.addToWatchlist(
            Watchlist(
                id = id,
                title = title,
                posterUrl = posterUrl,
                type = Watchlist.Type.TvSerial
            )
        )
    }
}