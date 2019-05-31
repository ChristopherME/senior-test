package com.peruapps.christopher_elias.ui.activities.main

import androidx.lifecycle.ViewModel
import com.peruapps.christopher_elias.di.ViewModelKey
import com.peruapps.christopher_elias.ui.fragments.home.HomeModule
import com.peruapps.christopher_elias.ui.fragments.login.LogInModule
import com.peruapps.christopher_elias.ui.fragments.profile.ProfileModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Created by Christopher Elias on 29-may-2019
 * christopher.mike.96@gmail.com

 * Lima, Perú
 * Copyright (c) 2019 All rights reserved.
 */

@Module
internal abstract class MainModule {

    @ContributesAndroidInjector(modules = [
        LogInModule::class,
        HomeModule::class,
        ProfileModule::class
    ])

    internal abstract fun mainActivity() : Main

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel) : ViewModel
}