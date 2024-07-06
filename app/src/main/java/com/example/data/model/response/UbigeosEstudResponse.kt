package com.example.data.model.response

data class UbigeosEstudResponse(
    val data: UbigeosResponse,
    val message: String,
    val success: Boolean
)


data class UbigeosResponse(
    val ubigeos: List<Ubigeo>
)

data class Ubigeo(
    val departamento: String,
    val distrito: String,
    val estudiante: String,
    val fecha: String,
    val id_pers: Int,
    val id_test_res: Int,
    val id_ubi: Int,
    val id_usu: Int,
    val nivel: String,
    val provincia: String,
    val tipo: String,
    val x: String,
    val y: String
)