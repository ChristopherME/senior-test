package com.peruapps.christopher_elias.di

import android.content.Context
import com.peruapps.christopher_elias.SeniorTest
import dagger.Module
import dagger.Provides

/**
 * Created by Christopher Elias on 30-may-2019
 * christopher.mike.96@gmail.com

 * Lima, Perú
 * Copyright (c) 2019 All rights reserved.
 */

@Module
class RoomModule {

    @Provides
    fun providesContext(application: SeniorTest): Context {
        return application.applicationContext
    }

}