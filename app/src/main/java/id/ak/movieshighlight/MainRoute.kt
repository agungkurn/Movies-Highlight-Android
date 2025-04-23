package id.ak.movieshighlight

import kotlinx.serialization.Serializable

@Serializable
sealed class MainRoute {
    @Serializable
    data object Home : MainRoute()

    @Serializable
    data class Details(val movieId: Int? = null, val tvSerialId: Int? = null) : MainRoute()
}