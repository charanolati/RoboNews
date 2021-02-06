package co.tob.robonews.di.component

import co.tob.robonews.MyApplication
import co.tob.robonews.db.DatabaseService
import co.tob.robonews.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: MyApplication)

    fun getDatabaseService(): DatabaseService

}
