package br.com.alura.markettracker.model.webclient

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {
        companion object {
            fun getRetrofitInstance(path: String): Retrofit {
                return Retrofit.Builder()
                    .baseUrl(path)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
        }
}