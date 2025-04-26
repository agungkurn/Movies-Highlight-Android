package id.ak.movieshighlight.watchlist

import dagger.Component
import id.ak.movieshighlight.WatchlistDependencies

@Component(dependencies = [WatchlistDependencies::class])
interface WatchlistComponent {
    fun inject(activity: WatchlistActivity)

    @Component.Builder
    interface Builder {
        fun dependencies(watchlistDependencies: WatchlistDependencies): Builder
        fun build(): WatchlistComponent
    }
}