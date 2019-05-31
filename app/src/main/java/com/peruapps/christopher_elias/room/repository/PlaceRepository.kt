package com.peruapps.christopher_elias.room.repository

import com.peruapps.christopher_elias.room.data.PlaceRoom
import io.reactivex.Completable
import io.reactivex.Observable

/**
 * Created by Christopher Elias on 30-may-2019
 * christopher.mike.96@gmail.com

 * Lima, Perú
 * Copyright (c) 2019 All rights reserved.
 */

interface PlaceRepository {

    fun insertPlace(place: PlaceRoom) : Completable

    fun getPlaces() : Observable<List<PlaceRoom>>

    fun deleteAllPlaces()

    fun deletePlace(place: PlaceRoom)
}