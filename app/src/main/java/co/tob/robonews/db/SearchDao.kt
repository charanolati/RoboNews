package co.tob.robonews.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import co.tob.robonews.model.SearchTerm
import io.reactivex.Single

@Dao
interface SearchDao {

    //    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Query("INSERT INTO searchterm ('query') VALUES (:query)")
    fun insertSearchQuery(query: String) : Single<Long>

    @Query("SELECT * FROM searchterm ")
    fun getSearchQueries(): LiveData<List<SearchTerm>>

}