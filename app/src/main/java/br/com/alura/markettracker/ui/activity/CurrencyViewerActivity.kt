package br.com.alura.markettracker.ui.activity


import androidx.activity.viewModels
import CurrencyViewModel
import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.markettracker.databinding.ActivityCurrencyViewerBinding
import br.com.alura.markettracker.ui.adapter.CurrencyViewerAdapter



class CurrencyViewerActivity : AppCompatActivity(){

    private val viewModel: CurrencyViewModel by viewModels()
    private lateinit var dataTextView: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter : CurrencyViewerAdapter
    private lateinit var type : String
    private lateinit var textSearchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        type = intent.getStringExtra("type").toString()
        super.onCreate(savedInstanceState)
        val binding = ActivityCurrencyViewerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        textSearchView = binding.activityPriceSearchInputEdittext
        recyclerView = binding.activityPriceRecyclerview
        dataTextView = binding.activityPriceData
        viewModel.init()
        adapter = CurrencyViewerAdapter(this, viewModel.dao.getPairList(type))
        recyclerView.adapter = adapter
        viewModel.currentDate.observe(this, Observer { currentDate ->
            dataTextView.text = currentDate
        })

        textSearchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(string: String?): Boolean {
                TODO("Not yet implemented")
            }
            override fun onQueryTextChange(string: String?): Boolean {
                if (string.isNullOrEmpty()) {
                    adapter = CurrencyViewerAdapter(this@CurrencyViewerActivity, viewModel.dao.getPairList(type))
                    recyclerView.adapter = adapter
                } else {
                    // Texto da busca não está vazio, execute a busca normalmente
                    viewModel.performSearch(string.toString(), type)
                }
                return true
            }
        })

        viewModel.pairList.observe(this, Observer { pairs ->
            adapter = CurrencyViewerAdapter(this, pairs)
            adapter.atualiza(pairs)
            recyclerView.adapter = adapter
        })


    }
}