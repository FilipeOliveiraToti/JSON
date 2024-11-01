package com.foliveira.pacientes.json

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContatosAdapter : RecyclerView.Adapter<ContatosAdapter.ViewHolder>() {

    private var listaContatos = mutableListOf<Contato>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view, parent, false)
        )
    }

    override fun getItemCount() = listaContatos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contato = listaContatos[position]

        holder.bind(contato)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(contato: Contato) {

            itemView.apply {
                val tv_nome = findViewById<TextView>(R.id.tv_nome)
                val tv_idade = findViewById<TextView>(R.id.tv_idade)
                val tv_telefones = findViewById<TextView>(R.id.tv_telefones)
                val tv_endereco = findViewById<TextView>(R.id.tv_endereco)

                val endereco = contato.endereco
                tv_endereco.text = "${endereco.rua}, ${endereco.numero} - ${endereco.bairro}, ${endereco.cidade}"
                tv_telefones.text = "${contato.telefones[0]} - ${contato.telefones[1]}"
                tv_nome.text = contato.nome
                tv_idade.text = "${contato.idade} anos"
            }
        }
    }

    fun setContato(contato: Contato) {
        listaContatos.add(contato)
        notifyItemInserted(itemCount)
    }

    fun getContatos() = listaContatos


}