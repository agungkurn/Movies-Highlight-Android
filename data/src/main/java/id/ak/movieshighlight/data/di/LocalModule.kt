package id.ak.movieshighlight.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.ak.movieshighlight.data.local.WatchlistDatabase
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {
    @Provides
    @Singleton
    fun provideWatchlistDatabase(@ApplicationContext context: Context): WatchlistDatabase {
        val passphrase = SQLiteDatabase.getBytes("watchlist".toCharArray())
        val factory = SupportFactory(passphrase)

        return Room.databaseBuilder(
            context = context,
            klass = WatchlistDatabase::class.java,
            name = WatchlistDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration(true)
            .openHelperFactory(factory)
            .build()
    }
}