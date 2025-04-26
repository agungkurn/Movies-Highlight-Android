package id.ak.movieshighlight.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DiscoverMovieResponse(
    @SerialName("results")
    val results: List<DiscoverMovieModel>? = null
) : ListResponse()
