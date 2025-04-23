package id.ak.movieshighlight.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class DiscoverTvResponse(
    @SerialName("results")
    val results: List<DiscoverTvModel>? = null
) : ListResponse()
