package id.ak.movieshighlight.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.ak.movieshighlight.data.repository.FilmRepositoryImpl
import id.ak.movieshighlight.domain.repository.FilmRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindRepository(filmRepositoryImpl: FilmRepositoryImpl): FilmRepository
}