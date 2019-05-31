package com.peruapps.christopher_elias.ui.fragments.home

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.peruapps.christopher_elias.adapter.PlacesAdapter
import com.peruapps.christopher_elias.data.DataManager
import com.peruapps.christopher_elias.entity.Place
import com.peruapps.christopher_elias.extensions.plusAssign
import com.peruapps.christopher_elias.room.repository.PlaceDataSource
import com.peruapps.christopher_elias.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList
import javax.inject.Inject

/**
 * Created by Christopher Elias on 29-may-2019
 * christopher.mike.96@gmail.com

 * Lima, Perú
 * Copyright (c) 2019 All rights reserved.
 */

class HomeViewModel @Inject constructor(private val dataManager: DataManager) : BaseViewModel<HomeNavigator>(){

    val adapter = PlacesAdapter(arrayListOf()){}
    val places = MutableLiveData<ArrayList<Place>>()
    val clientName = ObservableField<String>("")

    init {
        getClientName()
        fetchPlacesFromFireStore()
    }

    fun addPlacesToList(resultPlaces: ArrayList<Place>) {
        adapter.bindNewItems(resultPlaces)
        showEmptyView.set(resultPlaces.isEmpty())
    }

    fun refreshData(){
        isRefreshing.set(true)
        fetchPlacesFromFireStore()
    }

    private fun getClientName() {
        clientName.set(dataManager.getFireBaseCurrentUser()?.email)
    }

    // Fetch data from fire store. This is not real time implemented.
    private fun fetchPlacesFromFireStore() {
        isLoading.set(true)
        dataManager.getFireBaseFireStore().collection(PLACES_COLLECTION)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    task.result?.let { results ->
                        if (results.isEmpty) {
                            showEmptyView.set(true)
                            return@let
                        }
                        places.value = ArrayList(results.map { it.toObject(Place::class.java)})
                    }
                    isLoading.set(false)
                    isRefreshing.set(false)

                } else {
                    getNavigator().showMessage("Task unsuccessful")
                    isLoading.set(false)
                    isRefreshing.set(false)
                    showEmptyView.set(true)
                }
            }
            .addOnFailureListener { exception ->
                isLoading.set(false)
                isRefreshing.set(false)
                showEmptyView.set(true)
                getNavigator().showMessage(exception.message)
            }
    }

    companion object {
        const val PLACES_COLLECTION = "places"
    }
}