package com.peruapps.christopher_elias.services

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Christopher Elias on 30-may-2019
 * christopher.mike.96@gmail.com

 * Lima, Perú
 * Copyright (c) 2019 All rights reserved.
 */

@Module
abstract class ServiceBuilderModule {
    @ContributesAndroidInjector
    abstract fun contributeUploadService() : LocationService
}