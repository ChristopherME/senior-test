package com.peruapps.christopher_elias.room.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * Created by Christopher Elias on 30-may-2019
 * christopher.mike.96@gmail.com

 * Lima, Perú
 * Copyright (c) 2019 All rights reserved.
 */
@Entity(tableName = "places")
data class PlaceRoom(@PrimaryKey(autoGenerate = true)
                     @ColumnInfo(name = "place_id")
                     val id: Int = 0,
                     @ColumnInfo(name = "place_latitude")
                     val latitude: Double,
                     @ColumnInfo(name = "place_longitude")
                     val longitude: Double,
                     @ColumnInfo(name = "place_created_at")
                     val createdAt: String)