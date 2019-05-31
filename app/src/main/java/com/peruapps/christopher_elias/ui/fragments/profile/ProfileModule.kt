package com.peruapps.christopher_elias.ui.fragments.profile

import androidx.lifecycle.ViewModel
import com.peruapps.christopher_elias.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Created by Christopher Elias on 30-may-2019
 * christopher.mike.96@gmail.com

 * Lima, Perú
 * Copyright (c) 2019 All rights reserved.
 */

@Module
internal abstract class ProfileModule{

    @ContributesAndroidInjector
    internal abstract fun profileFragment() : Profile

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(viewModel: ProfileViewModel) : ViewModel

}