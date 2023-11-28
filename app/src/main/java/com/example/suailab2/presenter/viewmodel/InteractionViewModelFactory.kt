package com.example.suailab2.presenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.suailab2.domain.usecases.GetLastValuesFromDBUsecase
import com.example.suailab2.domain.usecases.SendValueUsecase
import javax.inject.Inject

class InteractionViewModelFactory @Inject constructor
    (private val getLastValuesFromDBUsecase: GetLastValuesFromDBUsecase,
     private val sendValueUsecase: SendValueUsecase
) :ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return InteractionFragmentViewModel(getLastValuesFromDBUsecase,
            sendValueUsecase) as T
    }
}