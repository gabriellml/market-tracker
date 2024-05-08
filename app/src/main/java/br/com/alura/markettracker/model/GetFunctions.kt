package br.com.alura.markettracker.model

import android.content.ContentValues.TAG
import android.util.Log
import retrofit2.Call
import br.com.alura.markettracker.model.dao.CurrencyDao
import br.com.alura.markettracker.model.webclient.RetrofitInitializer
import br.com.alura.markettracker.model.webclient.api.EndPoint
import com.google.gson.JsonObject
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

open class GetFunctions() {




    fun getCurrencyPrice(currency : String, dao: CurrencyDao, marketType: String) {
        val retrofitClient = RetrofitInitializer.getRetrofitInstance("https://cdn.jsdelivr.net/")
        val endPoint = retrofitClient.create(EndPoint::class.java)

        endPoint.getCurrencyRate(currency).enqueue(object : Callback<JsonObject> {

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {

                if (response.isSuccessful) {
                    val data = response.body()
                        when(currency) {
                            "eur" -> {
                                createPairList(demand = "usd", currency, data, dao, marketType)
                                createPairList(demand = "gbp", currency, data, dao, marketType)
                                createPairList(demand = "jpy", currency, data, dao, marketType)
                                createPairList(demand = "cad", currency, data, dao, marketType)
                                createPairList(demand = "aud", currency, data, dao, marketType)
                                createPairList(demand = "nzd", currency, data, dao, marketType)
                                createPairList(demand = "chf", currency, data, dao, marketType)
                                createPairList(demand = "brl", currency, data, dao, marketType)
                            }

                            "gbp" -> {
                                createPairList(demand = "usd", currency, data, dao, marketType)
                                createPairList(demand = "jpy", currency, data, dao, marketType)
                                createPairList(demand = "cad", currency, data, dao, marketType)
                                createPairList(demand = "aud", currency, data, dao, marketType)
                                createPairList(demand = "nzd", currency, data, dao, marketType)
                                createPairList(demand = "chf", currency, data, dao, marketType)
                                createPairList(demand = "brl", currency, data, dao, marketType)
                            }

                            "usd" -> {
                                createPairList(demand = "jpy", currency, data, dao, marketType)
                                createPairList(demand = "cad", currency, data, dao, marketType)
                                createPairList(demand = "chf", currency, data, dao, marketType)
                                createPairList(demand = "brl", currency, data, dao, marketType)

                            }

                            "aud" -> {

                                createPairList(demand = "usd", currency, data, dao, marketType)
                                createPairList(demand = "jpy", currency, data, dao, marketType)
                                createPairList(demand = "cad", currency, data, dao, marketType)
                                createPairList(demand = "nzd", currency, data, dao, marketType)
                                createPairList(demand = "chf", currency, data, dao, marketType)
                                createPairList(demand = "brl", currency, data, dao, marketType)
                            }

                            "nzd" -> {
                                createPairList(demand = "usd", currency, data, dao, marketType)
                                createPairList(demand = "jpy", currency, data, dao, marketType)
                                createPairList(demand = "cad", currency, data, dao, marketType)
                                createPairList(demand = "chf", currency, data, dao, marketType)
                                createPairList(demand = "brl", currency, data, dao, marketType)
                            }

                            "cad" -> {
                                createPairList(demand = "jpy", currency, data, dao, marketType)
                                createPairList(demand = "chf", currency, data, dao, marketType)
                                createPairList(demand = "brl", currency, data, dao, marketType)
                            }

                            "chf" -> {
                                val jpyRate = data?.get("jpy")?.asString
                                createPairList(demand = "jpy", currency, data, dao, marketType)
                                createPairList(demand = "brl", currency, data, dao, marketType)

                            }

                            "btc" -> {
//                            precisa procurar por:
//                            usd
                                createPairList(demand = "usdt", currency, data, dao, marketType)
                            }
                            "eth" -> {
//                            precisa procurar por:
//                            usd
                                createPairList(demand = "usdt", currency, data, dao, marketType)
                            }
                            "bnb" -> {
//                            precisa procurar por:
//                            usd
                                createPairList(demand = "usdt", currency, data, dao, marketType)
                            }
                            "sol" -> {
//                            precisa procurar por:
//                            usd
                                createPairList(demand = "usdt", currency, data, dao, marketType)
                            }
                            "xrp" -> {
//                            precisa procurar por:
//                            usd
                                createPairList(demand = "usdt", currency, data, dao, marketType)
                            }
                            "doge" -> {
//                            precisa procurar por:
//                            usd
                                createPairList(demand = "usdt", currency, data, dao, marketType)
                            }
                            "ada" -> {
//                            precisa procurar por:
//                            usd
                                createPairList(demand = "usdt", currency, data, dao, marketType)
                            }
                            "shib" -> {
//                            precisa procurar por:
//                            usd
                                createPairList(demand = "usdt", currency, data, dao, marketType)
                            }
                            "trx" -> {
//                            precisa procurar por:
//                            usd
                                createPairList(demand = "usdt", currency, data, dao, marketType)
                            }
                        }
                } else {
                    println("Falha na requisição: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                println("Não foi possível recuperar o valor. Erro: ${t.message}")
            }
        })
    }

    fun createPairList(demand: String, currency: String, data: JsonObject?, dao: CurrencyDao, marketType: String) {
        val price = data?.getAsJsonObject(currency)?.get(demand)?.asString
        val novoPar = Pair(
            parity = "$currency".toUpperCase() + "$demand".toUpperCase(),
            price = price.toString()
        )
        dao.addPair(marketType, novoPar)
        Log.i(TAG, "criaPar: ${novoPar.parity}")
        Log.i(TAG, "criaPar: ${novoPar.price}")
        Log.i(TAG, "criaPar: ${marketType}")
    }

    fun callingAPI(dao: CurrencyDao, marketType: String) {
        if(marketType == "forex"){
            getCurrencyPrice("eur", dao, marketType)
            getCurrencyPrice("gbp", dao, marketType)
            getCurrencyPrice("usd", dao, marketType)
            getCurrencyPrice("aud", dao, marketType)
            getCurrencyPrice("nzd", dao, marketType)
            getCurrencyPrice("cad", dao, marketType)
            getCurrencyPrice("chf", dao, marketType)
        } else if(marketType == "crypto"){
            getCurrencyPrice("btc", dao, marketType)
            getCurrencyPrice("eth", dao, marketType)
            getCurrencyPrice("bnb", dao, marketType)
            getCurrencyPrice("sol", dao, marketType)
            getCurrencyPrice("xrp", dao, marketType)
            getCurrencyPrice("doge", dao, marketType)
            getCurrencyPrice("ada", dao, marketType)
            getCurrencyPrice("shib", dao, marketType)
            getCurrencyPrice("trx", dao, marketType)
        }
    }

    fun updateDate() : String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        Log.i(TAG, "criaData: nova dataaa")
        return dateFormat.format(Date())
    }


}