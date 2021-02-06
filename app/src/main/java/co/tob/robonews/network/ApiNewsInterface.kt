package co.tob.robonews.network

import co.tob.robonews.model.News
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiNewsInterface {

    companion object {
        private const val API_KEY: String = "a56f0524f79a485d9cf9d1a9a0fc13ea"
    }

    @GET("top-headlines")
    fun trendingNews(
        @Query("country")
        countryCode: String = "us",
        @Query("page")
        pageNum: Int,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Single<News>

    @GET("/v2/everything")
    suspend fun searchNews(
        @Query("q")
        query: String = "bitcoin",
        @Query("page")
        pageNum: Int,
        @Query("apiKey")
        api_key: String = API_KEY
    ): Single<News>

}