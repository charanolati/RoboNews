package co.tob.robonews.network

import co.tob.robonews.model.Article
import co.tob.robonews.di.component.DaggerNetworkComponent
import co.tob.robonews.di.module.NetworkModule
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

class NewsRepository {

    @Inject
    lateinit var networkModule: NetworkModule

    @Inject
    lateinit var retrofit: Retrofit

    init {
        DaggerNetworkComponent.builder().build().inject(this@NewsRepository)
    }

    fun getNews(): Single<List<Article>> {
        return networkModule.provideRetrofitService(retrofit)
            .trendingNews("in", 1)
            .subscribeOn(Schedulers.io())
            .map { Article.from(it) }
    }

    fun searchNews(): Single<List<Article>> {
        return networkModule.provideRetrofitService(retrofit)
            .trendingNews("in", 1)
            .subscribeOn(Schedulers.io())
            .map { Article.from(it) }
    }
}

