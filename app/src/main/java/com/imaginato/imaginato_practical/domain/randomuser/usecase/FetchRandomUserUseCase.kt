package com.imaginato.imaginato_practical.domain.randomuser.usecase

import com.imaginato.imaginato_practical.data.randomuser.entity.RandomUserResponse
import com.imaginato.imaginato_practical.domain.base.BaseUseCase
import com.imaginato.imaginato_practical.domain.randomuser.repository.RandomUserRepository
import javax.inject.Inject

class FetchRandomUserUseCase @Inject constructor(
    private val randomUserRepository: RandomUserRepository
) : BaseUseCase<RandomUserResponse, FetchRandomUserUseCase.Param>() {

    data class Param(val page: Int)

    override suspend fun execute(params: Param): RandomUserResponse {
        return randomUserRepository.fetchRandomUsers(params.page)
    }
}
