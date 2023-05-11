package com.academy.alfagiftmini.presentation.homepage.components.activity.productdetail

import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.academy.alfagiftmini.MyApplication
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.ActivityProductDetailBinding
import com.academy.alfagiftmini.domain.productdetail.model.ProductDetailDomainModel
import com.academy.alfagiftmini.domain.productdetail.model.ProductPromosi103DomainModel
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.PresentationUtils.fromHtml
import com.academy.alfagiftmini.presentation.factory.PresentationFactory
import com.academy.alfagiftmini.presentation.homepage.components.adapter.productdetail.ProductDetailSliderAdapter
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductDetailViewModel
import com.bumptech.glide.Glide
import com.smarteist.autoimageslider.SliderView
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import javax.inject.Inject

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailBinding
    private lateinit var adapter: ProductDetailSliderAdapter
    private lateinit var progressBar:AlertDialog
    private var productId: Long? = null
    private var sizePromo: Int? = null

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
        setProgressBar()
        checkInternet()
    }

    private fun setProgressBar() {
        progressBar = AlertDialog.Builder(this, R.style.NetworkAlertDialogTheme).setCancelable(false).setView(R.layout.progress).create()
    }

    private fun setToolbar() {
        binding.bannerItemListToolbar.ivDetailBackToolbar.setOnClickListener {
            finish()
        }
    }

    private fun checkInternet() {
       progressBar.show()
        if (PresentationUtils.isNetworkAvailable(this)) {
            getProductId()
            if (productId != 0L) {
                getProductData(productId!!)
            }
        } else {
            networkDialog()
        }
    }

    private fun getProductData(productId: Long) {
        lifecycleScope.launch {
            viewModel.getProductDetail(productId)
            viewModel.productDetailData.collectLatest {
                setDataPresentation(it)
            }
        }
    }

    private fun getProductGratis(productId:Long) {
        lifecycleScope.launch {
            viewModel.getProductPromoGratis(productId)
            viewModel.productGratisData.collectLatest {
                getJumlahGratis(it[0].promoProductId)
            }
        }
    }

    private fun getJumlahGratis(it: List<Long>){
        sizePromo = it.size
        if (sizePromo != null){
            if (sizePromo!! >1){
                binding.tvProdukGratisPlus.visibility = View.VISIBLE
                binding.tvProdukGratisPlus.text = getString(R.string.plus_pro,sizePromo!!-1)
            }else{
                binding.tvProdukGratisPlus.visibility = View.INVISIBLE
            }
        }

    }

    private fun getProductId() {
        productId = intent.getLongExtra(PresentationUtils.PRODUCT_ID, 0L)
    }

    private fun setDataPresentation(it: List<ProductDetailDomainModel>) {
        val productData = it[0]
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
            tvHargaProductSecondary.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            if (productData.productSpecialPrice < productData.price){
                tvHargaProductSecondary.visibility = View.VISIBLE
                tvDiskonPercentage.visibility = View.VISIBLE
                val besarDiskon = (((productData.price - productData.productSpecialPrice)/productData.price.toDouble())*100).toInt()
                tvDiskonPercentage.text = getString(R.string.besar_diskon,besarDiskon)
                tvHargaProductSecondary.text = PresentationUtils.formatter(productData.price)
                tvHargaProductPrimary.text = PresentationUtils.formatter(productData.productSpecialPrice)
            }else{
                tvHargaProductSecondary.visibility = View.INVISIBLE
                tvDiskonPercentage.visibility = View.INVISIBLE
                tvHargaProductPrimary.text = PresentationUtils.formatter(productData.price)
            }

            tvDeskripsiContent.text = productData.productDesc.fromHtml()
            val promoGratisBarang = productData.kodePromo.filter {
                it == 103
            }
            if (promoGratisBarang.isNotEmpty()){

                setPromoProductVisibility(true)

                Glide.with(this@ProductDetailActivity)
                    .load(productData.imagePreview103)
                    .into(sivPromoProduct)

                getProductGratis(productId!!)

                tvLihatSemuaBarangGratis.setOnClickListener {
                    val produkGratisIntent = Intent(this@ProductDetailActivity,ProductDetailPromoGratisActivity::class.java)
                    produkGratisIntent.putExtra(PresentationUtils.PRODUCT_ID_PROMO,productData.productId)
                    startActivity(produkGratisIntent)
                }

            } else{
                setPromoProductVisibility(false)
            }

        }
        progressBar.dismiss()
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

    private fun setPromoProductVisibility(adaProdukGratis: Boolean){
        if (adaProdukGratis){
            binding.apply {
                cvPromoGratisProduk.visibility = View.VISIBLE
                tvTitlePromo.visibility = View.VISIBLE
            }
        } else {
            binding.apply {
                cvPromoGratisProduk.visibility = View.GONE
                tvTitlePromo.visibility = View.GONE
            }
        }
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