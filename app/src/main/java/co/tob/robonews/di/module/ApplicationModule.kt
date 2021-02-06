package co.tob.robonews.di.module

import android.content.Context
import androidx.room.Room
import co.tob.robonews.MyApplication
import co.tob.robonews.db.DatabaseService
import co.tob.robonews.di.ApplicationContext
import co.tob.robonews.di.DatabaseInfo
import co.tob.robonews.di.NetworkInfo
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: MyApplication) {

    @ApplicationContext
    @Provides
    fun provideContext(): Context = application

    @Provides
    @DatabaseInfo
    fun provideDatabaseName(): String = "news_database"

    @Provides
    @DatabaseInfo
    fun provideDatabaseVersion(): Int = 1

    @Provides
    @NetworkInfo
    fun provideApiKey(): String = "SOME_API_KEY"

    @Singleton
    @Provides
    fun provideDatabaseInstance(): DatabaseService = Room.databaseBuilder(
        application, DatabaseService::class.java,
        "news"
    ).build()

    @Singleton
    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

}
