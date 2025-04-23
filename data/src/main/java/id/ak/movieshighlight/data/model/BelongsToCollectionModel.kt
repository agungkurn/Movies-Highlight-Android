package id.ak.movieshighlight.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BelongsToCollectionModel(
    @SerialName("id")
    val id: Int? = null,

    @SerialName("name")
    val name: String? = null,
)
