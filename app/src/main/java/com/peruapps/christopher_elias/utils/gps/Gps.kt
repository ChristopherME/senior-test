package com.peruapps.christopher_elias.utils.gps

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.os.Build
import android.os.Bundle
import android.util.Log
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.common.api.ResultCallback
import com.google.android.gms.location.*
import com.peruapps.christopher_elias.utils.VersionUtils

/**
 * Created by Christopher Elias on 30-may-2019
 * christopher.mike.96@gmail.com

 * Lima, Perú
 * Copyright (c) 2019 All rights reserved.
 */

class Gps (private val context: Context,
           private val listener: IOnLocationChanged)
    : GoogleApiClient.ConnectionCallbacks,
    GoogleApiClient.OnConnectionFailedListener,
    LocationListener, ResultCallback<LocationSettingsResult> {

    private var googleApiClient: GoogleApiClient? = null
    private var locationRequest: LocationRequest? = null
    private var locationSettingsRequest: LocationSettingsRequest? = null
    private var fusedLocationProviderClient: FusedLocationProviderClient? = null
    private var locationCallback : LocationCallback? = null

    private var shouldStopAfterFetch : Boolean = false

    init {
        buildGoogleApiClient()
        createLocationRequest()
        buildLocationSettingsRequest()
        checkLocationSettings()
    }

    private fun buildGoogleApiClient() {
        googleApiClient = GoogleApiClient.Builder(context)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .addApi(LocationServices.API)
            .build()

        val googleAPI = GoogleApiAvailability.getInstance()
        val resultCode: Int = googleAPI.isGooglePlayServicesAvailable(context)
        if (resultCode == ConnectionResult.SUCCESS) {
            googleApiClient?.connect()
        } else {
            Log.e("GPS", "Error fetching google play services.")
        }

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

        // Create location callback
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult?) {
                super.onLocationResult(result)

                result?.let { resultNotNull ->
                    listener.LocationChanged(resultNotNull.lastLocation)
                }

                if (shouldStopAfterFetch) {
                    stopRefreshing()
                }
            }
            override fun onLocationAvailability(availability: LocationAvailability?) {/* Ignore */ }
        }
    }

    private fun createLocationRequest() {
        locationRequest = LocationRequest()

        // Sets the desired interval for active location updates. This interval is
        // inexact. You may not receive updates at all if no location sources are available, or
        // you may receive them slower than requested. You may also receive updates faster than
        // requested if other applications are requesting location at a faster interval.
        locationRequest?.interval = 10000

        // Sets the fastest rate for active location updates. This interval is exact, and your
        // application will never receive updates faster than this value.
        locationRequest?.fastestInterval = 5000
        locationRequest?.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        // Verify the permissions before start fetching the location.
    }

    private fun buildLocationSettingsRequest() {

        val builder = LocationSettingsRequest.Builder()
        builder.addLocationRequest(locationRequest!!)
        builder.setAlwaysShow(true)

        locationSettingsRequest = builder.build()

        /*locationSettingsRequest = LocationSettingsRequest.Builder()
            .apply { addLocationRequest(locationRequest!!) }
            .build()*/
    }

    private fun checkLocationSettings() {
        val result = LocationServices.getSettingsClient(context).checkLocationSettings(locationSettingsRequest)
        result.addOnCompleteListener { task ->
            try {
                val response = task.getResult(ApiException::class.java)

                // All locations settings are satisfied. The client can initialize location
                // request here...
                Log.e("GPS", "Checking location settings (if gps is available)")
                requestLocationUpdates()
            } catch (exception: ApiException) {
                when(exception.statusCode) {
                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> {
                        // Location settings are not satisfied. But could be fixed by showing the user a dialog
                        try {
                            // Cast to a resolvable exception
                            val resolvable = exception as ResolvableApiException

                            // Show the dialog by calling startResolutionForResult
                            // and check the result in activity result
                            resolvable.startResolutionForResult(context as Activity, 2)
                            // resolvable.startResolutionForResult(context as Activity, 100)
                        }
                        catch (e: IntentSender.SendIntentException) { /* Ignore */ }
                        catch (e: ClassCastException) { /* Ignore */ }
                    }
                    LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {
                        // Locations settings are not satisfied. However, we have no way to fix the
                        // settings so we won't show the dialog
                    }
                }
            }
        }
    }

    private fun hasPermissions() : Boolean {
        if (VersionUtils.isMarshmallow())
        {
            if (context.checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) return false
        }

        return true
    }

    @SuppressLint("MissingPermission")
    fun requestLocationUpdates() {
        if (hasPermissions())
        {
            fusedLocationProviderClient?.requestLocationUpdates(locationRequest, locationCallback, null)
        }
    }

    private fun stopRefreshing() {
        fusedLocationProviderClient?.removeLocationUpdates(locationCallback)
    }

    //region LocationListener
    override fun onLocationChanged(location: Location) {
        listener.LocationChanged(location)
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) { /* Ignore */ }

    override fun onProviderEnabled(provider: String?) { /* Ignore */ }

    override fun onResult(p0: LocationSettingsResult) { /* Ignore */ }

    override fun onProviderDisabled(provider: String?) { /* Ignore */ }
    //endregion

    //region GoogleApiClient (I G N O R E this)
    override fun onConnected(p0: Bundle?) { /* Ignore */ }

    override fun onConnectionSuspended(p0: Int) { /* Ignore */ }

    override fun onConnectionFailed(p0: ConnectionResult) { /* Ignore */ }
    //endregion


}