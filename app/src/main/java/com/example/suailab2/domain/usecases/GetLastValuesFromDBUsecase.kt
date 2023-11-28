package com.example.suailab2.domain.usecases

import com.example.suailab2.domain.repository.ValuesDbRepository
import javax.inject.Inject

class GetLastValuesFromDBUsecase @Inject constructor
    (private val dbRepository: ValuesDbRepository ) {

        suspend fun execute(): List<Double> =
            dbRepository.getLastValues()
}