package com.marcioposgraduacao.trucogamemarker

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class PlayerNamesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val etP1 = findViewById<android.widget.EditText>(R.id.etPlayer1Name)
        val etP2 = findViewById<android.widget.EditText>(R.id.etPlayer2Name)
        val btnConfirm = findViewById<android.widget.Button>(R.id.btnConfirm)

        btnConfirm.setOnClickListener {

            val newName1 = etP1.text.toString()
            val newName2 = etP2.text.toString()


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
}