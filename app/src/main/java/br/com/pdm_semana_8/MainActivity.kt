package br.com.pdm_semana_8

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private lateinit var editTextDestino: EditText
    private lateinit var buttonPlanejar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextDestino = findViewById(R.id.editTextDestino)
        buttonPlanejar = findViewById(R.id.buttonPlanejar)

        buttonPlanejar.setOnClickListener {
            val destino = editTextDestino.text.toString().trim()

            if (destino.isNotEmpty()) {

                val intent = Intent(this, DestinoActivity::class.java).apply {
                    putExtra("DESTINO", destino)
                }
                startActivity(intent)
            } else {
                editTextDestino.error = "Por favor, digite um destino"
            }
        }
    }
}