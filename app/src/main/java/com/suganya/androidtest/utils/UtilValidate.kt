package com.suganya.androidtest.utils

import android.content.Context
import android.net.ConnectivityManager

object UtilValidate {


    fun isConnectivity(context: Context): Boolean {
        try {
            val connectivity = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (connectivity != null) {
                val info = connectivity.activeNetworkInfo
                if (info != null) {

                    return true
                }
            }
        } catch (e: Exception) {

        }

        return false
    }


}
