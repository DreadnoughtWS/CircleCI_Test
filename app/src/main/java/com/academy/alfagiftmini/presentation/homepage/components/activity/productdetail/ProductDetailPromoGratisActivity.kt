package com.academy.alfagiftmini.presentation.homepage.components.activity.productdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Slide
import android.transition.Transition
import android.transition.TransitionManager
import android.view.Gravity
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.academy.alfagiftmini.MyApplication
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.ActivityProductDetailPromoGratisBinding
import com.academy.alfagiftmini.domain.productdetail.model.ProductDetailDomainModel
import com.academy.alfagiftmini.domain.productdetail.model.ProductPromosi103DomainModel
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.factory.PresentationFactory
import com.academy.alfagiftmini.presentation.homepage.components.adapter.productdetail.ProductDetailSliderAdapter
import com.academy.alfagiftmini.presentation.homepage.components.adapter.productdetail.ProductGratisAdapter
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductDetailViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductDetailPromoGratisActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailPromoGratisBinding
    private lateinit var adapter: ProductGratisAdapter
    private lateinit var progressBar: AlertDialog

    @Inject
    lateinit var presentationFactory: PresentationFactory
    private val viewModel: ProductDetailViewModel by viewModels {
        presentationFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MyApplication).appComponent.productGratisActivityInject(this)
        binding = ActivityProductDetailPromoGratisBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (intent != null){
            getLiveData()
        }

        setButtons()

    }

    private fun getLiveData() {
        val productId = intent.getLongExtra(PresentationUtils.PRODUCT_ID_PROMO,0L)
        lifecycleScope.launch {
            viewModel.getProductPromoGratis(productId)
            viewModel.productGratisData.collectLatest {
                setupLayout(it[0])
            }
        }
    }

    private fun setupLayout(promoData: ProductPromosi103DomainModel) {
        binding.apply {
            tvPromoGratisProductContent.text = promoData.description
//            val productGratisList = arrayListOf<ProductDetailDomainModel>()
            adapter = ProductGratisAdapter(mutableListOf())
            rvProdukGratis.layoutManager = LinearLayoutManager(this@ProductDetailPromoGratisActivity)
            rvProdukGratis.adapter = adapter
            viewModel.productDetailDataLive.observe(this@ProductDetailPromoGratisActivity) {
//                if(it.isEmpty()) return@observe
                adapter.addDataToList(it[0])
            }
            lifecycleScope.launch {
                promoData.promoProductId.forEach {
                    viewModel.getProductDetailLive(it)
                }
            }

        }
    }

    private fun setButtons() {
        binding.apply {

            ivDropdown.setOnClickListener {
                val transitionBottom: Transition = Slide(Gravity.TOP)
                transitionBottom.duration = 600
                transitionBottom.addTarget(this.rvProdukGratis)
                TransitionManager.beginDelayedTransition(this.root, transitionBottom)
                this.rvProdukGratis.visibility = if (this.rvProdukGratis.isShown) View.GONE else View.VISIBLE

                if (this.rvProdukGratis.isShown){
                    this.tvPromoGratisProductContent.maxLines = 10
                    this.ivDropdown.setImageResource(R.drawable.baseline_keyboard_arrow_up_24_gray)
                } else {
                    this.tvPromoGratisProductContent.maxLines = 2
                    this.ivDropdown.setImageResource(R.drawable.baseline_keyboard_arrow_down_24_gray)
                }

            }
            ibBackButton.setOnClickListener {
                finish()
            }
            btTutupGratisButton.setOnClickListener {
                finish()
            }
        }
    }
}