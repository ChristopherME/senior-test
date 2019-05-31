package com.peruapps.christopher_elias.extensions

import androidx.databinding.ObservableField

/**
 * Created by Christopher Elias on 29-may-2019
 * christopher.mike.96@gmail.com

 * Lima, Perú
 * Copyright (c) 2019 All rights reserved.
 */


fun ObservableField<String>.value() = this.get()?:""