package id.ak.movieshighlight

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.ak.movieshighlight.domain.usecase.details.GetMovieDetails
import id.ak.movieshighlight.domain.usecase.details.GetTvSerialDetails
import id.ak.movieshighlight.domain.usecase.details.IsInWatchlist
import id.ak.movieshighlight.domain.usecase.watchlist.AddToWatchlist
import id.ak.movieshighlight.domain.usecase.watchlist.GetWatchlistPagingData
import id.ak.movieshighlight.domain.usecase.watchlist.RemoveFromWatchlist

@EntryPoint
@InstallIn(SingletonComponent::class)
interface WatchlistDependencies {
    fun getWatchListPagingData(): GetWatchlistPagingData
    fun getMovieDetails(): GetMovieDetails
    fun getTvSerialDetails(): GetTvSerialDetails
    fun isInWatchlist(): IsInWatchlist
    fun addToWatchlist(): AddToWatchlist
    fun removeFromWatchlist(): RemoveFromWatchlist
}