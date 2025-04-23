package id.ak.movieshighlight.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class DiscoverMovieResponse(
    @SerialName("results")
    val results: List<DiscoverMovieModel>? = null
) : ListResponse()
