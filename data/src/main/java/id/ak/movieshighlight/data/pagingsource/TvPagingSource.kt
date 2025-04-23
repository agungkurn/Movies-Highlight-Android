package id.ak.movieshighlight.data.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import id.ak.movieshighlight.data.remote.FilmApi
import id.ak.movieshighlight.domain.entity.Movie
import id.ak.movieshighlight.domain.entity.TvSerial

class TvPagingSource(private val api: FilmApi) : PagingSource<Int, TvSerial>() {
    override fun getRefreshKey(state: PagingState<Int, TvSerial>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TvSerial> {
        val page = params.key ?: 1

        return try {
            val response = api.getDiscoverTv(page)
            val list = response.results?.map { it.toDomain() }.orEmpty()

            val prev = if (page > 1) page - 1 else null
            val next = if (page < (response.totalPages ?: 0)) page + 1 else null

            LoadResult.Page(list, prev, next)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}