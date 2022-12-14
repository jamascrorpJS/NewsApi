package com.jamascrorp.testproject.domain.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface AuthorizationRepository {

    fun signUp(email: String, password: String): Task<AuthResult>
}