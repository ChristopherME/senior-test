package com.peruapps.christopher_elias.services

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.location.Location
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.peruapps.christopher_elias.R
import com.peruapps.christopher_elias.extensions.plusAssign
import com.peruapps.christopher_elias.extensions.toDate
import com.peruapps.christopher_elias.room.data.PlaceRoom
import com.peruapps.christopher_elias.room.repository.PlaceDataSource
import com.peruapps.christopher_elias.ui.activities.main.Main
import com.peruapps.christopher_elias.utils.VersionUtils
import com.peruapps.christopher_elias.utils.gps.Gps
import com.peruapps.christopher_elias.utils.gps.IOnLocationChanged
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Christopher Elias on 30-may-2019
 * christopher.mike.96@gmail.com

 * Lima, Perú
 * Copyright (c) 2019 All rights reserved.
 */

class LocationService : BaseService(), IOnLocationChanged {

    private var gps : Gps? = null
    private var savePlaceTask: Runnable? = null
    private var threadPool: ScheduledExecutorService? = null

    @Inject
    lateinit var placeDataSource: PlaceDataSource

    override fun onCreate() {
        super.onCreate()
        savePlaceTask = Runnable {
            savePlace()
        }
        // Create a thread pool to execute "savePlace" periodically
        threadPool = Executors.newScheduledThreadPool(CORE_POOL_SIZE)
        threadPool?.scheduleAtFixedRate(savePlaceTask, 10, 10, TimeUnit.SECONDS)
    }

    // [START_STICKY] Keeps service alive
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotification()
        return START_STICKY
    }

    private fun savePlace() {
        gps = Gps(this, this)
        gps?.requestLocationUpdates()
    }

    //region GPS

    @SuppressLint("CheckResult")
    override fun LocationChanged(location: Location) {

        placeDataSource.insertPlace(PlaceRoom(
            latitude = location.latitude,
            longitude = location.longitude,
            createdAt = System.currentTimeMillis().toDate())
        ).subscribe({
            logError("something")
        }, {
            err->
            logError(err.message)
        })

        logError("Saving place")
    }

    override fun LocationNeedPermissions() {
        threadPool?.shutdown()
    }
    //endregion

    override fun onBind(intent: Intent?): IBinder? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDestroy() {
        logError("Service killed")
        threadPool?.shutdown()
        stopForeground(true)
        super.onDestroy()
    }
}