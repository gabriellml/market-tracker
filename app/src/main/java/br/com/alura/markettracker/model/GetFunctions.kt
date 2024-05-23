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
                                val offer = "Euro"
                                createPairList(demand = "usd", offer, currency, data, dao, marketType)
                                createPairList(demand = "gbp", offer, currency, data, dao, marketType)
                                createPairList(demand = "jpy", offer, currency, data, dao, marketType)
                                createPairList(demand = "cad", offer, currency, data, dao, marketType)
                                createPairList(demand = "aud", offer, currency, data, dao, marketType)
                                createPairList(demand = "nzd", offer, currency, data, dao, marketType)
                                createPairList(demand = "chf", offer, currency, data, dao, marketType)
                                createPairList(demand = "brl", offer, currency, data, dao, marketType)
                            }

                            "gbp" -> {
                                val offer = "Great British Pound"
                                createPairList(demand = "usd", offer, currency, data, dao, marketType)
                                createPairList(demand = "jpy", offer, currency, data, dao, marketType)
                                createPairList(demand = "cad", offer, currency, data, dao, marketType)
                                createPairList(demand = "aud", offer, currency, data, dao, marketType)
                                createPairList(demand = "nzd", offer, currency, data, dao, marketType)
                                createPairList(demand = "chf", offer, currency, data, dao, marketType)
                                createPairList(demand = "brl", offer, currency, data, dao, marketType)
                            }

                            "usd" -> {
                                val offer = "United States Dollar"
                                createPairList(demand = "jpy", offer, currency, data, dao, marketType)
                                createPairList(demand = "cad", offer, currency, data, dao, marketType)
                                createPairList(demand = "chf", offer, currency, data, dao, marketType)
                                createPairList(demand = "brl", offer, currency, data, dao, marketType)

                            }

                            "aud" -> {
                                val offer = "Australian Dollar"
                                createPairList(demand = "usd", offer, currency, data, dao, marketType)
                                createPairList(demand = "jpy", offer, currency, data, dao, marketType)
                                createPairList(demand = "cad", offer, currency, data, dao, marketType)
                                createPairList(demand = "nzd", offer, currency, data, dao, marketType)
                                createPairList(demand = "chf", offer, currency, data, dao, marketType)
                                createPairList(demand = "brl", offer, currency, data, dao, marketType)
                            }

                            "nzd" -> {
                                val offer = "New Zealand Dollar"
                                createPairList(demand = "usd", offer, currency, data, dao, marketType)
                                createPairList(demand = "jpy", offer, currency, data, dao, marketType)
                                createPairList(demand = "cad", offer, currency, data, dao, marketType)
                                createPairList(demand = "chf", offer, currency, data, dao, marketType)
                                createPairList(demand = "brl", offer, currency, data, dao, marketType)
                            }

                            "cad" -> {
                                val offer = "Canadian Dollar"
                                createPairList(demand = "jpy", offer, currency, data, dao, marketType)
                                createPairList(demand = "chf", offer, currency, data, dao, marketType)
                                createPairList(demand = "brl", offer, currency, data, dao, marketType)
                            }

                            "chf" -> {
                                val offer = "Swiss Franc"
                                createPairList(demand = "jpy", offer, currency, data, dao, marketType)
                                createPairList(demand = "brl", offer, currency, data, dao, marketType)

                            }

                            "btc" -> {
//                            precisa procurar por:
//                            usd
                                val offer = "Bitcoin"
                                createPairList(demand = "usdt", offer, currency, data, dao, marketType)
                            }
                            "eth" -> {
//                            precisa procurar por:
//                            usd
                                val offer = "Ethereum"
                                createPairList(demand = "usdt", offer, currency, data, dao, marketType)
                            }
                            "bnb" -> {
//                            precisa procurar por:
//                            usd
                                val offer = "Binance Coin"
                                createPairList(demand = "usdt", offer, currency, data, dao, marketType)
                            }
                            "sol" -> {
//                            precisa procurar por:
//                            usd
                                val offer = "Solana"
                                createPairList(demand = "usdt", offer, currency, data, dao, marketType)
                            }
                            "xrp" -> {
//                            precisa procurar por:
//                            usd
                                val offer = "Ripple"
                                createPairList(demand = "usdt", offer, currency, data, dao, marketType)
                            }
                            "doge" -> {
//                            precisa procurar por:
//                            usd
                                val offer = "Doge Coin"
                                createPairList(demand = "usdt", offer, currency, data, dao, marketType)
                            }
                            "ada" -> {
//                            precisa procurar por:
//                            usd
                                val offer = "Cardano"
                                createPairList(demand = "usdt", offer, currency, data, dao, marketType)
                            }
                            "shib" -> {
//                            precisa procurar por:
//                            usd
                                val offer = "Shiba Inu"
                                createPairList(demand = "usdt", offer, currency, data, dao, marketType)
                            }
                            "trx" -> {
//                            precisa procurar por:
//                            usd
                                val offer = "Tron"
                                createPairList(demand = "usdt", offer, currency, data, dao, marketType)
                            }
                            "ton" -> {
//                            precisa procurar por:
//                            usd
                                val offer = "Toncoin"
                                createPairList(demand = "usdt", offer, currency, data, dao, marketType)
                            }
                            "avax" -> {
//                            precisa procurar por:
//                            usd
                                val offer = "Avalanche"
                                createPairList(demand = "usdt", offer, currency, data, dao, marketType)
                            }
                            "link" -> {
//                            precisa procurar por:
//                            usd
                                val offer = "Chainlink"
                                createPairList(demand = "usdt", offer, currency, data, dao, marketType)
                            }
                            "near" -> {
//                            precisa procurar por:
//                            usd
                                val offer = "NEAR Protocol"
                                createPairList(demand = "usdt", offer, currency, data, dao, marketType)
                            }
                            "matic" -> {
//                            precisa procurar por:
//                            usd
                                val offer = "Polygon"
                                createPairList(demand = "usdt", offer, currency, data, dao, marketType)
                            }
                            "ltc" -> {
//                            precisa procurar por:
//                            usd
                                val offer = "Litecoin"
                                createPairList(demand = "usdt", offer, currency, data, dao, marketType)
                            }
                            "icp" -> {
//                            precisa procurar por:
//                            usd
                                val offer = "Internet Protocol"
                                createPairList(demand = "usdt", offer, currency, data, dao, marketType)
                            }
                            "trx" -> {
//                            precisa procurar por:
//                            usd
                                val offer = "Tron"
                                createPairList(demand = "usdt", offer, currency, data, dao, marketType)
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

    fun createPairList(demand: String, offer: String, currency: String, data: JsonObject?, dao: CurrencyDao, marketType: String) {
        val price = data?.getAsJsonObject(currency)?.get(demand)?.asString
        var demandFiat = ""
        when(demand){
            "brl" -> { demandFiat = "Brazilian Real" }
            "gbp" -> { demandFiat = "Great British Pound" }
            "usd" -> { demandFiat = "United States Dollar" }
            "cad" -> { demandFiat = "Canadian Dollar" }
            "aud" -> { demandFiat = "Australian Dollar" }
            "nzd" -> { demandFiat = "New Zealand Dollar" }
            "chf" -> { demandFiat = "Swiss Franc" }
            "eur" -> { demandFiat = "Euro" }
            "jpy" -> { demandFiat = "Japanese Yen" }
            "usdt" -> { demandFiat = "Tether USD" }
        }
        val novoPar = Pair(
            offerFiatCurrency = offer,
            demandFiatCurrency = demandFiat,
            offerCode = currency.toUpperCase(),
            demandCode = demand.toUpperCase(),
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
            getCurrencyPrice("ton", dao, marketType)
            getCurrencyPrice("avax", dao, marketType)
            getCurrencyPrice("link", dao, marketType)
            getCurrencyPrice("near", dao, marketType)
            getCurrencyPrice("matic", dao, marketType)
            getCurrencyPrice("ltc", dao, marketType)
            getCurrencyPrice("icp", dao, marketType)
        }
    }

    fun updateDate() : String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        Log.i(TAG, "criaData: nova dataaa")
        return dateFormat.format(Date())
    }


}