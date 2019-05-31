package com.peruapps.christopher_elias.ui.base

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Christopher Elias on 29-may-2019
 * christopher.mike.96@gmail.com

 * Lima, Perú
 * Copyright (c) 2019 All rights reserved.
 */

abstract class BaseViewModel <T> : ViewModel() {

    private var navigator: T? = null

    // Shows or hide progress loading bar if the have it.
    val isLoading = ObservableField<Boolean>(true)

    // Shows or hide and empty view layout if the view have it
    val showEmptyView = ObservableField<Boolean>(false)

    // Shows, hide, init or stop refreshing of Swipe refresh layout if the view have it.
    val isRefreshing = ObservableField<Boolean>(false)

    // region RxJava2
    val compositeDisposable = CompositeDisposable()

    // Clear compositeDisposable of the instance
    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
    //endregion

    fun getNavigator() : T {
        return navigator!!
    }

    fun setNavigator(navigator: T) {
        this.navigator = navigator
    }

    fun logE(message: String?){
        Log.e(this.javaClass.simpleName, message?:"null")
    }
}