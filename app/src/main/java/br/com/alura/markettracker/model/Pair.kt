package br.com.alura.markettracker.model

data class Pair (
    val offerFiatCurrency : String,
    val demandFiatCurrency : String,
    val offerCode : String,
    val demandCode : String,
    val parity : String,
    var price : String
    )