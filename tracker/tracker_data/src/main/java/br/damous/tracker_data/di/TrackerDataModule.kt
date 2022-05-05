package br.damous.tracker_data.di

import android.app.Application
import androidx.room.Room
import br.damous.tracker_data.local.TrackerDatabase
import br.damous.tracker_data.remote.OpenFoodApi
import br.damous.tracker_data.repository.TrackerRepositoryImpl
import br.damous.tracker_data.util.Constants.BASE_URL
import br.damous.tracker_data.util.Constants.DB_NAME
import br.damous.tracker_domain.repository.TrackerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TrackerDataModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideOpenFoodApi(client: OkHttpClient): OpenFoodApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideTrackerDatabase(app: Application): TrackerDatabase {
        return Room.databaseBuilder(
            app,
            TrackerDatabase::class.java,
            DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideTrackerRepository(
        api: OpenFoodApi,
        db: TrackerDatabase
    ): TrackerRepository {
        return TrackerRepositoryImpl(db.dao, api)
    }
}