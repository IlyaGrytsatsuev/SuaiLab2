package com.example.suailab2.presenter.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.suailab2.domain.usecases.GetLastValuesFromDBUsecase
import com.example.suailab2.domain.usecases.SendValueUsecase
import com.example.suailab2.presenter.ExceptionState
import com.example.suailab2.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class InteractionFragmentViewModel(
    private val getLastValuesFromDBUsecase: GetLastValuesFromDBUsecase,
    private val sendValueUsecase: SendValueUsecase
):ViewModel() {

    private val privateExceptionsState: MutableStateFlow<ExceptionState>
    = MutableStateFlow(ExceptionState.NoException)
    val exceptionsState: StateFlow<ExceptionState> = privateExceptionsState


    fun sendValue(value: Double, url: String? = null){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                sendValueUsecase.execute(value, url)
            }
            catch(e:Exception){
                privateExceptionsState.value = ExceptionState.Exception
            }
        }
    }

    fun printSavedValues() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                Log.i("dbList", getLastValuesFromDBUsecase.execute().toString())
            } catch (e: Exception) {
                privateExceptionsState.value = ExceptionState.Exception
            }
        }
    }

    fun sendRandomValue(){
        val value = Random.nextDouble()
        sendValue(value = value, url = null)
    }

}