package com.example.suailab2.domain.usecases

import com.example.suailab2.domain.repository.ValuesApiRepository
import com.example.suailab2.domain.repository.ValuesDbRepository
import javax.inject.Inject

class SendValueUsecase @Inject constructor
    (private val apiRepository: ValuesApiRepository){

        suspend fun execute(value: Double, url:String?){
            apiRepository.sendValue(value, url)
        }
}