package br.com.silvestresantiago732.motivacionaldia.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.silvestresantiago732.motivacionaldia.data.Frase

class MainViewModel: ViewModel() {

    // LiveData para a frase. UI irá observar isso.
    // _frase é versão mutável, privada ao ViewModel.
    private val _frase = MutableLiveData<Frase>()
    // frase é a versão pública e imutável (somente leitura) para a UI.
    val frase: LiveData<Frase> = _frase

    fun sortearNovaFrase(frases: Array<String>, autores: Array<String>) {
        if (frases.isNotEmpty() && autores.isNotEmpty() && frases.size == autores.size ) {
            val indice = frases.indices.random()
            val fraseSorteada = frases[indice]
            val autorSorteado = autores[indice]

            // Atualiza a valor do LiveData. Qualquer observador será notificado
            _frase.value = Frase(fraseSorteada, autorSorteado)
        }
    }

}