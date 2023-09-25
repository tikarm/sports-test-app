package com.example.testapp.di

import com.example.testapp.data.EventsRepositoryImpl
import com.example.testapp.domain.EventsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class EventsDataModule {

    @Binds
    abstract fun eventsRepository(repository: EventsRepositoryImpl): EventsRepository
}