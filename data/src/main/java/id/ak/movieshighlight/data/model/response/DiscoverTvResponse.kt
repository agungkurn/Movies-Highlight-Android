package id.ak.movieshighlight.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DiscoverTvResponse(
    @SerialName("results")
    val results: List<DiscoverTvModel>? = null
) : ListResponse()
