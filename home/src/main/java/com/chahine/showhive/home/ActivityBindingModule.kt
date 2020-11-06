package com.chahine.showhive.home

import androidx.lifecycle.ViewModel
import com.chahine.showhive.di.ViewModelKey
import com.chahine.showhive.home.calendar.CalendarViewModel
import com.chahine.showhive.home.discover.DiscoverViewModel
import com.chahine.showhive.home.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

//@Module
interface ActivityBindingModule {

//    @Binds
//    @IntoMap
//    @ViewModelKey(CalendarViewModel::class)
//    fun bindCalendarViewModel(vm: CalendarViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(DiscoverViewModel::class)
//    fun bindDiscoverViewModel(vm: DiscoverViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(ProfileViewModel::class)
//    fun bindProfileViewModel(vm: ProfileViewModel): ViewModel
}
