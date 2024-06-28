package com.example.data.model.response


/*
* Para cada response JSON del backend le corresponde un archivo JSON
* por ejemplo del backend se recibe con obtenerTest, la siguiente
* estructura JSON:
*
* {
	"data": {
		"preguntas": [
			{
				"descripcion": "texto",
				"id_preg": 1000,
				"puntaje_maximo": 4,
				"puntaje_minimo": 1
			},
			{
			.
			.
			.
			}
		"test": {
			"descripcion": "texto",
			"id_test": 100,
			"recomendacion": "texto",
			"tipo": "Test de Ansiedad de Zung"
		}
	"message": "COMPLETE",
	"success": true
}
*
* Tendremos una estructura que responde a esa estructura en un solo archivo
*
* */
data class TestSingleResponse(
    val data: TestDataResponse,
    val message: String,
    val success: Boolean
)

data class TestDataResponse(
    val preguntas: List<PreguntaResponse>,
    val test: TestResponse
)

data class PreguntaResponse(
    val descripcion: String,
    val id_preg: Int,
    val puntaje_maximo: Int,
    val puntaje_minimo: Int
)