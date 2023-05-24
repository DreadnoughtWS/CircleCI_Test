package com.academy.alfagiftmini.presentation

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.text.Html
import android.text.Spanned
import androidx.core.content.res.ResourcesCompat
import androidx.paging.LoadState
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.presentation.homepage.components.adapter.productlist.ProductListGratisProductPagingAdapter
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

object PresentationUtils {
    val EMAIL_REGEX = "^[\\w-]+@([\\w-]+\\.)+[\\w-]{2,4}$".toRegex()

    const val COUNTRY_PHONE_CODE = "+62"

    const val SP_ACC_TOKEN = "acc_token"

    const val TYPE_PRODUCT_NAME = "TYPE_PRODUCT_NAME"
    const val TYPE_PRODUCT_NAME_LIKE = "TYPE_PRODUCT_NAME_LIKE"

    const val SHOW_LIHAT_SEMUA = true
    const val HIDE_LIHAT_SEMUA = false

    const val ORDER_BY_ASCENDING = "asc"
    const val ORDER_BY_DESCENDING = "desc"

    const val TYPE_SEARCH_OFFICIAL = "search_official"
    const val TYPE_GET_ALL_OFFICIAL = "get_all_official"

    const val TYPE_PROMOSI = "promosi"
    const val TYPE_BUKAN_PROMOSI = "bukan_promosi"

    const val TYPE_HARGA_SPESIAL = 201
    const val TYPE_HARGA_SPESIAL_PROMOSI = 202
    const val TYPE_GRATIS_PRODUK = 103
    const val TYPE_PAKET = 901
    const val TYPE_PAKET_PROMOSI = 902
    const val TYPE_TEBUS_MURAH = 502

    const val TYPE_SHOPPING_LIST_BELANJA = 10
    const val TYPE_REKOMENDASI_BELANJA = 11
    const val TYPE_PENAWARAN_TERBAIK = 12
    const val TYPE_PENAWARAN_TERBAIK_PROMOSI = 13

    const val INTENT_DATA = "data"
    const val PRODUCT_ID = "PRODUCT_ID"

    const val PRODUCT_ID_PROMO = "PRODUCT_ID_PROMO"
    const val BANNER_DATA = "BANNER_DATA"
    const val BANNER_RULE = "BANNER_RULE"
    const val ALL_BANNER_LIST = "ALL_BANNER_LIST"

    const val CATEGORIES_KEY = "PRODUCT_CATEGORY"

    const val SHARED_PREFERENCE = "USER_DATA"
    const val SP_USER_ID = "USER_ID"
    const val SP_FIRST_NAME = "FIRST_NAME"
    const val SP_LAST_NAME = "LAST_NAME"
    const val SP_PHONE = "USER_PHONE"
    const val SP_PASS = "PASSWORD"

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

    fun adapterAddLoadStateListenerProductt(
        adapter: ProductListGratisProductPagingAdapter, dialog: Dialog, context: Context
    ) {
        adapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading) {
                setLoading(true, dialog)
            } else {
                setLoading(false, dialog)
            }
            if (loadState.refresh is LoadState.Error) {
                setLoading(false, dialog)
                if (!isNetworkAvailable(context)) {
                    showError("Tidak ada koneksi internet", context)
                } else {
                    showError("Product tidak ditemukan", context)
                }
            }

            if (loadState.append is LoadState.Error) {
                setLoading(false, dialog)
                if (!isNetworkAvailable(context)) showError(
                    "Tidak ada koneksi internet", context
                )
            }
        }
    }

    fun adapterAddLoadStateListenerProduct(
        adapter: ProductListGratisProductPagingAdapter,
        dialog: Dialog,
        context: Context,
        function: ()->Unit
    ) {
        adapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading) {
                setLoading(true, dialog)
            } else {
                setLoading(false, dialog)
            }
            if (loadState.refresh is LoadState.Error) {
                setLoading(false, dialog)
                if (!isNetworkAvailable(context)) {
                    val dialogg = noInternetDialog(context)
                    dialogg.setPositiveButton("RETRY") { _, _ ->
                        function()
                    }
                    dialogg.setNegativeButton("CLOSE") { dialog, _ ->
                        dialog.cancel()
                    }
                    shownoInternetDialog(dialogg)
                } else {
                    showError("Product tidak ditemukan", context)
                }
            }

            if (loadState.append is LoadState.Error) {
                setLoading(false, dialog)
                if (!isNetworkAvailable(context)) {
                    val dialogg = noInternetDialog(context)
                    dialogg.setPositiveButton("RETRY") { _, _ ->
                        function()
                    }
                    dialogg.setNegativeButton("CLOSE") { dialog, _ ->
                        dialog.cancel()
                    }
                    shownoInternetDialog(dialogg)
                }
            }
        }
    }

    fun noInternetDialog(context: Context): androidx.appcompat.app.AlertDialog.Builder {
        val dialogBuilder =
            androidx.appcompat.app.AlertDialog.Builder(context, R.style.NetworkAlertDialogTheme)
        dialogBuilder.setMessage("No Network Connection detected, Please make sure you have a stable connection to the internet, then press retry to refresh the app and try again.")
        dialogBuilder.setCancelable(false)
        dialogBuilder.setIcon(R.drawable.no_internet_logo)
        dialogBuilder.setTitle("No Network Connection")
        return dialogBuilder


    }

    fun shownoInternetDialog(dialogBuilder: androidx.appcompat.app.AlertDialog.Builder) {
        val connectionAlertDialog = dialogBuilder.create()
        connectionAlertDialog.window?.setBackgroundDrawableResource(R.drawable.connection_dialog_background)
        connectionAlertDialog.show()
    }
}