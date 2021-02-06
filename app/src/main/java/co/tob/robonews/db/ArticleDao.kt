package co.tob.robonews.db

import androidx.lifecycle.LiveData
import androidx.room.*
import co.tob.robonews.model.Article
import io.reactivex.Single

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewsArticle(article: Article): Single<Long>

    @Query("SELECT * FROM article")
    fun getNewsArticles(): LiveData<List<Article>>

    @Query("SELECT * FROM article WHERE bookmark =:value")
    fun getBookMarkedArticles(value: Boolean): Single<List<Article>>

    @Delete
    fun deleteNewsArticle(article: Article)

    @Query("UPDATE article SET bookmark = :isArticleBookMarked WHERE articleId =:articleId")
    fun saveOrRemoveBookmarkArticle(isArticleBookMarked: Boolean, articleId: Int): Single<Int>

    @Query("SELECT * FROM article WHERE title =:query")
    fun searchArticle(query: String): Single<List<Article>>

    @Query("SELECT count(*) from article")
    fun count(): Single<Int>

}
