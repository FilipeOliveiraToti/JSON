package com.foliveira.pacientes.json

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.FileReader
import java.io.FileWriter

class MainActivityViewModel {

    fun getContatosFromJSON(filesDir: File): List<Contato> {
        val file = File(filesDir, "contatos.json")
        val reader = FileReader(file)
        val gson = Gson()
        val listType = object : TypeToken<List<Contato>>() {}.type
        val contatos: List<Contato> = gson.fromJson(reader, listType)

        return contatos
    }

    fun escreverContatosEmJSON(contatos: List<Contato>, filesDir: File) {
        val json = Gson().toJson(contatos)

        FileWriter("$filesDir/contatos.json").use { fileWriter ->
            fileWriter.write(json)
        }
    }

    fun getContatoAleatorio() : Contato{

        val contato = Contato(
            nome = nomes.random(),
            idade = idades.random(),
            telefones = listOf(
                telefones.random(),
                telefones.random()
            ),
            endereco = Endereco(
                rua = ruas.random(),
                numero = numeroDaCasa.random(),
                bairro = bairros.random(),
                cidade = cidades.random()
            )
        )

        return contato
    }

    private val nomes = listOf("João Souza", "Maria Silva", "Pedro Santos", "Ana Oliveira", "Lucas Pereira", "Filipe Oliveira", "João Silva", "Maria Souza", "Pedro Oliveira", "Ana Pereira")

    private val idades = listOf(25, 30, 35, 40, 45, 50, 55, 60, 65, 70)

    private val telefones = listOf("21 999999999", "21 888888888", "11 777777777", "11 666666666", "11 555555555", "50 444444444", "50 333333333", "50 222222222", "50 111111111")

    private val ruas = listOf("Rua da Árvore", "Estrada do Pássaro", "Avenida do Contorno", "Avenida Brasil", "Travessa da Felicidade", "")

    private val numeroDaCasa = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    private val bairros = listOf("Centro", "Vila Nova", "Jardim Botânico", "Ramos", "Botafogo", "Penha", "Lapa", "Copacabana", "Ipanema", "Leblon")

    private val cidades = listOf("Rio de Janeiro", "São Paulo", "Belo Horizonte", "Porto Alegre", "Curitiba", "Salvador", "Fortaleza", "Manaus", "Recife", "Brasília")
}