package com.academy.alfagiftmini.presentation.homepage.components.activity.productdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.ActivityProductDetailPromoGratisBinding
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
            adapter =
            rvProdukGratis =
        }
    }

    private fun setButtons() {
        binding.apply {
            ivDropdown.setOnClickListener {

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