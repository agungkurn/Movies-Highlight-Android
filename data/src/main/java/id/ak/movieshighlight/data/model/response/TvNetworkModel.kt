package id.ak.movieshighlight.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TvNetworkModel(
    @SerialName("logo_path")
    val logoPath: String? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("id")
    val id: Int? = null,

    @SerialName("origin_country")
    val originCountry: String? = null
)