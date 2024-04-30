package com.takzuo.melisearch.data

import com.takzuo.melisearch.model.RemoteResult
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("search")
    suspend fun listProductsMeli(
        @Query("q") type: String,
    ): RemoteResult
}
