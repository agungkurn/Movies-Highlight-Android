package id.ak.movieshighlight.domain.entity

data class Movie(
    val id: Int,
    val title: String,
    val originalLanguage: String,
    val budget: Int,
    val revenue: Int,
    val genres: List<String>,
    val overview: String,
    val runtime: Int,
    val posterUrl: String,
    val originCountry: List<String>,
    val productionCompanies: List<String>,
    val productionCountries: List<String>,
    val releaseDate: String,
    val voteAverage: Double,
    val voteCount: Int,
    val belongsToCollection: String?,
    val tagline: String,
    val adult: Boolean,
    val homepage: String,
    val status: String
)
