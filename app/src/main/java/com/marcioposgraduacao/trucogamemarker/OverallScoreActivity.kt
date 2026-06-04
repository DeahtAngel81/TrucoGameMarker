package com.marcioposgraduacao.trucogamemarker

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class OverallScoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_overall_score)


            // Recuperação dos dados enviados pela MainActivity
            val nameP1 = intent.getStringExtra("NAME_P1") ?: "Player 1"
            val nameP2 = intent.getStringExtra("NAME_P2") ?: "Player 2"
            val winsP1 = intent.getIntExtra("WINS_P1", 0)
            val winsP2 = intent.getIntExtra("WINS_P2", 0)


            // Localizando os TextViews pelos novos IDs refatorados
            val tvStatusP1 = findViewById<TextView>(R.id.tvPlayer1Score)
            val tvStatusP2 = findViewById<TextView>(R.id.tvPlayer2Score)


            // Atualizando a Interface com os valores corretos
            tvStatusP1.text = "$nameP1: $winsP1 vitórias"
            tvStatusP2.text = "$nameP2: $winsP2 vitórias"

        }
    }