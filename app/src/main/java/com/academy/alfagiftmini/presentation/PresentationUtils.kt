package com.academy.alfagiftmini.presentation

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.text.Html
import android.text.Spanned
import androidx.core.content.res.ResourcesCompat
import com.academy.alfagiftmini.R
import java.text.DecimalFormat

object PresentationUtils {
    const val SHOW_LIHAT_SEMUA = true
    const val HIDE_LIHAT_SEMUA = false

    const val ORDER_BY_ASCENDING = "asc"
    const val ORDER_BY_DESCENDING = "desc"

    const val TYPE_SEARCH_OFFICIAL = "search_official"
    const val TYPE_GET_ALL_OFFICIAL = "get_all_official"

    const val TYPE_PROMOSI = "promosi"
    const val TYPE_BUKAN_PROMOSI = "bukan_promosi"

    const val TYPE_HARGA_SPESIAL = 201
    const val TYPE_GRATIS_PRODUK = 103
    const val TYPE_PAKET = 901
    const val TYPE_TEBUS_MURAH = 502

    const val INTENT_DATA ="data"


    const val BANNER_ID = "BANNER_ID"
    const val BANNER_DATA = "BANNER_DATA"
    const val ALL_BANNER_LIST = "ALL_BANNER_LIST"

    fun String.fromHtml(): Spanned {
        return Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
    }

    fun hargaFormatter(n: Int): String = DecimalFormat("#,###").format(n)


    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val nw = connectivityManager.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
        return when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            //for other device how are able to connect with Ethernet
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            //for check internet over Bluetooth
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
            else -> false
        }
    }


    private fun setupDialogError(context: Context, msg: String): AlertDialog.Builder {
        return AlertDialog.Builder(context).apply {
            setIcon(
                ResourcesCompat.getDrawable(
                    context.resources, R.drawable.baseline_error_24, null
                )
            )
            setTitle("Error")
            setMessage(msg)
            setCancelable(false)
        }
    }

    fun showError(error: String?, context: Context) {
        setupDialogError(context, error ?: "").setPositiveButton("Ok") { dialog, _ ->
            dialog.dismiss()
        }.show()
    }

    fun setLoading(isLoading: Boolean, dialog: Dialog) {
        if (isLoading) {
            dialog.show()
        } else {
            dialog.dismiss()
        }
    }

    fun loadingAlertDialog(context: Context): AlertDialog {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(context)
        return alertDialog.setView(R.layout.progress).create()
    }
}