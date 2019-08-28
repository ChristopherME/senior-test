package com.peruapps.christopher_elias.ui.base

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

/**
 * Created by Christopher Elias on 29-may-2019
 * christopher.mike.96@gmail.com

 * Lima, Perú
 * Copyright (c) 2019 All rights reserved.
 */

abstract class BaseViewModel <T> : ViewModel() {

    //TODO: Change this. Use Navigation Components from Android JetPack.
    //      This is just a temporal solution to fix memory leaks due to the reference to activity.
    //      The final solution will be using Navigation with multiple fragments and one activity.
    //      By making navigator weakReference the garbage collector will be able to clean the  "context"
    //      from memory after the viewmodel has been cleared..
    private var navigator: WeakReference<T>? = null

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
        navigator?.clear()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
        super.onCleared()
    }
    //endregion


    fun getNavigator() : T {
        return navigator?.get()!!
    }

    fun setNavigator(navigator: T) {
        this.navigator = WeakReference(navigator)
    }

    fun logE(message: String?){
        Log.e(this.javaClass.simpleName, message?:"null")
    }
}