package com.marcioposgraduacao.trucogamemarker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.toString


class PlayerNamesActivity : AppCompatActivity() {

    private lateinit var etP1: EditText
    private lateinit var etP2: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_names)

        etP1 = findViewById(R.id.etPlayer1Name)
        etP2 = findViewById(R.id.etPlayer2Name)
        val btnConfirm = findViewById<Button>(R.id.btnConfirm)

        // Hints do editText é apresentado
        etP1.hint = "Nome do Jogador 1"
        etP2.hint = "Nome do Jogador 2"

        // Em caso de rotação da tela os dados permanecem salvos
        if (savedInstanceState != null) {
            etP1.setText(savedInstanceState.getString("P1_NAME"))
            etP2.setText(savedInstanceState.getString("P2_NAME"))
        }

        /*Após inserir os nomes, Confirmar, sair e clicar em INF. Nomes,
        os dados salvos estão gravados*/
        else {
            val currentName1 = intent.getStringExtra("CURRENT_NAME_P1")
            val currentName2 = intent.getStringExtra("CURRENT_NAME_P2")

            /* permissão para preencher o texto somente se estiver o campo vazio.
             Caso clicar em Zerar Histórico o nome iserido é apagado*/
            if (!currentName1.isNullOrBlank() && !currentName1.equals(
                    "Player 1",
                    ignoreCase = true
                )
            ) {
                etP1.setText(currentName1)
            } else {
                etP1.text = null // Garante que o campo esteja limpo para mostrar o hint
            }

            if (!currentName2.isNullOrBlank() && !currentName2.equals(
                    "Player 2",
                    ignoreCase = true
                )
            ) {
                etP2.setText(currentName2)
            } else {
                etP2.text = null // Garante que o campo esteja limpo para mostrar o hint
            }
        }

        btnConfirm.setOnClickListener {
            val newName1 = etP1.text.toString().trim()
            val newName2 = etP2.text.toString().trim()

            if (newName1.isNotEmpty() && newName2.isNotEmpty()) {
                val resultIntent = Intent()
                resultIntent.putExtra("NEW_NAME_P1", newName1)
                resultIntent.putExtra("NEW_NAME_P2", newName2)
                setResult(RESULT_OK, resultIntent)

                Toast.makeText(
                    this, "Nomes e pontuações inicializados!!",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            } else {
                Toast.makeText(
                    this, "Por favor, preencha os nomes dos jogadores.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("P1_NAME", etP1.text.toString())
        outState.putString("P2_NAME", etP2.text.toString())
    }
}
