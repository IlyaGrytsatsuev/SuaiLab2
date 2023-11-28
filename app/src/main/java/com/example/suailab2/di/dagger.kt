package com.example.suailab2.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.suailab2.data.db.DB
import com.example.suailab2.data.db.repository.ValuesDbRepositoryImpl
import com.example.suailab2.data.network.repository.ValuesApiRepositoryImpl
import com.example.suailab2.data.network.service.ValuesApiService
import com.example.suailab2.domain.repository.ValuesApiRepository
import com.example.suailab2.domain.repository.ValuesDbRepository
import com.example.suailab2.presenter.ui.InteractionFragment
import com.example.suailab2.presenter.viewmodel.InteractionViewModelFactory
import com.example.suailab2.utils.Constants
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Component(modules = [ApiModule::class, DbModel::class,
    ViewModelFactoriesModel::class])
interface AppComponent{
    @Component.Factory
    interface ComponentBuilder{
        fun create(@BindsInstance context: Context):AppComponent
    }
    fun inject(fragment: InteractionFragment)
}

@Module
interface ApiModule{
    companion object {
        @Provides
        fun provideGsonConverterFactory() : GsonConverterFactory
                = GsonConverterFactory.create(GsonBuilder().create())

        @Provides
        fun provideOkHttpClient() = OkHttpClient().newBuilder()
            .addInterceptor(
                HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        @Provides
        fun provideMemesApiService(converterFactory: GsonConverterFactory, client: OkHttpClient)
                : ValuesApiService = Retrofit.Builder()
            .addConverterFactory(converterFactory)
            .client(client)
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(ValuesApiService::class.java)
    }
    @Binds
    fun provideMemesApiRepository(memesApiRepositoryImpl: ValuesApiRepositoryImpl) : ValuesApiRepository
}

@Module
interface DbModel{
    companion object{
        @Provides
        fun provideDB(context:Context) =
            Room.databaseBuilder(context,
                DB::class.java, "valuesdb").build()
        @Provides
        fun provideCharacterDao(db: DB) = db.valuesDao()
    }

    @Binds
    fun provideCharactersDbRepository(valuesDbRepositoryImpl:
                                      ValuesDbRepositoryImpl
    ): ValuesDbRepository
}

@Module
interface ViewModelFactoriesModel{
    @Binds
    fun provideViewModelFactory(viewModelFactory: InteractionViewModelFactory)
    : ViewModelProvider.Factory
}