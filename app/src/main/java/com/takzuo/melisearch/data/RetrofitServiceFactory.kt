package com.takzuo.melisearch.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Objeto singleton que se encarga de crear una instancia de RetrofitService.
 */
object RetrofitServiceFactory {
    // URL base para la API de MercadoLibre
    private const val BASE_URL = "https://api.mercadolibre.com/sites/MLA/"

    /**
     * Método para crear una instancia de RetrofitService.
     *
     * @return Una instancia de RetrofitService configurada para consumir la API de MercadoLibre.
     */
    fun makeRetrofitService(): RetrofitService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL) // Configurar la URL base
            .addConverterFactory(GsonConverterFactory.create()) // Usar Gson para la conversión de JSON
            .build()
            .create(RetrofitService::class.java) // Crear la instancia de RetrofitService
    }
}
