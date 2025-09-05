package br.com.silvestresantiago732.motivacionaldia

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.silvestresantiago732.motivacionaldia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val frases by lazy { resources.getStringArray(R.array.frases_motivacionais) }
    private val autores by lazy { resources.getStringArray(R.array.autores_motivacionais) }

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.buttonSortear.setOnClickListener {
            sortearFrase()
        }

    }

    private fun sortearFrase() {
        val indice = frases.indices.random()
        val fraseSorteada = frases[indice]
        val autorSorteado = autores[indice]

        binding.textMensagemDia.text = fraseSorteada
        binding.textMensagemDiaAutor.text = autorSorteado
    }
}