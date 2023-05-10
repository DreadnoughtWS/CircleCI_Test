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
import java.text.DecimalFormatSymbols
import java.util.*

object PresentationUtils {
    val EMAIL_REGEX = "^[\\w-]+@([\\w-]+\\.)+[\\w-]{2,4}$".toRegex()

    const val COUNTRY_PHONE_CODE = "+62"

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

    const val TYPE_SHOPPING_LIST_BELANJA = 10
    const val TYPE_REKOMENDASI_BELANJA = 11
    const val TYPE_PENAWARAN_TERBAIK = 12

    const val INTENT_DATA ="data"
    const val PRODUCT_ID ="PRODUCT_ID"

    const val BANNER_ID = "BANNER_ID"
    const val BANNER_DATA = "BANNER_DATA"
    const val ALL_BANNER_LIST = "ALL_BANNER_LIST"

    const val CATEGORIES_KEY = "PRODUCT_CATEGORY"

    const val SHARED_PREFERENCE = "USER_DATA"
    const val SP_USER_ID = "USER_ID"
    const val SP_FIRST_NAME = "FIRST_NAME"
    const val SP_LAST_NAME = "LAST_NAME"
    const val SP_PHONE = "USER_PHONE"

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

    fun formatter(n: Int): String =
        DecimalFormat("Rp #,###", DecimalFormatSymbols(Locale.GERMANY)).format(n)
}