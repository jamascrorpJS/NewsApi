package com.jamascrorp.testproject.presentation.FragmentSources

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.jamascrorp.testproject.domain.usecases.GetSourcesUseCase
import javax.inject.Inject

class SourcesFragmentViewModel @Inject constructor(
    private val getSource: GetSourcesUseCase,
) : ViewModel() {

    fun getSource() = liveData {
        val x = getSource.invoke()
        emit(x)
    }


}