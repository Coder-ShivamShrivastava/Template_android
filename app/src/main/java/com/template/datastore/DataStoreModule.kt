package com.template.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataStoreModule {

    @Provides
    @Singleton
    fun getDataStore(@ApplicationContext context: Context): DataStore<Preferences> =
        PreferenceDataStoreFactory.create(produceFile = {
            context.preferencesDataStoreFile(DATA_STORE_NAME)
        })


    @Provides
    @Singleton
    fun exceptionHandler(): CoroutineExceptionHandler = CoroutineExceptionHandler { _, t ->
        t.printStackTrace()

        CoroutineScope(Dispatchers.Main).launch {
            t.printStackTrace()
        }
    }
}


