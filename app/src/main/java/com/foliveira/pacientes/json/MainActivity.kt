package com.foliveira.pacientes.json

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.IOException


class MainActivity : AppCompatActivity() {
    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.rv_contatos) }
    private val fab by lazy { findViewById<FloatingActionButton>(R.id.fab_add) }
    private val btSalvar by lazy { findViewById<MaterialButton>(R.id.bt_salvar) }
    private val adapter = ContatosAdapter()

    private val viewModel = MainActivityViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupRecyclerView()
        setupFab()
        setupSalvar()
    }

    private fun setupSalvar() {
        btSalvar.setOnClickListener {
            try {
                val contatos = adapter.getContatos()
                viewModel.escreverContatosEmJSON(contatos, filesDir)
                Toast.makeText(this, "Contatos salvos com sucesso!", Toast.LENGTH_SHORT).show()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun setupFab() {
        fab.setOnClickListener {
            val contatoAleatorio = viewModel.getContatoAleatorio()
            adapter.setContato(contatoAleatorio)
        }
    }

    private fun setupRecyclerView() {
        recyclerView.adapter = adapter
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        try {
            val contato = viewModel.getContatosFromJSON(filesDir)

            contato.forEach { contato ->
                adapter.setContato(contato)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}