package com.peruapps.christopher_elias.extensions

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by Christopher Elias on 30-may-2019
 * christopher.mike.96@gmail.com

 * Lima, Perú
 * Copyright (c) 2019 All rights reserved.
 */


operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
    add(disposable)
}