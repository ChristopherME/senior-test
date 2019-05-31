package com.peruapps.christopher_elias.room.repository

import com.peruapps.christopher_elias.room.data.PlaceDao
import com.peruapps.christopher_elias.room.data.PlaceRoom
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Christopher Elias on 30-may-2019
 * christopher.mike.96@gmail.com

 * Lima, Perú
 * Copyright (c) 2019 All rights reserved.
 */

class PlaceDataSource @Inject constructor(private val placeDao: PlaceDao) : PlaceRepository{

    override fun insertPlace(place: PlaceRoom) : Completable {
        return placeDao.insertPlace(place)
    }

    override fun getPlaces(): Observable<List<PlaceRoom>> {
        return placeDao.getPlaces()
    }

    override fun deletePlace(place: PlaceRoom) {
        placeDao.deletePlace(place)
    }

    override fun deleteAllPlaces() {
        placeDao.deleteAllPlaces()
    }
}