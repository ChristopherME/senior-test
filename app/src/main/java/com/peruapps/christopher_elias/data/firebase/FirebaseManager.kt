package com.peruapps.christopher_elias.data.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

/**
 * Created by Christopher Elias on 29-may-2019
 * christopher.mike.96@gmail.com

 * Lima, Perú
 * Copyright (c) 2019 All rights reserved.
 */

class FirebaseManager @Inject constructor() : IFirebaseManager {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val firebaseFireStore = FirebaseFirestore.getInstance()

    override fun getFireBaseAuth(): FirebaseAuth {
        return firebaseAuth
    }

    override fun getFireBaseCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    override fun getFireBaseFireStore(): FirebaseFirestore {
        return firebaseFireStore
    }

    override fun clientLogOut() {
        firebaseAuth.signOut()
    }
}