package br.com.alura.markettracker.model.dao

import br.com.alura.markettracker.model.Pair

class CurrencyDao {

    fun addPair(marketType: String, par: Pair){
        val index = indiceDoPar(marketType, par)
        if(marketType == "forex"){
            if (index != -1) {
                forexList[index].price = par.price
            } else {
                forexList.add(par)
            }
        } else if(marketType == "crypto"){
            if (index != -1) {
                cryptoList[index].price = par.price
            } else {
                cryptoList.add(par)
            }
        }
    }

    fun getPairList(marketType : String) : List<Pair>{
        if(marketType == "forex"){
            return forexList.toList()
        } else {
            return cryptoList.toList()
        }
    }

    private fun existePar(par: Pair): Boolean {
        return forexList.any { it.parity == par.parity }
    }

    private fun indiceDoPar(marketType: String, par: Pair): Int {
        if(marketType == "forex"){
            return forexList.indexOfFirst { it.parity == par.parity }
        } else {
            return cryptoList.indexOfFirst { it.parity == par.parity }
        }
    }

    companion object {
        private val forexList = mutableListOf<Pair>()
        private val cryptoList : MutableList<Pair> = mutableListOf<Pair>()
    }
}