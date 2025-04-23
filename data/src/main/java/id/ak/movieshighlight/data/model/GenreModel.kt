package id.ak.movieshighlight.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenreModel(
    @SerialName("name")
    val name: String? = null,

    @SerialName("id")
    val id: Int? = null
)