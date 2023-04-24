package com.academy.alfagiftmini.presentation.homepage.activity.productlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import com.academy.alfagiftmini.MyApplication
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.ActivityProductListBinding
import com.academy.alfagiftmini.presentation.factory.PresentationFactory
import com.academy.alfagiftmini.presentation.homepage.fragmentadapter.FragmentAdapterProductListHargaSpesial
import com.academy.alfagiftmini.presentation.homepage.viewmodel.ProductListViewModel
import com.google.android.material.tabs.TabLayoutMediator
import javax.inject.Inject

class ProductListHargaSpesialActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductListBinding
    private lateinit var tabs: List<String>

    @Inject
    lateinit var presentationFactory: PresentationFactory
    private val viewModel: ProductListViewModel by viewModels {
        presentationFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.productListActivityInject(this)
        binding = ActivityProductListBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initTabs()
        initView()
    }

    fun getProductListViewModel(): ProductListViewModel {
        return viewModel
    }

    private fun initView() {
        binding.vpHargaSpecial.adapter = FragmentAdapterProductListHargaSpesial(this)
        TabLayoutMediator(
            binding.tlHargaSpecial, binding.vpHargaSpecial
        ) { tab, pos ->
            when (pos) {
                0 -> {
                    tab.setCustomView(R.layout.tab_item)
                    tab.customView?.findViewById<TextView>(R.id.tv_tab_item)?.text = tabs[0]
                }
                1 -> {
                    tab.setCustomView(R.layout.tab_item)
                    tab.customView?.findViewById<TextView>(R.id.tv_tab_item)?.text = tabs[1]
                    tab.customView?.findViewById<ImageView>(R.id.iv_tab_item)
                        ?.setImageResource(R.drawable.key_arrow_right)
                }
                2 -> {
                    tab.setCustomView(R.layout.tab_item)
                    tab.customView?.findViewById<TextView>(R.id.tv_tab_item)?.text = tabs[2]
                }
            }
        }.attach()
    }

    private fun initTabs() {
        tabs = listOf(
            "Promosi", "Nama Produk", "Terlaris"
        )
    }

}