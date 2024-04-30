package com.takzuo.melisearch.presenter

import com.takzuo.melisearch.contract.MainContract
import com.takzuo.melisearch.data.RetrofitServiceFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Clase que implementa la interfaz MainContract.Presenter y maneja la lógica de búsqueda de productos.
 *
 * La vista que implementa la interfaz MainContract.View.
 */
class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {

    // Instancia de RetrofitService para realizar llamadas a la API
    val service = RetrofitServiceFactory.makeRetrofitService()

    /**
     * Método para buscar productos en la API.
     *
     * @param query La consulta de búsqueda.
     */
    override fun searchProducts(query: String) {
        // Se utiliza un CoroutineScope para manejar la operación de búsqueda de forma asíncrona
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Se utiliza un CoroutineScope para manejar la operación de búsqueda de forma asíncrona
                val products = service.listProductsMeli(query)

                // Actualizar la vista con los productos obtenidos
                withContext(Dispatchers.Main) {
                    view.showProducts(products.results)
                }
            } catch (e: Exception) {
                // Manejar cualquier error y mostrar un mensaje de error en la vista
                withContext(Dispatchers.Main) {
                    view.showError("Error al buscar productos: ${e.message}")
                }
            }
        }
    }
}
