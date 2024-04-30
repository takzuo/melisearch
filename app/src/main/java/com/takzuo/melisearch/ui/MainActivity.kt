package com.takzuo.melisearch.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.takzuo.melisearch.R
import com.takzuo.melisearch.contract.MainContract
import com.takzuo.melisearch.model.Result
import com.takzuo.melisearch.presenter.MainPresenter

/**
 * Actividad principal de la aplicación que muestra una interfaz de usuario para buscar y mostrar productos.
 */
class MainActivity : AppCompatActivity(), MainContract.View, ProductAdapter.OnItemClickListener {

    // Propiedades de clase
    private lateinit var presenter: MainContract.Presenter
    private lateinit var productAdapter: ProductAdapter
    private var productListState: Parcelable? = null
    private lateinit var productList: RecyclerView // Declarar productList como propiedad de clase
    private lateinit var searchEditText: EditText // Declarar searchEditText como propiedad de clase
    private lateinit var alertDialog: AlertDialog // Declarar alertDialog como propiedad de clase

    /**
     * Método que se llama cuando la actividad se está iniciando.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar el presentador
        presenter = MainPresenter(this)

        // Configurar el botón de búsqueda
        val searchButton: Button = findViewById(R.id.search_button)
        searchEditText = findViewById(R.id.search_edit_text)


        // Inicializar productList
        productList = findViewById(R.id.product_list)

        // Configurar el RecyclerView para mostrar productos en dos columnas
        productAdapter = ProductAdapter(emptyList()) // Pasar una lista vacía inicialmente
        productList.adapter = productAdapter
        productList.layoutManager = GridLayoutManager(this, 2)

        // Restaurar el estado del RecyclerView si está disponible
        productListState?.let {
            productList.layoutManager?.onRestoreInstanceState(it)
        }

        searchButton.setOnClickListener {
            val query: String = searchEditText.text.toString()
            presenter.searchProducts(query)
        }

        // Establecer el listener de clic en el adaptador
        productAdapter.setOnItemClickListener(this)
    }

    override fun onItemClick(product: Result) {
        // Mostrar la alerta con la foto del producto y su precio
        showAlert(product)
    }

    private fun showAlert(product: Result) {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.alert_product, null)
        builder.setView(dialogView)

        // Obtener las referencias de los elementos de la vista de la alerta
        val productImageView: ImageView = dialogView.findViewById(R.id.alert_product_image)
        val productNameTextView: TextView = dialogView.findViewById(R.id.product_names)
        val productPriceTextView: TextView = dialogView.findViewById(R.id.alert_product_price)
        val closeButton: Button = dialogView.findViewById(R.id.close_button)


        // Mostrar la imagen del producto utilizando Glide u otra biblioteca similar
        Glide.with(this)
            .load(product.thumbnail)
            .placeholder(R.drawable.logo1) // Imagen de placeholder mientras carga
            .error(R.drawable.logo1) // Imagen de error si falla la carga
            .into(productImageView)


        // Mostrar el nombre del producto
        productNameTextView.text = product.title

        // Mostrar el precio del producto
        productPriceTextView.text = product.price.toString()

        // Crear y mostrar la alerta
        alertDialog = builder.create()
        alertDialog.show()

        closeButton.setOnClickListener {
            alertDialog.dismiss()
        }
    }

    /**
     * Método para mostrar los productos en la interfaz de usuario.
     */
    override fun showProducts(products: List<Result>) {
        // Actualizar el adaptador con la lista de productos
        productAdapter.updateProducts(products)
    }

    /**
     * Método para mostrar un mensaje de error en la interfaz de usuario.
     */
    override fun showError(message: String) {
        // TODO Implementar la lógica para mostrar un mensaje de error en la UI
    }
}
