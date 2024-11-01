package com.foliveira.pacientes.json

data class Contato(
    val nome: String,
    val idade: Int,
    val telefones: List<String>,
    val endereco: Endereco
)
