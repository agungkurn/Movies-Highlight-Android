package id.ak.movieshighlight.domain.usecase.watchlist

import id.ak.movieshighlight.domain.repository.FilmRepository
import javax.inject.Inject

class GetWatchlistPagingData @Inject constructor(filmRepository: FilmRepository) {
    val movies = filmRepository.movieWatchlistPagingData
    val tvSeries = filmRepository.tvWatchlistPagingData
}