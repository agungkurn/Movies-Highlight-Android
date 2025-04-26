package id.ak.movieshighlight.domain.usecase.watchlist

import id.ak.movieshighlight.domain.repository.FilmRepository
import javax.inject.Inject

class GetWatchlistPagingData @Inject constructor(private val filmRepository: FilmRepository) {
    val movies get() = filmRepository.getMovieWatchlistPagingData()
    val tvSeries get() = filmRepository.getTvWatchlistPagingData()
}