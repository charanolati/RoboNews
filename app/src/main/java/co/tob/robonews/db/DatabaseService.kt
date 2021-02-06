package co.tob.robonews.db

import androidx.room.Database
import androidx.room.RoomDatabase
import co.tob.robonews.model.Article
import co.tob.robonews.model.SearchTerm

@Database(entities = [Article::class, SearchTerm::class], exportSchema = false, version = 3)
abstract class DatabaseService : RoomDatabase() {

    abstract fun getArticleDao(): ArticleDao

    abstract fun getSearchDao(): SearchDao

}
