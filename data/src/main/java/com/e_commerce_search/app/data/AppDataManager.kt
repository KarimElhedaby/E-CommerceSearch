package com.e_commerce_search.app.data

import com.e_commerce_search.app.data.api.retrofit.ApiHelperInterface
import com.e_commerce_search.app.data.ui_models.ProductResponse
import io.reactivex.Observable


class AppDataManager(var service: ApiHelperInterface) : DataManager {


    override fun getProducts(searchKey: String): Observable<MutableList<ProductResponse>> {
            return service.getProducts(searchKey)
    }

}