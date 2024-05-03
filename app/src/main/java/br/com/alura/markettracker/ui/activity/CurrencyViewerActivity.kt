package br.com.alura.markettracker.ui.activity


import androidx.activity.viewModels
import CurrencyViewModel
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.markettracker.databinding.ActivityPriceBinding
import br.com.alura.markettracker.model.dao.CurrencyDao
import br.com.alura.markettracker.ui.adapter.CurrencyViewerAdapter


class CurrencyViewerActivity : AppCompatActivity(){

    private val viewModel: CurrencyViewModel by viewModels()
    private val dao = CurrencyDao()
    private lateinit var dataTextView: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter : CurrencyViewerAdapter
    private lateinit var type : String

    override fun onCreate(savedInstanceState: Bundle?) {
        type = intent.getStringExtra("type").toString()
        super.onCreate(savedInstanceState)
        val binding = ActivityPriceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView = binding.activityPriceRecyclerview
        dataTextView = binding.activityPriceData
        viewModel.init()

        viewModel.currentDate.observe(this, Observer { currentDate ->
            dataTextView.text = currentDate
        })

        type?.let {
            viewModel.fetchData(it)
            viewModel.pairList.observe(this, Observer { pairs ->
                    adapter = CurrencyViewerAdapter(this, pairs)
                    adapter.notifyDataSetChanged()
                    recyclerView.adapter = adapter
                    Log.i(TAG, "onCreate: atualizando o adapter")
            })
        }
    }
}