package com.peruapps.christopher_elias.room.data

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable

/**
 * Created by Christopher Elias on 30-may-2019
 * christopher.mike.96@gmail.com

 * Lima, Perú
 * Copyright (c) 2019 All rights reserved.
 */

/**
 * Data Access Object for the places table.
 */
@Dao
interface PlaceDao {

    /**
     * Insert a place in the database. If the place already exists, replace it.
     * @param place the place to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlace(place: PlaceRoom): Completable

    /**
     * Get all places saved.
     * @return the list of places saved in the service.
     */
    @Query("SELECT * FROM places")
    fun getPlaces() : Observable<List<PlaceRoom>>

    /**
     * Delete all places.
     */
    @Query("DELETE FROM places")
    fun deleteAllPlaces()

    /**
     * Delete a place
     */
    @Delete
    fun deletePlace(place: PlaceRoom)
}