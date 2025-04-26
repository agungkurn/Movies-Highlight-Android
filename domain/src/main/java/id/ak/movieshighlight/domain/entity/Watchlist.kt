package id.ak.movieshighlight.domain.entity

data class Watchlist(
    val id: Int,
    val title: String,
    val posterUrl: String,
    val type: Type
) {
    enum class Type(val displayName: String) {
        Movie("Movie"), TvSerial("TV Serial")
    }
}
