package com.imaginato.imaginato_practical.di

import com.imaginato.imaginato_practical.data.randomuser.respository.RandomUserRepositoryImpl
import com.imaginato.imaginato_practical.domain.randomuser.repository.RandomUserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindAccountRepository(
        userRepository: RandomUserRepositoryImpl
    ): RandomUserRepository

}
