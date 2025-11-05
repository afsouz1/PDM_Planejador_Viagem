package br.com.pdm_semana_8


import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class DestinoActivity : AppCompatActivity() {

    private lateinit var textViewDestino: TextView
    private lateinit var buttonMapa: Button
    private lateinit var buttonVoos: Button
    private lateinit var buttonFotos: Button
    private lateinit var buttonPartilhar: Button

    private var destino: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destino)

        // Receber dados da MainActivity
        destino = intent.getStringExtra("DESTINO") ?: "Destino não especificado"

        initViews()
        setupClickListeners()
    }

    private fun initViews() {
        textViewDestino = findViewById(R.id.textViewDestino)
        buttonMapa = findViewById(R.id.buttonMapa)
        buttonVoos = findViewById(R.id.buttonVoos)
        buttonFotos = findViewById(R.id.buttonFotos)
        buttonPartilhar = findViewById(R.id.buttonPartilhar)

        textViewDestino.text = destino
    }

    private fun setupClickListeners() {
        // Ver no Mapa
        buttonMapa.setOnClickListener {
            val uri = Uri.parse("geo:0,0?q=${Uri.encode(destino)}")
            val mapIntent = Intent(Intent.ACTION_VIEW, uri)

            if (mapIntent.resolveActivity(packageManager) != null) {
                startActivity(mapIntent)
            } else {
                Toast.makeText(this, "Nenhum aplicativo de mapa encontrado", Toast.LENGTH_SHORT).show()
            }
        }

        // Pesquisar Voos
        buttonVoos.setOnClickListener {
            val query = "voos+para+${destino.replace(" ", "+")}"
            val uri = Uri.parse("https://www.google.com/search?q=$query")
            val browserIntent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(browserIntent)
        }

        // Ver Fotos
        buttonFotos.setOnClickListener {
            val query = "${destino.replace(" ", "+")}"
            val uri = Uri.parse("https://www.google.com/search?q=$query&tbm=isch")
            val browserIntent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(browserIntent)
        }

        // Partilhar Destino
        buttonPartilhar.setOnClickListener {
            val shareText = "Estou a planear uma viagem para $destino!"
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, shareText)
            }

            val chooser = Intent.createChooser(shareIntent, "Partilhar destino via")
            if (shareIntent.resolveActivity(packageManager) != null) {
                startActivity(chooser)
            } else {
                Toast.makeText(this, "Nenhum aplicativo de partilha encontrado", Toast.LENGTH_SHORT).show()
            }
        }
    }
}