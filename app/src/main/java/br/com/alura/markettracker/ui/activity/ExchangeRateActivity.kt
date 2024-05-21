package br.com.alura.markettracker.ui.activity

import CurrencyViewModel
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import br.com.alura.markettracker.databinding.ActivityExchangeRateBinding
import java.text.DecimalFormat

class ExchangeRateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExchangeRateBinding
    private lateinit var dataTextView : TextView        // Data e Hora
    private lateinit var offerTextView : TextView       // Espaço para exibição da Moeda 1
    private lateinit var demandTextView : TextView      // Espaço para exibição da Moeda 2
    private lateinit var offerValueEditText : EditText  // Espaço para digitar o valor 1
    private lateinit var demandValueEditText : EditText // Espaço para digitar o valor 2
    private lateinit var currency1 : TextView           // Espaço para o codigo da moeda1
    private lateinit var currency2 : TextView           // Espaço para o codigo da moeda2
    private var price : Double = 0.0                    // Variavel que recebe o valor do câmbio
    private val decimalFormat = DecimalFormat("#.##")
    private val viewModel: CurrencyViewModel by viewModels()
    private var ignoreChanges: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {

        price = intent.getStringExtra("price")!!.toDouble()
        super.onCreate(savedInstanceState)

        binding = ActivityExchangeRateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        currency1 = binding.activityExchangeRateCurrency1Code
        currency2 = binding.activityExchangeRateCurrency2Code
        offerValueEditText = binding.activityExchangeRateInputEdittext1
        demandValueEditText = binding.activityExchangeRateInputEdittext2
        offerTextView = binding.activityExchangeRateOffer
        demandTextView = binding.activityExchangeRateDemand
        offerTextView.text = intent.getStringExtra("offer").toString()
        demandTextView.text = intent.getStringExtra("demand").toString()
        currency1.text = intent.getStringExtra("offerCode").toString()
        currency2.text = intent.getStringExtra("demandCode").toString()
        dataTextView = binding.activityExchangeRateData
        viewModel.init()
        viewModel.currentDate.observe(this, Observer { currentDate ->
            dataTextView.text = currentDate
        })
        setupTextWatchers()

        viewModel.exchangeResultFromTop.observe(this, Observer { result ->
            if (!ignoreChanges) {
                ignoreChanges = true
                demandValueEditText.setText(decimalFormat.format(result))
                ignoreChanges = false
            }
        })

        viewModel.exchangeResultFromBottom.observe(this, Observer { result ->
            if (!ignoreChanges) {
                ignoreChanges = true
                offerValueEditText.setText(decimalFormat.format(result))
                ignoreChanges = false
            }
        })

    }

    private fun setupTextWatchers() {
        offerValueEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!ignoreChanges) {
                    val amountText = s.toString()
                    if (amountText.isNotEmpty()) {
                        try {
                            val amount = amountText.toDouble()
                            viewModel.calculateFromTop(amount, price)
                        } catch (e: NumberFormatException) {
                            // TODO: catch exception
                        }
                    }
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        demandValueEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!ignoreChanges) {
                    val amountText = s.toString()
                    if (amountText.isNotEmpty()) {
                        try {
                            val amount = amountText.toDouble()
                            viewModel.calculateFromBottom(amount, price)
                        } catch (e: NumberFormatException) {
                            // TODO: catch exception
                        }
                    }
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }


}