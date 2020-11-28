package com.e_commerce_search.app.data.api.retrofit

import com.e_commerce_search.app.data.ui_models.ProductResponse
import io.reactivex.Observable
import retrofit2.http.*

interface ApiHelperInterface {
    /**
     *  Put  retrofit calls here
     */

    @GET("products")
    fun getProducts(
        @Query("search") searchKey: String
    ): Observable<MutableList<ProductResponse>>

}