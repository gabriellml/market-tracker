package br.com.alura.markettracker.model.webclient.api

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EndPoint {
    @GET("npm/@fawazahmed0/currency-api@latest/v1/currencies.min.json")
    fun getCurrencies() : Call<JsonObject>

    @GET("npm/@fawazahmed0/currency-api@latest/v1/currencies/{currency}.min.json")
    fun getCurrencyRate(@Path("currency") from: String): Call<JsonObject>
}

