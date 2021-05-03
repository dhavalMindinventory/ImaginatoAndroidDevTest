package com.imaginato.imaginato_practical.domain.randomuser.repository

import com.imaginato.imaginato_practical.data.randomuser.entity.RandomUserResponse


interface RandomUserRepository {
    suspend fun fetchRandomUsers(page: Int): RandomUserResponse
}
