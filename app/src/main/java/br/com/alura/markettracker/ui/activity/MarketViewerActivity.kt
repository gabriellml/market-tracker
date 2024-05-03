package br.com.alura.markettracker.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.telecom.Call
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.markettracker.databinding.ActivityMarketViewerBinding
import br.com.alura.markettracker.model.GetFunctions
import br.com.alura.markettracker.model.webclient.RetrofitInitializer
import br.com.alura.markettracker.model.webclient.api.EndPoint
import com.google.gson.JsonObject
import okhttp3.OkHttpClient
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.Timer
import java.util.TimerTask


class MarketViewerActivity: AppCompatActivity() {

    private lateinit var dataTextView: TextView
    private val functions : GetFunctions = GetFunctions()
    private lateinit var handler : Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMarketViewerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dataTextView = binding.activityMarketViewerData
        handler = Handler(Looper.getMainLooper())
        handler.post(object : Runnable {
            override fun run() {
                dataTextView.text = functions.updateDate()
                handler.postDelayed(this, 1000)
            }
        })

        binding.activityMarketViewerForex.setOnClickListener {
            val intent = Intent(this, CurrencyViewerActivity::class.java)
            intent.putExtra("type", "forex")
            startActivity(intent)
        }

        binding.activityMarketViewerCrypto.setOnClickListener {
            val intent = Intent(this, CurrencyViewerActivity::class.java)
            intent.putExtra("type", "crypto")
            startActivity(intent)
        }

    }

    override fun onDestroy() {
        handler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }

}