package co.tob.robonews.di.component

import co.tob.robonews.network.NewsRepository
import co.tob.robonews.di.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface NetworkComponent {

    fun inject(newsRepository: NewsRepository)

}