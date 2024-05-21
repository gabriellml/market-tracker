package br.com.alura.markettracker.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.markettracker.databinding.CurrencyDisplayBinding
import br.com.alura.markettracker.model.Pair
import br.com.alura.markettracker.ui.activity.ExchangeRateActivity


class CurrencyViewerAdapter(private val context: Context, pares: List<Pair>) : RecyclerView.Adapter<CurrencyViewerAdapter.ViewHolder>(){

    private val pares = pares.toMutableList()

    class ViewHolder(binding: CurrencyDisplayBinding) : RecyclerView.ViewHolder(binding.root) {

        private val parity = binding.activityMarketViewerCurrency
        private val price = binding.activityMarketViewerPrice
        private val offer = binding.activityMarketViewerOffer
        private val demand = binding.activityMarketViewerDemand

        fun vincula(par : Pair) {
            offer.text = par.offerFiatCurrency
            demand.text = par.demandFiatCurrency
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

        holder.itemView.setOnClickListener {
            // Crie uma Intent para abrir a nova activity
            val intent = Intent(context, ExchangeRateActivity::class.java)
            // Adicione quaisquer extras necessários à Intent, como dados do par clicado
            intent.putExtra("price", par.price)
            intent.putExtra("offer", par.offerFiatCurrency)
            intent.putExtra("demand", par.demandFiatCurrency)
            intent.putExtra("offerCode", par.offerCode)
            intent.putExtra("demandCode", par.demandCode)
            context.startActivity(intent)
        }
    }

    fun atualiza(pares: List<Pair>) {
        this.pares.clear()
        this.pares.addAll(pares)
        notifyDataSetChanged()
    }

}