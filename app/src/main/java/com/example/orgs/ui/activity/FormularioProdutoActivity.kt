package com.example.orgs.ui.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.orgs.R
import com.example.orgs.dao.ProdutosDAO
import com.example.orgs.model.Produto
import kotlinx.coroutines.*
import java.math.BigDecimal

@DelicateCoroutinesApi
class FormularioProdutoActivity : AppCompatActivity(R.layout.activity_formulario_produto) {

    private lateinit var nomeView: EditText
    private lateinit var descricaoView: EditText
    private lateinit var valorView: EditText
    private lateinit var produto: Produto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val button = findViewById<Button>(R.id.formProdutoButton)
        button.setOnClickListener { onSubmit() }

        nomeView = findViewById(R.id.campoProdutoNome)
        descricaoView = findViewById(R.id.campoProdutoDescricao)
        valorView = findViewById(R.id.campoProdutoValor)
    }

    private fun onSubmit() {
        try {
            validation()
            ProdutosDAO.add(produto)
            cleanForm()
            finish()
            runBlocking { showSuccessMessage() }
        } catch (e: ValidationError) {
            e.view.error = e.message
        }
    }

    private fun validation() {
        cleanErrors()

        val nome = nomeView.text.toString()
        val descricao = descricaoView.text.toString()
        val valorText = valorView.text.toString()
        val valor = if (valorText.isBlank()) BigDecimal.ZERO else BigDecimal(valorText)

        if(nome.isBlank()) throw ValidationError(nomeView, "Preencha o campo nome")
        if(descricao.isBlank()) throw ValidationError(descricaoView, "Preencha o campo descrição")
        if(valor == BigDecimal.ZERO) throw ValidationError(valorView, "Preencha o campo valor")

        produto = Produto(nome, descricao, valor)
    }

    private fun cleanForm() {
        nomeView.text = null
        descricaoView.text = null
        valorView.text = null
    }

    private fun cleanErrors() {
        nomeView.error = null
        descricaoView.error = null
        valorView.error = null
    }

    private suspend fun showSuccessMessage() {
        val toast = Toast.makeText(this, "${produto.nome} adicionado", Toast.LENGTH_LONG)

        GlobalScope.launch {
            delay(1000L)
            toast.show()
        }
    }

    class ValidationError(val view: EditText, override val message: String) : Throwable()
}