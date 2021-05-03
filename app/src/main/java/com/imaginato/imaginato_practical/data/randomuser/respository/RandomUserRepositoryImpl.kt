package com.imaginato.imaginato_practical.data.randomuser.respository

import com.imaginato.imaginato_practical.data.randomuser.entity.RandomUserResponse
import com.imaginato.imaginato_practical.domain.randomuser.repository.RandomUserRepository
import javax.inject.Inject

class RandomUserRepositoryImpl @Inject constructor(
    private val randomUserApi: RandomUserApi
) : RandomUserRepository {

    override suspend fun fetchRandomUsers(page: Int): RandomUserResponse {
        return randomUserApi.fetchRandomUsers(page)
    }
}
