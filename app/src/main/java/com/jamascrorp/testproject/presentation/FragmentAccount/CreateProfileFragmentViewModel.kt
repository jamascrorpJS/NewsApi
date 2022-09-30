package com.jamascrorp.testproject.presentation.FragmentAccount

import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.jamascrorp.testproject.domain.usecases.GetAuthorizationByEmail
import javax.inject.Inject

class CreateProfileFragmentViewModel @Inject constructor(private val getAuthorizationByEmail: GetAuthorizationByEmail) :
    ViewModel() {

    fun signUp(email: String, password: String): Task<AuthResult> {
        return getAuthorizationByEmail.invoke(email, password)
    }
}