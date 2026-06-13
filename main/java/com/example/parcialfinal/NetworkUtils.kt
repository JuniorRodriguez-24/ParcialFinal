package com.example.parcialfinal

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

// Función que pide el JSON y devuelve el resultado a la pantalla
fun obtenerMensajeDelDia(context: Context, alObtenerMensaje: (String) -> Unit) {
    val queue = Volley.newRequestQueue(context)

    // URL Real para pruebas (devuelve un JSON con una frase aleatoria)
    val urlReal = "https://dummyjson.com/quotes/random"

    // URL Falsa (Ejemplo de cómo se vería una URL propia de una clínica)
    // val urlFalsa = "https://clinica-san-jose.com/api/consejo_diario.json"

    // 2. Realizar petición para un archivo JSON (Temas 4.5 y 4.6)
    val jsonObjectRequest = JsonObjectRequest(
        Request.Method.GET, urlReal, null,
        { response ->
            // Si es exitosa, leemos la variable "quote" del JSON de dummyjson.com
            try {
                val mensaje = response.getString("quote")
                alObtenerMensaje(mensaje)
            } catch (e: Exception) {
                alObtenerMensaje("Error leyendo el mensaje del servidor.")
            }
        },
        { error ->
            // Si no hay internet, mostramos un mensaje por defecto
            alObtenerMensaje("Recuerda cuidar tu salud todos los días.")
        }
    )
    // 3. Añadimos la petición a la cola para que se ejecute
    queue.add(jsonObjectRequest)
}