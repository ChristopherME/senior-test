package com.peruapps.christopher_elias.di

import com.peruapps.christopher_elias.SeniorTest
import com.peruapps.christopher_elias.services.ServiceBuilderModule
import com.peruapps.christopher_elias.ui.activities.main.MainModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by Christopher Elias on 29-may-2019
 * christopher.mike.96@gmail.com

 * Lima, Perú
 * Copyright (c) 2019 All rights reserved.
 */


@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ViewModelBuilder::class,
        AppModule::class,
        MainModule::class,
        ServiceBuilderModule::class
    ])

interface AppComponent : AndroidInjector<SeniorTest> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<SeniorTest>()
}