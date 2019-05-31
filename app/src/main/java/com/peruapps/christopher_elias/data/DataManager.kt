package com.peruapps.christopher_elias.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.peruapps.christopher_elias.data.firebase.FirebaseManager
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Christopher Elias on 29-may-2019
 * christopher.mike.96@gmail.com

 * Lima, Perú
 * Copyright (c) 2019 All rights reserved.
 */

@Singleton
class DataManager @Inject constructor(private val firebaseManager: FirebaseManager) : IDataManager{

    override fun getFireBaseAuth(): FirebaseAuth {
        return firebaseManager.getFireBaseAuth()
    }

    override fun getFireBaseFireStore(): FirebaseFirestore {
        return firebaseManager.getFireBaseFireStore()
    }

    override fun getFireBaseCurrentUser(): FirebaseUser? {
        return firebaseManager.getFireBaseCurrentUser()
    }

    override fun clientLogOut() {
        firebaseManager.clientLogOut()
    }
}