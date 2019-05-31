package com.peruapps.christopher_elias.data.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

/**
 * Created by Christopher Elias on 29-may-2019
 * christopher.mike.96@gmail.com

 * Lima, Perú
 * Copyright (c) 2019 All rights reserved.
 */

interface IFirebaseManager {

    fun getFireBaseAuth() : FirebaseAuth

    fun getFireBaseCurrentUser(): FirebaseUser?

    fun getFireBaseFireStore() : FirebaseFirestore

    fun clientLogOut()
}