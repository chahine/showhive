package com.chahine.showhive.show

import android.app.Activity
import com.chahine.showhive.di.ShowHiveComponent
import com.chahine.showhive.qualifiers.ActivityScope
import dagger.BindsInstance
import dagger.Component

//@ActivityScope
//@Component(
//    modules = [ActivityModule::class],
//    dependencies = [ShowHiveComponent::class]
//)
interface ActivityComponentLegacy {

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
