package id.ak.movieshighlight.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpokenLanguageModel(

    @SerialName("name")
    val name: String? = null,

    @SerialName("iso_639_1")
    val iso6391: String? = null,

    @SerialName("english_name")
    val englishName: String? = null
)