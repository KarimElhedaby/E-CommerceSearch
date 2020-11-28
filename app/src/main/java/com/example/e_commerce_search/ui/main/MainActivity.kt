package com.example.e_commerce_search.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.e_commerce_search.app.Common
import com.e_commerce_search.app.replaceFragmentToActivity
import com.example.e_commerce_search.R
import com.example.e_commerce_search.ui.search.SearchResultFragment
import com.example.e_commerce_search.ui.welcome.WelcomeFragment
import com.example.e_commerce_search.utils.MyToast
import com.jakewharton.rxbinding.widget.RxTextView
import kotlinx.android.synthetic.main.main_header.*
import kotlinx.android.synthetic.main.search_border_item.*
import kotlinx.android.synthetic.main.toolbar.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    var firstSearchTime = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        // first welcomed in the landing screen

        replaceFragmentToActivity(
            supportFragmentManager, WelcomeFragment.newInstance(),
            false
        )


        val viewModel: MainViewModel by viewModel()

        /*debounce will only notify you of the text
         change inside the EditText after a certain time
        */
        toolbar.titleTextView.text = "E-Commerce search app"

        RxTextView.textChanges(searchEditText)
            .debounce(2, TimeUnit.SECONDS)
            .subscribe { textChanged ->
                if (textChanged.toString().isNotEmpty())
                    performSearch(textChanged.toString(), viewModel)
            }

        // observe any error msg from view model
        handleErrorMsg(viewModel)


    }

    private fun performSearch(searchKey: String, viewModel: MainViewModel) {

        Common.hideKeyboard(this@MainActivity)

        viewModel.filterData(searchKey = searchKey)

        //that check to initiate view model for first

        if (!firstSearchTime) {

            firstSearchTime = firstSearchTime.not()
            replaceFragmentToActivity(
                supportFragmentManager, SearchResultFragment.newInstance(),
                false
            )

        }

    }

    private fun handleErrorMsg(viewModel: MainViewModel) {
        viewModel.handleErrorMsg().observe(
            this, Observer { errorMsg ->
                MyToast.showError(this@MainActivity, errorMsg)
            })
    }

}