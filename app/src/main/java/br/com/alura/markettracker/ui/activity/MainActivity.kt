package br.com.alura.markettracker.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import br.com.alura.markettracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.activityMainBotaoNext.setOnClickListener {
            val intent = Intent(this, MarketViewerActivity::class.java)
            startActivity(intent)
        }

    }


}