package com.peruapps.christopher_elias.entity

/**
 * Created by Christopher Elias on 29-may-2019
 * christopher.mike.96@gmail.com

 * Lima, Perú
 * Copyright (c) 2019 All rights reserved.
 */

data class Place (val description: String = "",
                  val image: String = "",
                  val lat: Double = 0.0,
                  val long: Double = 0.0,
                  val createdAt: Long = 0L,
                  var extra: Extra? = null,
                  var author: User? = null) {

    class Extra(firebaseId: Int)
}