package com.peruapps.christopher_elias.ui.fragments.profile

import androidx.lifecycle.MutableLiveData
import com.peruapps.christopher_elias.R
import com.peruapps.christopher_elias.adapter.PlacesRoomAdapter
import com.peruapps.christopher_elias.data.DataManager
import com.peruapps.christopher_elias.entity.Place
import com.peruapps.christopher_elias.entity.User
import com.peruapps.christopher_elias.extensions.plusAssign
import com.peruapps.christopher_elias.room.data.PlaceRoom
import com.peruapps.christopher_elias.room.repository.PlaceDataSource
import com.peruapps.christopher_elias.ui.base.BaseViewModel
import com.peruapps.christopher_elias.ui.fragments.home.HomeViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList
import javax.inject.Inject

/**
 * Created by Christopher Elias on 30-may-2019
 * christopher.mike.96@gmail.com

 * Lima, Perú
 * Copyright (c) 2019 All rights reserved.
 */

class ProfileViewModel @Inject constructor(private val dataManager: DataManager,
                                           private val placeDataSource: PlaceDataSource) : BaseViewModel<ProfileNavigator>(){

    val adapter = PlacesRoomAdapter(arrayListOf()){
            place -> uploadPlaceToFireStore(place)
    }
    val places = MutableLiveData<ArrayList<PlaceRoom>>()

    init {
        getSavedPlaces()
    }

    fun addPlacesToList(resultPlaces: ArrayList<PlaceRoom>) {
        isLoading.set(false)
        adapter.bindNewItems(resultPlaces)
        showEmptyView.set(resultPlaces.isEmpty())
    }

    private fun getSavedPlaces() {
        isLoading.set(true)
        compositeDisposable += placeDataSource.getPlaces()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ savedPlaces ->
                places.value = ArrayList(savedPlaces)
            },{ err ->
                getNavigator().showMessage(err.message)
                isLoading.set(false)
            })
    }

    private fun uploadPlaceToFireStore(place: PlaceRoom) {

        getNavigator().showLoading(true)

        val newPlace = Place (
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.",
            image = "https://i.imgur.com/YqRUAZN.jpg",
            createdAt = System.currentTimeMillis(),
            lat = place.latitude,
            long = place.longitude,
            author = User("App User", dataManager.getFireBaseCurrentUser()?.email!!)
        )

        dataManager.getFireBaseFireStore().collection(HomeViewModel.PLACES_COLLECTION)
            .add(newPlace)
            .addOnSuccessListener {
                deleteSavedPlace(place)
                getNavigator().showMessageFromResource(R.string.upload_place_success)
                getNavigator().showLoading(false)
            }
            .addOnFailureListener { exception ->
                getNavigator().showMessage(exception.message)
                getNavigator().showLoading(false)
            }
    }

    private fun deleteSavedPlace(place: PlaceRoom) {
        placeDataSource.deletePlace(place)
    }
}