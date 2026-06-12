package com.marcioposgraduacao.trucogamemarker


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var scoreP1 = 0
    private var scoreP2 = 0
    private var winsP1 = 0
    private var winsP2 = 0
    private var nameP1 = "Player 1"
    private var nameP2 = "Player 2"

    private lateinit var tvPlayer1: TextView
    private lateinit var tvPlayer2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvPlayer1 = findViewById<TextView>(R.id.tvPlayer)
        tvPlayer2 = findViewById<TextView>(R.id.tvPlayer2)

        updateScoreDisplay()

        // Configuração dos botões do Jogador 1
        findViewById<Button>(R.id.btnPlus1_P1).setOnClickListener { addPoints(1, 1) }
        findViewById<Button>(R.id.btnPlus3_P1).setOnClickListener { addPoints(3, 1) }
        findViewById<Button>(R.id.btnPlus6_P1).setOnClickListener { addPoints(6, 1) }
        findViewById<Button>(R.id.btnPlus9_P1).setOnClickListener { addPoints(9, 1) }
        findViewById<Button>(R.id.btnPlus12_P1).setOnClickListener { addPoints(12, 1) }

        // Configuração dos botões do Jogador 2
        findViewById<Button>(R.id.btnPlus1_P2).setOnClickListener { addPoints(1, 2) }
        findViewById<Button>(R.id.btnPlus3_P2).setOnClickListener { addPoints(3, 2) }
        findViewById<Button>(R.id.btnPlus6_P2).setOnClickListener { addPoints(6, 2) }
        findViewById<Button>(R.id.btnPlus9_P2).setOnClickListener { addPoints(9, 2) }
        findViewById<Button>(R.id.btnPlus12_P2).setOnClickListener { addPoints(12, 2) }

        // Botões de Ação
        // Os ouvintes de clique agora são tratados via android:onClick no XML
    }

    private fun addPoints(points: Int, player: Int) {
        if (player == 1) {
            scoreP1 += points
            if (scoreP1 >= 12) showWinnerDialog(nameP1, 1)
        } else {
            scoreP2 += points
            if (scoreP2 >= 12) showWinnerDialog(nameP2, 2)
        }
        updateScoreDisplay()
    }

    /*private fun updateScoreDisplay() {
        tvPlayer1.text = getString(R.string.player_1_score, scoreP1).replace("Player 1", nameP1)
        tvPlayer2.text = getString(R.string.player_2_score, scoreP2).replace("Player 2", nameP2)
    }*/

    private fun updateScoreDisplay() {
        tvPlayer1.text = getString(R.string.player_1_score, scoreP1)
            .replace("Player 1", nameP1)
        tvPlayer2.text = getString(R.string.player_2_score, scoreP2)
            .replace("Player 2", nameP2)
    }
    private fun showWinnerDialog(winnerName: String, playerIndex: Int) {
        if (playerIndex == 1) winsP1++ else winsP2++

        AlertDialog.Builder(this)
            .setTitle("Fim da Partida!")
            .setMessage("O jogador $winnerName venceu esta rodada!")
            .setPositiveButton("OK") { _, _ ->
                scoreP1 = 0
                scoreP2 = 0
                updateScoreDisplay()
            }
            .setCancelable(false)
            .show()
    }

    fun btnMatchHistory(view: View) {
        val intent = Intent(this, OverallScoreActivity::class.java)
        intent.putExtra("NAME_P1", nameP1)
        intent.putExtra("NAME_P2", nameP2)
        intent.putExtra("WINS_P1", winsP1)
        intent.putExtra("WINS_P2", winsP2)
        startActivity(intent)

       /* val intent = Intent(this, PlayerNamesActivity::class.java)
        intent.putExtra("CURRENT_NAME_P1", nomeAtualP1)
        intent.putExtra("CURRENT_NAME_P2", nomeAtualP2)
        startActivityForResult(intent, SEU_CODIGO_DE_REQUEST)*/
    }

    fun btnResetHistory(view: View) {
        scoreP1 = 0
        scoreP2 = 0
        winsP1 = 0
        winsP2 = 0
        nameP1 = "Player 1"
        nameP2 = "Player 2"
        updateScoreDisplay()
        Toast.makeText(this, "Histórico reiniciado com sucesso!",
            Toast.LENGTH_SHORT).show()
    }

    fun btnSetNames(view: View) {
        val intent = Intent(this, PlayerNamesActivity::class.java)
        intent.putExtra("CURRENT_NAME_P1", nameP1)
        intent.putExtra("CURRENT_NAME_P2", nameP2)
        startActivityForResult(intent, 100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK) {
            nameP1 = data?.getStringExtra("NEW_NAME_P1") ?: nameP1
            nameP2 = data?.getStringExtra("NEW_NAME_P2") ?: nameP2
            updateScoreDisplay()
        }
    }
}


