package com.jamascrorp.testproject.data.repository

import android.content.Context
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.jamascrorp.testproject.domain.repository.AuthorizationRepository
import com.jamascrorp.testproject.presentation.Util
import javax.inject.Inject

class AuthorizationRepositoryImpl @Inject constructor(val context: Context) :
    AuthorizationRepository {

    val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun signUp(email: String, password: String): Task<AuthResult> {
        return firebaseAuth.createUserWithEmailAndPassword(email, password).apply {
            addOnSuccessListener {
                Util.toast(context, "You registry is ok")
            }
            addOnFailureListener {
                Util.toast(context, "You registry is fail")
            }
        }
    }
}