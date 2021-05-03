package com.imaginato.imaginato_practical.domain.usecase

import com.imaginato.imaginato_practical.domain.base.BaseUseCase
import kotlinx.coroutines.delay
import javax.inject.Inject

class SplashUseCase @Inject constructor(
) : BaseUseCase<Boolean, SplashUseCase.Params>() {

    data class Params(val delayInMillis: Long)

    override suspend fun execute(params: Params): Boolean {
        delay(params.delayInMillis)
        return true
    }
}
