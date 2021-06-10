package com.chahine.showhive.auth

//@ActivityScope
//@Component(
//    modules = [ActivityModule::class],
//    dependencies = [ShowHiveComponent::class]
//)
interface ActivityComponent {

    fun inject(activity: SplashActivity)

//    @Component.Builder
//    interface Builder {
//        @BindsInstance
//        fun activity(activity: Activity): Builder
//        fun activityModule(module: ActivityModule): Builder
//        fun appComponent(appComponent: ShowHiveComponent): Builder
//        fun build(): ActivityComponent
//    }
}
