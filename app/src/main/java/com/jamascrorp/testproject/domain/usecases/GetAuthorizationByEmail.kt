package com.jamascrorp.testproject.domain.usecases

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.jamascrorp.testproject.domain.repository.AuthorizationRepository
import javax.inject.Inject

class GetAuthorizationByEmail @Inject constructor(val repository: AuthorizationRepository) {

    operator fun invoke(email: String, password: String): Task<AuthResult> {
        return repository.signUp(email, password)
    }
}