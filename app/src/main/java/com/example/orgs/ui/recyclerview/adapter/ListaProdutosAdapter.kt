package com.example.orgs.ui.recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.orgs.R
import com.example.orgs.model.Produto

class ListaProdutosAdapter(
    private val produtos: List<Produto>
) : RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nome: TextView? = itemView.findViewById(R.id.produtoNome)
        val descricao: TextView? = itemView.findViewById(R.id.produtoDescricao)
        val valor: TextView? = itemView.findViewById(R.id.produtoValor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.produto_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = produtos[position]
        holder.nome?.text = produto.nome
        holder.descricao?.text = produto.descricao
        holder.valor?.text = String.format(produto.valor.toString())
    }

    override fun getItemCount(): Int = produtos.size

}
