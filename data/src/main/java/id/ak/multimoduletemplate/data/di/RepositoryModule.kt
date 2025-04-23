package id.ak.multimoduletemplate.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.ak.multimoduletemplate.data.repository.RepositorySample
import id.ak.multimoduletemplate.domain.repository.DomainRepositorySample
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindRepository(repositorySample: RepositorySample): DomainRepositorySample
}