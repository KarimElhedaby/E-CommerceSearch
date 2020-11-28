package com.e_commerce_search.app.utils.inteceptor

import android.content.Context
import android.net.ConnectivityManager

class ConnectivityStatus {
    companion object {

        fun isConnected(context: Context): Boolean {

            val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val connection = manager.activeNetworkInfo
            return connection != null && connection.isConnectedOrConnecting
        }
    }
}