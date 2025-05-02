package id.ak.movieshighlight.data.repository

import androidx.paging.testing.asSnapshot
import id.ak.movieshighlight.data.MainDispatcherRule
import id.ak.movieshighlight.data.local.WatchlistDao
import id.ak.movieshighlight.data.local.WatchlistDatabase
import id.ak.movieshighlight.data.model.response.DiscoverMovieModel
import id.ak.movieshighlight.data.model.response.DiscoverMovieResponse
import id.ak.movieshighlight.data.model.response.DiscoverTvModel
import id.ak.movieshighlight.data.model.response.DiscoverTvResponse
import id.ak.movieshighlight.data.model.response.MovieDetailsResponse
import id.ak.movieshighlight.data.model.response.TvDetailsResponse
import id.ak.movieshighlight.data.remote.FilmApi
import id.ak.movieshighlight.domain.entity.Movie
import id.ak.movieshighlight.domain.entity.TvSerial
import id.ak.movieshighlight.domain.entity.Watchlist
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class FilmRepositoryImplTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @RelaxedMockK
    lateinit var filmApi: FilmApi

    @RelaxedMockK
    lateinit var watchlistDatabase: WatchlistDatabase

    @RelaxedMockK
    lateinit var watchlistDao: WatchlistDao

    private val repository by lazy {
        FilmRepositoryImpl(filmApi, watchlistDatabase)
    }

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        every { watchlistDatabase.watchlistDao() } returns watchlistDao
    }

    @Test
    fun `getMoviePagingData should return correct data`() = runTest {
        val mockResponse = mockk<DiscoverMovieResponse>(relaxed = true) {
            every { results } returns List(3) { DiscoverMovieModel(id = it + 1) }
            every { totalPages } returns 1
        }
        coEvery { filmApi.getDiscoverMovie(any()) } returns mockResponse

        val actual = repository.getMoviePagingData().asSnapshot()

        assert(actual.isNotEmpty())
        assertEquals(mockResponse.results?.size, actual.size)
        assertEquals(mockResponse.results?.firstOrNull()?.id, actual.firstOrNull()?.id)
    }

    @Test
    fun `getMoviePagingData should return empty list when data is empty`() = runTest {
        val mockResponse = mockk<DiscoverMovieResponse>(relaxed = true) {
            every { results } returns listOf()
            every { totalPages } returns 1
        }
        coEvery { filmApi.getDiscoverMovie(any()) } returns mockResponse

        val actual = repository.getMoviePagingData().asSnapshot()

        assert(actual.isEmpty())
    }

    @Test
    fun `getTvPagingData should return a valid PagingData Flow`() = runTest {
        val mockResponse = mockk<DiscoverTvResponse>(relaxed = true) {
            every { results } returns List(3) { DiscoverTvModel(id = it + 1) }
            every { totalPages } returns 1
        }
        coEvery { filmApi.getDiscoverTv(any()) } returns mockResponse

        val actual = repository.getTvPagingData().asSnapshot()

        assert(actual.isNotEmpty())
        assertEquals(mockResponse.results?.size, actual.size)
        assertEquals(mockResponse.results?.firstOrNull()?.id, actual.firstOrNull()?.id)
    }

    @Test
    fun `getTvPagingData should return empty list when data is empty`() = runTest {
        val mockResponse = mockk<DiscoverTvResponse>(relaxed = true) {
            every { results } returns listOf()
            every { totalPages } returns 1
        }
        coEvery { filmApi.getDiscoverTv(any()) } returns mockResponse

        val actual = repository.getTvPagingData().asSnapshot()

        assert(actual.isEmpty())
    }

    @Test
    fun `getMovieDetails should return mapped domain object`() = runTest {
        val movieId = 1
        val response = mockk<MovieDetailsResponse>()
        val expectedDomain = mockk<Movie>()

        coEvery { filmApi.getMovieDetails(any()) } returns response
        every { response.toDomain() } returns expectedDomain

        val result = repository.getMovieDetails(movieId)

        assertEquals(expectedDomain, result)
        coVerify { filmApi.getMovieDetails(movieId) }
    }

    @Test(expected = Exception::class)
    fun `getMovieDetails throws error when failed fetch API`() = runTest {
        val movieId = 1

        coEvery { filmApi.getMovieDetails(any()) } throws Exception()

        repository.getMovieDetails(movieId)
    }

    @Test
    fun `getTvSerialDetails should return mapped domain object`() = runTest {
        val tvId = 1
        val response = mockk<TvDetailsResponse>()
        val expectedDomain = mockk<TvSerial>()

        coEvery { filmApi.getTvDetails(tvId) } returns response
        every { response.toDomain() } returns expectedDomain

        val result = repository.getTvSerialDetails(tvId)

        assertEquals(expectedDomain, result)
        coVerify { filmApi.getTvDetails(tvId) }
    }

    @Test(expected = Exception::class)
    fun `getTvSerialDetails throws error when failed fetch API`() = runTest {
        val tvId = 1

        coEvery { filmApi.getTvDetails(tvId) } throws Exception()

        repository.getTvSerialDetails(tvId)
    }

    @Test
    fun `addToWatchlist should call correct dao method based on type`() = runTest {
        val movieWatchlist = Watchlist(1, "", "", Watchlist.Type.Movie)
        val tvWatchlist = Watchlist(2, "", "", Watchlist.Type.TvSerial)

        repository.addToWatchlist(movieWatchlist)
        repository.addToWatchlist(tvWatchlist)

        coVerify {
            watchlistDao.insertWatchlistMovie(any())
            watchlistDao.insertWatchlistTvSeries(any())
        }
    }

    @Test
    fun `removeFromWatchlist should call correct dao method based on type`() = runTest {
        val id = 123

        repository.removeFromWatchlist(id, Watchlist.Type.Movie)
        repository.removeFromWatchlist(id, Watchlist.Type.TvSerial)

        coVerify {
            watchlistDao.deleteMovie(id)
            watchlistDao.deleteTvSerial(id)
        }
    }

    @Test
    fun `isMovieInWatchlist should return expected value`() = runTest {
        val id = 42
        val expected = true

        every { watchlistDao.isMovieExists(id) } returns flowOf(expected)

        val result = repository.isMovieInWatchlist(id).first()

        assertEquals(expected, result)
    }

    @Test
    fun `isTvInWatchlist should return expected value`() = runTest {
        val id = 100
        val expected = false

        every { watchlistDao.isTvSeriesExists(id) } returns flowOf(expected)

        val result = repository.isTvInWatchlist(id).first()

        assertEquals(expected, result)
    }
}
