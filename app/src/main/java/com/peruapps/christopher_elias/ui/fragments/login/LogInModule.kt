package com.peruapps.christopher_elias.ui.fragments.login

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
internal abstract class LogInModule{

    @ContributesAndroidInjector
    internal abstract fun logInFragment() : LogIn

    @Binds
    @IntoMap
    @ViewModelKey(LogInViewModel::class)
    abstract fun bindLogInViewModel(viewModel: LogInViewModel) : ViewModel

}