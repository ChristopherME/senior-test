package com.peruapps.christopher_elias.room.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.peruapps.christopher_elias.room.data.PlaceDao
import com.peruapps.christopher_elias.room.data.PlaceRoom

/**
 * Created by Christopher Elias on 30-may-2019
 * christopher.mike.96@gmail.com

 * Lima, Perú
 * Copyright (c) 2019 All rights reserved.
 */

/**
 * The Room database that contains the Places table
 */
@Database(entities = [PlaceRoom::class], version = 1)
abstract class PlaceDataBase : RoomDatabase(){

    abstract fun placeDao() : PlaceDao

    /*companion object {

        @Volatile private var placeDB: PlaceDataBase? = null

        fun getInstance(context: Context): PlaceDataBase =
            placeDB ?: synchronized(this) {
                placeDB
                    ?: buildDatabase(context).also { placeDB = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                PlaceDataBase::class.java, "Sample.db")
                .build()
    }*/
}