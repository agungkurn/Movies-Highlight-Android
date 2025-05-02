package id.ak.movieshighlight.domain.usecase.list

import androidx.paging.PagingData
import id.ak.movieshighlight.domain.entity.MovieListItem
import id.ak.movieshighlight.domain.repository.FilmRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviePagingData @Inject constructor(private val filmRepository: FilmRepository) {
    operator fun invoke(): Flow<PagingData<MovieListItem>> {
        return filmRepository.getMoviePagingData()
    }
}