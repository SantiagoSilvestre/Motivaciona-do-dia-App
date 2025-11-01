package br.com.silvestresantiago732.motivacionaldia

import android.content.pm.PackageInfo
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.silvestresantiago732.motivacionaldia.databinding.ActivityMainBinding
import br.com.silvestresantiago732.motivacionaldia.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val frases by lazy { resources.getStringArray(R.array.frases_motivacionais) }
    private val autores by lazy { resources.getStringArray(R.array.autores_motivacionais) }

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // --- CÓDIGO PARA PEGAR O VERSIONNAME ---
        try {
            val packageInfo: PackageInfo = packageManager.getPackageInfo(packageName, 0)
            val versaoApp = packageInfo.versionName
            binding.txtVersao.text = getString(R.string.version_name, versaoApp) // Ex: "Versão 1.1.0"
        } catch (e: Exception) {
            // Caso ocorra um erro ao buscar a versão, exibe um texto padrão
            binding.txtVersao.text = getString(R.string.versao_nao_disponivel)
        }
        // ----------------------------------------


        // functions
        listeners()
        observe()
    }

    private fun listeners() {
        binding.buttonSortear.setOnClickListener {
            mainViewModel.sortearNovaFrase(frases, autores)
        }
    }

    private fun observe() {
        mainViewModel.frase.observe(this) { fraseData ->
            binding.textMensagemDia.text = fraseData.texto
            binding.textMensagemDiaAutor.text = fraseData.author
        }
    }
}