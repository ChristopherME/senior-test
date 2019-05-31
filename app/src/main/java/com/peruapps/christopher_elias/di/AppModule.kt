package com.peruapps.christopher_elias.di

import android.content.Context
import androidx.room.Room
import com.peruapps.christopher_elias.SeniorTest
import com.peruapps.christopher_elias.data.DataManager
import com.peruapps.christopher_elias.data.IDataManager
import com.peruapps.christopher_elias.room.repository.PlaceDataBase
import com.peruapps.christopher_elias.room.data.PlaceDao
import com.peruapps.christopher_elias.room.repository.PlaceDataSource
import com.peruapps.christopher_elias.room.repository.PlaceRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Christopher Elias on 29-may-2019
 * christopher.mike.96@gmail.com

 * Lima, Perú
 * Copyright (c) 2019 All rights reserved.
 */

@Module
class AppModule {

    @Provides
    fun providesContext(application: SeniorTest): Context {
        return application.applicationContext
    }

    @Provides
    fun provideDataManager(dataManager: DataManager) : IDataManager {
        return dataManager
    }

    @Provides
    fun providePlaceDB(application: SeniorTest) : PlaceDataBase {
        return Room.databaseBuilder(application, PlaceDataBase::class.java, "Sample.db")
            .allowMainThreadQueries()
            .build()
    }

    @Singleton
    @Provides
    fun providePlaceDao(placeDataBase: PlaceDataBase): PlaceDao{
        return placeDataBase.placeDao()
    }

    @Singleton
    @Provides
    fun providePlaceRepository(placeDao: PlaceDao): PlaceRepository{
        return PlaceDataSource(placeDao)
    }

}