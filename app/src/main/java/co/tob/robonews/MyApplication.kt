package co.tob.robonews

import android.app.Application
import co.tob.robonews.di.component.ApplicationComponent
import co.tob.robonews.di.component.DaggerApplicationComponent
import co.tob.robonews.di.module.ApplicationModule

class MyApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        getDependencies()
    }

    private fun getDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }
}