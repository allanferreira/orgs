package com.example.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.orgs.R
import com.example.orgs.dao.ProdutosDAO
import com.example.orgs.ui.recyclerview.adapter.ListaProdutosAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListaProdutosActivity : AppCompatActivity(R.layout.activity_lista_produtos) {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val floatingActionButton = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        floatingActionButton.setOnClickListener { goToProductFormScreen() }

        ProdutosDAO.fetch()
        recyclerView = findViewById(R.id.recyclerView)
    }

    override fun onResume() {
        super.onResume()

        recyclerView.adapter = ListaProdutosAdapter(ProdutosDAO.produtos)
    }

    private fun goToProductFormScreen() {
        val intent = Intent(this, FormularioProdutoActivity::class.java)
        startActivity(intent)
    }
}