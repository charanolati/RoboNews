package co.tob.robonews.di.component


import co.tob.robonews.di.ActivityScope
import co.tob.robonews.di.module.ActivityModule
import co.tob.robonews.ui.main.MainActivity

import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: MainActivity)
}
