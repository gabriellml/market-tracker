package br.com.alura.markettracker.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.markettracker.databinding.CurrencyDisplayBinding
import br.com.alura.markettracker.model.Pair


class CurrencyViewerAdapter(private val context: Context, pares: List<Pair>) : RecyclerView.Adapter<CurrencyViewerAdapter.ViewHolder>(){

    private val pares = pares.toMutableList()

    class ViewHolder(binding: CurrencyDisplayBinding) : RecyclerView.ViewHolder(binding.root) {

        private val parity = binding.activityMarketViewerCurrency
        private val price = binding.activityMarketViewerPrice

        fun vincula(par : Pair) {
            parity.text = par.parity
            price.text = par.price
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CurrencyDisplayBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return pares.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val par = pares[position]
        holder.vincula(par)
    }

    fun atualiza(pares: List<Pair>) {
        this.pares.clear()
        this.pares.addAll(pares)
        notifyDataSetChanged()
    }

}