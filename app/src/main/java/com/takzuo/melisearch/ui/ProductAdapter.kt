package com.takzuo.melisearch.ui

import com.takzuo.melisearch.R

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.takzuo.melisearch.model.Result
import java.net.URL

/**
 * Adaptador para mostrar productos en un RecyclerView.
 *
 * @property products La lista de productos a mostrar.
 */
class ProductAdapter(private var products: List<Result>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    /**
     * Método que se llama cuando se necesita crear un nuevo ViewHolder.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(itemView)
    }

    /**
     * Método que se llama para asociar los datos de un producto con un ViewHolder.
     */
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentItem = products[position]
        holder.bind(currentItem)

        // Agregar listener de clic al elemento
        holder.itemView.setOnClickListener {
            onItemClick(currentItem)
        }
    }

    private fun onItemClick(product: Result) {
        itemClickListener?.onItemClick(product)
    }

    // Interfaz para manejar el clic en un elemento
    interface OnItemClickListener {
        fun onItemClick(product: Result)
    }

    // Propiedad para almacenar el listener
    private var itemClickListener: OnItemClickListener? = null

    // Método para establecer el listener
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.itemClickListener = listener
    }

    /**
     * Método que devuelve el número total de elementos en la lista de productos.
     */
    override fun getItemCount(): Int {
        return products.size
    }

    /**
     * Método para actualizar la lista de productos y notificar al adaptador sobre el cambio.
     */
    fun updateProducts(newProducts: List<Result>) {
        products = newProducts
        notifyDataSetChanged()
    }

    /**
     * ViewHolder para mostrar los datos de un producto.
     */
    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val productNameTextView: TextView = itemView.findViewById(R.id.product_name)
        private val productPriceTextView: TextView = itemView.findViewById(R.id.product_price)
        private val productPhotoImageView: ImageView = itemView.findViewById(R.id.product_photo)

        /**
         * Método para asociar los datos de un producto con los elementos de la vista.
         */
        fun bind(product: Result) {
            productNameTextView.text = product.title
            productPriceTextView.text = product.price.toString()

            try {
                val imageUrl = URL(product.thumbnail)
                Glide.with(itemView)
                    .load(imageUrl)
                    .placeholder(R.drawable.eee1) // Imagen de placeholder mientras carga
                    .error(R.drawable.eee1) // Imagen de error si falla la carga
                    .into(productPhotoImageView)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
