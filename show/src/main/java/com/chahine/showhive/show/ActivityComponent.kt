package com.chahine.showhive.show

import android.app.Activity
import com.chahine.showhive.di.ShowHiveComponent
import com.chahine.showhive.qualifiers.PerActivity
import dagger.BindsInstance
import dagger.Component

//@PerActivity
//@Component(
//    modules = [ActivityModule::class],
//    dependencies = [ShowHiveComponent::class]
//)
interface ActivityComponent {

    fun inject(activity: ShowActivity)

//    @Component.Builder
//    interface Builder {
//        @BindsInstance
//        fun activity(activity: Activity): Builder
//        fun appComponent(appComponent: ShowHiveComponent): Builder
//        fun activityModule(module: ActivityModule): Builder
//        fun build(): ActivityComponent
//    }
}
