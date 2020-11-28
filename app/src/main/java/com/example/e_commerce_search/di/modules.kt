package com.example.e_commerce_search.di


import com.example.e_commerce_search.ui.main.MainViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel
val mainModule = module {

    viewModel { MainViewModel(get()) }
}