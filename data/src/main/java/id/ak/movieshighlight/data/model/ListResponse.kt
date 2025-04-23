package id.ak.movieshighlight.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
open class ListResponse(
    @SerialName("page")
    val page: Int? = null,

    @SerialName("total_pages")
    val totalPages: Int? = null,

    @SerialName("total_results")
    val totalResults: Int? = null
)