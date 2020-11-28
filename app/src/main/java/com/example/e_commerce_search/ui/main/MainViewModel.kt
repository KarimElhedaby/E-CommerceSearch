package com.example.e_commerce_search.ui.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.e_commerce_search.app.data.DataManager
import com.e_commerce_search.app.data.ui_models.ProductResponse
import com.e_commerce_search.app.utils.inteceptor.ConnectivityStatus
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject


class MainViewModel(
    val dataManager: DataManager
) : ViewModel(), KoinComponent {

    var disposable: Disposable? = null

    private val searchResults: MutableLiveData<MutableList<ProductResponse>> =
        MutableLiveData<MutableList<ProductResponse>>()

    private val msgError: MutableLiveData<String> =
        MutableLiveData<String>()

    val context: Context by inject()

    fun filterData(searchKey: String) {
        if (ConnectivityStatus.isConnected(context)) {
            disposable =
                dataManager.getProducts(searchKey)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                        onNext = {
                            searchResults.value = it
                        },
                        onError = {
                            msgError.value = it.message.toString()
                        })
        } else {
            msgError.postValue("Please connect internet")
        }
    }

    fun getSearchResults(): LiveData<MutableList<ProductResponse>> {
        return searchResults
    }

    fun handleErrorMsg(): LiveData<String> {
        return msgError
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}
