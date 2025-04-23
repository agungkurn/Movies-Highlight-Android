package id.ak.movieshighlight.domain.entity

data class TvSerialListItem(
    val id: Int,
    val overview: String,
    val originalLanguage: String,
    val title: String,
    val posterPath: String,
    val firstAirDate: String,
    val adult: Boolean,
    val voteAverage: Double,
    val voteCount: Int,
    val originCountry: List<String>
)