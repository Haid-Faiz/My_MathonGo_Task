package com.example.my_mathongo_task.di

import android.content.Context
import com.example.datasource.remote.ApiClient
import com.example.datasource.remote.apis.MathonGoApi
import com.example.my_mathongo_task.utils.AppPrefs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesApiClient(): ApiClient = ApiClient()

    @Provides
    @Singleton
    fun providesMathonGoApi(apiClient: ApiClient): MathonGoApi =
        apiClient.retrofit.create(MathonGoApi::class.java)

    @Provides
    @Singleton
    fun providesAppPrefs(@ApplicationContext context: Context): AppPrefs = AppPrefs(context)
}
