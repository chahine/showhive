package com.chahinem.showhive.auth

import com.chahinem.showhive.di.ShowHiveComponent
import com.chahinem.showhive.qualifiers.PerActivity
import dagger.Component
import dagger.android.AndroidInjectionModule

@PerActivity
@Component(
    modules = [ActivityModule::class, AndroidInjectionModule::class],
    dependencies = [ShowHiveComponent::class]
)
interface ActivityComponent {

}
