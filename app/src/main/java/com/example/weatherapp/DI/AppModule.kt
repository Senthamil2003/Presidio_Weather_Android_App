package com.example.weatherapp.DI

import android.content.Context
import com.example.weatherapp.data.model.AppState
import com.example.weatherapp.data.model.WeatherResponse
import com.example.weatherapp.data.remote.WeatherAPI
import com.example.weatherapp.data.repository.WeatherRepositoryImp
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.redux.Action
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.reduxkotlin.Store
import org.reduxkotlin.createStore
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideAp():WeatherAPI{
        return Retrofit.Builder().baseUrl("https://api.weatherapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(WeatherAPI::class.java)
    }
    @Provides
    @Singleton
    fun provideRepository(api: WeatherAPI): WeatherRepository {
        return WeatherRepositoryImp(api)
    }

    @Provides
    @Singleton
    fun provideStore(): Store<AppState> {
        val reducer: (AppState, Any) -> AppState = { state, action ->
            when (action) {
                is Action.FetchWeather -> state.copy(loading = true, error = null)
                is Action.WeatherFetchSuccess -> state.copy(
                    loading = false,
                    weatherData = action.weather,
                    error = null
                )
                is Action.WeatherFetchError -> state.copy(
                    loading = false,
                    error = action.error
                )
                is Action.SelectDay -> state.copy(selectedDayIndex = action.index)
                is Action.Search->state.copy(searchLocation = action.search)

                else -> state
            }
        }

        return createStore(reducer, AppState())
    }
    @Provides
    @Singleton
    fun provideApplicationContext(@ApplicationContext context: Context): Context {
        return context
    }

}