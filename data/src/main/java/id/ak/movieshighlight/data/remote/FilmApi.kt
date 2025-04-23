package id.ak.movieshighlight.data.remote

import id.ak.movieshighlight.data.model.DiscoverMovieResponse
import id.ak.movieshighlight.data.model.DiscoverTvResponse
import id.ak.movieshighlight.data.model.MovieDetailsResponse
import id.ak.movieshighlight.data.model.TvDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmApi {
    @GET("discover/movie")
    suspend fun getDiscoverMovie(
        @Query("page") page: Int,
        @Query("watch_region") region: String = "id"
    ): DiscoverMovieResponse

    @GET("discover/tv")
    suspend fun getDiscoverTv(
        @Query("page") page: Int,
        @Query("watch_region") region: String = "id"
    ): DiscoverTvResponse

    @GET("movie/{id}")
    suspend fun getMovieDetails(
        @Path("id") id: Int
    ): MovieDetailsResponse

    @GET("tv/{id}")
    suspend fun getTvDetails(
        @Path("id") id: Int
    ): TvDetailsResponse
}