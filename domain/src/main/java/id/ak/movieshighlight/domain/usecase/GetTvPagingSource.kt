package id.ak.movieshighlight.domain.usecase

import androidx.paging.PagingSource
import id.ak.movieshighlight.domain.entity.TvSerialListItem
import id.ak.movieshighlight.domain.repository.FilmRepository
import javax.inject.Inject

class GetTvPagingSource @Inject constructor(private val filmRepository: FilmRepository) {
    val pageSize = 20

    operator fun invoke(): PagingSource<Int, TvSerialListItem> {
        return filmRepository.getTvPagingSource()
    }
}