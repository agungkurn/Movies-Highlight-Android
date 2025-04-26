package id.ak.movieshighlight.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.ak.movieshighlight.data.local.WatchlistDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {
    @Provides
    @Singleton
    fun provideWatchlistDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context = context,
            klass = WatchlistDatabase::class.java,
            name = WatchlistDatabase.DATABASE_NAME
        ).build()
}