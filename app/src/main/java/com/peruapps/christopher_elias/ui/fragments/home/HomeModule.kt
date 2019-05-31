package com.peruapps.christopher_elias.ui.fragments.home

import androidx.lifecycle.ViewModel
import com.peruapps.christopher_elias.di.ViewModelKey
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
internal abstract class HomeModule{

    @ContributesAndroidInjector
    internal abstract fun homeFragment() : Home

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel) : ViewModel

}