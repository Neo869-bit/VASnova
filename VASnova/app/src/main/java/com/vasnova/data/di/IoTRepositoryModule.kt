package com.vasnova.data.di

import com.vasnova.data.FakeIoTRepository
import com.vasnova.domain.IoTRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class IoTRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindIoTRepository(impl: FakeIoTRepository): IoTRepository
}
