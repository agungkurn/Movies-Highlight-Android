package id.ak.movieshighlight

import kotlinx.serialization.Serializable

@Serializable
sealed class MainRoute {
    @Serializable
    data object Home : MainRoute()
}