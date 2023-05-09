package com.academy.alfagiftmini.presentation.homepage.components.activity.productdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.academy.alfagiftmini.MyApplication
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.ActivityProductDetailBinding
import com.academy.alfagiftmini.domain.productdetail.model.ProductDetailResponseModel
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.PresentationUtils.fromHtml
import com.academy.alfagiftmini.presentation.factory.PresentationFactory
import com.academy.alfagiftmini.presentation.homepage.components.adapter.productdetail.ProductDetailSliderAdapter
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductDetailViewModel
import com.smarteist.autoimageslider.SliderView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailBinding
    private lateinit var adapter: ProductDetailSliderAdapter

    @Inject
    lateinit var presentationFactory: PresentationFactory
    private val viewModel: ProductDetailViewModel by viewModels {
        presentationFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.productDetailActivityInject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar()
        checkInternet()

    }

    private fun setToolbar() {
        binding.bannerItemListToolbar.ivDetailBackToolbar.setOnClickListener {
            finish()
        }
    }

    private fun checkInternet() {
        if (PresentationUtils.isNetworkAvailable(this)) {
            getProductId()
        } else {
            networkDialog()
        }
    }

    private fun getProductData(productId: Int) {
        lifecycleScope.launch {
            viewModel.getProductDetail(productId)
            viewModel.productDetailData.collectLatest {
                setDataPresentation(it)
            }
        }
    }

    private fun getProductId() {
        val productIdIntent = intent.getIntExtra(PresentationUtils.PRODUCT_ID, 0)
        if (productIdIntent != 0) {
            getProductData(productIdIntent)
        }
    }

    private fun setDataPresentation(it: ProductDetailResponseModel) {
        val productData = it.productList[0]
        setImageSlider(productData.productImages[0].url)
        binding.apply {
            when (productData.productPickupAvailability){
                0 -> {
                    tvStokFrom.text = getString(R.string.stok_gudang)
                    tvStokFrom.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.stok_gudang,0,0,0)
                }
                1 -> {
                    tvStokFrom.text = getString(R.string.stok_toko)
                    tvStokFrom.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.stok_toko,0,0,0)
                }
            }
            tvNamaProduct.text = productData.productName
            if (productData.productSpecialPrice < productData.price){
                tvHargaProductSecondary.visibility = View.VISIBLE
                tvDiskonPercentage.visibility = View.VISIBLE
                val besarDiskon = (productData.price - productData.productSpecialPrice/productData.price)*100
                tvDiskonPercentage.text = getString(R.string.besar_diskon,besarDiskon)
                tvHargaProductPrimary.text = PresentationUtils.formatter(productData.productSpecialPrice)
            }else{
                tvHargaProductSecondary.visibility = View.INVISIBLE
                tvDiskonPercentage.visibility = View.INVISIBLE
                tvHargaProductPrimary.text = PresentationUtils.formatter(productData.price)
            }

            tvDeskripsiContent.text = productData.productDesc.fromHtml()

        }

    }

    private fun setImageSlider(imageList: List<String>){
        adapter = ProductDetailSliderAdapter(
            imageList,
            this@ProductDetailActivity
        )
        binding.productImageSlider.autoCycleDirection = SliderView.LAYOUT_DIRECTION_LTR
        binding.productImageSlider.setSliderAdapter(adapter)
        binding.productImageSlider.scrollTimeInSec = 3
        binding.productImageSlider.startAutoCycle()
    }

    private fun networkDialog() {
        val dialogBuilder = AlertDialog.Builder(this, R.style.NetworkAlertDialogTheme)
        dialogBuilder.setMessage("No Network Connection detected, Please make sure you have a stable connection to the internet, then press retry to refresh the app and try again.")
        dialogBuilder.setCancelable(false)
        dialogBuilder.setIcon(R.drawable.no_internet_logo)
        dialogBuilder.setTitle("No Network Connection")
        dialogBuilder.setPositiveButton("RETRY") { _, _ ->
            checkInternet()
        }
        dialogBuilder.setNegativeButton("CLOSE") { dialog, _ ->
            dialog.cancel()
        }
        val connectionAlertDialog = dialogBuilder.create()
        connectionAlertDialog.window?.setBackgroundDrawableResource(R.drawable.connection_dialog_background)
        connectionAlertDialog.show()
    }
}