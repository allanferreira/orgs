package com.example.orgs.dao

import com.example.orgs.model.Produto
import java.math.BigDecimal

class ProdutosDAO {

    companion object {
        var produtos = listOf<Produto>()
            private set

        fun add(produto: Produto) {
            produtos = produtos + listOf(produto)
        }

        fun fetch() {
            produtos = listOf(
                Produto("Muxus, Goblin Gardioso", "Jumpstart", BigDecimal("119.99")),
                Produto("Krenko, Chefe da Turba", "Jumpstart", BigDecimal("13.25")),
                Produto("Elfos de Llanowar", "Core Set 2021", BigDecimal("0.20")),
                Produto("Brasolâmina", "Trono de Eldraine", BigDecimal("28.00")),
                Produto("Lurrus da Toca Onírica", "Ikoria: Terra de Colossos", BigDecimal("49.99")),
            )
        }
    }

}