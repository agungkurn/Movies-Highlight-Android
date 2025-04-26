package id.ak.movieshighlight.domain.usecase.list

import androidx.paging.PagingData
import id.ak.movieshighlight.domain.entity.TvSerialListItem
import id.ak.movieshighlight.domain.repository.FilmRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTvPagingData @Inject constructor(private val filmRepository: FilmRepository) {
    val pageSize: Int
        get() = FilmRepository.PAGE_SIZE

    operator fun invoke(): Flow<PagingData<TvSerialListItem>> {
        return filmRepository.getTvPagingData()
    }
}