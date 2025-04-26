package id.ak.movieshighlight.watchlist

import kotlinx.serialization.Serializable

@Serializable
sealed class WatchlistRoute {
    @Serializable
    data object List : WatchlistRoute()

    @Serializable
    data class Details(val movieId: Int? = null, val tvSerialId: Int? = null) : WatchlistRoute()
}