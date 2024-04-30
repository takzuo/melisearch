package com.takzuo.melisearch.contract

import com.takzuo.melisearch.model.Result

interface MainContract {
    interface View {
        fun showProducts(products: List<Result>)
        fun showError(message: String)
    }

    interface Presenter {
        fun searchProducts(query: String)
    }
}
