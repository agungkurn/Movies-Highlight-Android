package id.ak.movieshighlight.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreatorModel(
    @SerialName("id")
    val id: Int? = null,

    @SerialName("credit_id")
    val creditId: String? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("gender")
    val gender: String? = null,

    @SerialName("profile_path")
    val profilePath: String? = null
)