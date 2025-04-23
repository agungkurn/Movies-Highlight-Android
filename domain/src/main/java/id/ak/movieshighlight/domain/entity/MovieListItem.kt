package id.ak.movieshighlight.domain.entity

data class MovieListItem(
    val id: Int,
    val overview: String,
    val originalLanguage: String,
    val title: String,
    val posterPath: String,
    val releaseDate: String,
    val adult: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)