package com.peruapps.christopher_elias.ui.fragments.home

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.peruapps.christopher_elias.BR
import com.peruapps.christopher_elias.R
import com.peruapps.christopher_elias.databinding.FragmentHomeBinding
import com.peruapps.christopher_elias.entity.Place
import com.peruapps.christopher_elias.services.LocationService
import com.peruapps.christopher_elias.ui.base.BaseFragment
import com.peruapps.christopher_elias.ui.compound.LocationPermissionsDialog
import com.peruapps.christopher_elias.ui.fragments.profile.Profile
import java.util.ArrayList

/**
 * Created by Christopher Elias on 29-may-2019
 * christopher.mike.96@gmail.com

 * Lima, Perú
 * Copyright (c) 2019 All rights reserved.
 */

class Home : BaseFragment<FragmentHomeBinding, HomeViewModel>(), HomeNavigator {

    private lateinit var viewModel: HomeViewModel

    //region Base
    override fun getLayoutId() = R.layout.fragment_home

    override fun getBindingVariable() = BR.homeViewModel

    override fun getViewModel(): HomeViewModel {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
        return viewModel
    }
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)
        subscribeToLiveData()
        showPermissionsDialog()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults.isNotEmpty()){
            if (grantResults.any { it == PackageManager.PERMISSION_GRANTED })
            {
                activity?.startService(Intent(activity, LocationService::class.java))
            }
        }
    }

    // Handle observe fragment lifecycle and handle orientation changes
    private fun subscribeToLiveData() {
        viewModel.places.observe(this, Observer<ArrayList<Place>>{
            viewModel.addPlacesToList(it)
        })
    }

    private fun showPermissionsDialog() {
        //TODO("Show this only at the first time")
        LocationPermissionsDialog(context!!){ okPressed, dialogView ->
            if (okPressed){
                requestLocationPermission()
            }
            dialogView.dismiss()
        }.show()
    }

    // We need the user granted the permissions and send his/her location in the background.
    private fun requestLocationPermission() {
        if (checkLocationPermissionsGranted()) {
            activity?.startService(Intent(activity, LocationService::class.java))
        }
    }

    //region Navigator
    override fun showProfileView() {
        replaceFragment(Profile(), R.id.containerFrameLayout, true)
    }

    override fun showMessage(message: String?) {
        showToast(message)
    }

    override fun showMessageFromResource(stringId: Int) {
        showToast(stringId)
    }
    //endregion
}