package com.academy.alfagiftmini.presentation.homepage.components.activity.banner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import com.academy.alfagiftmini.MyApplication
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.ActivityBannerPromoItemListBinding
import com.academy.alfagiftmini.presentation.factory.PresentationFactory
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.bannerproduct.FragmentBannerProductNamaProduk
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.bannerproduct.FragmentBannerProductPromosi
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.bannerproduct.FragmentBannerProductTerlaris
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.BannerListViewModel
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductListViewModel
import com.google.android.material.tabs.TabLayout
import javax.inject.Inject

class BannerPromoItemListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBannerPromoItemListBinding

    @Inject
    lateinit var presentationFactory: PresentationFactory
    private val viewModel: BannerListViewModel by viewModels {
        presentationFactory
    }

    private val productListViewModel: ProductListViewModel by viewModels {
        presentationFactory
    }

    private val bannerId = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.bannerListPromoItemListActivityInject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityBannerPromoItemListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initTabs()
        setupFragment(0)
    }

    private fun initTabs() {
        var isClicked: Boolean? = null


        binding.tlBannerProduct.addTab(binding.tlBannerProduct.newTab().setCustomView(
            R.layout.tab_item
        ).apply {
            customView?.findViewById<TextView>(R.id.tv_tab_item)?.text = "Promosi"
        })

        binding.tlBannerProduct.addTab(binding.tlBannerProduct.newTab().setCustomView(
            R.layout.tab_item
        ).apply {
            customView?.findViewById<TextView>(R.id.tv_tab_item)?.text = "Nama Product"
            customView?.findViewById<ImageView>(R.id.iv_tab_item_up)
                ?.setImageResource(R.drawable.arrow_up_tab_item)
            customView?.findViewById<ImageView>(R.id.iv_tab_item_down)
                ?.setImageResource(R.drawable.arrow_down_tab_item)
        })



        binding.tlBannerProduct.addTab(binding.tlBannerProduct.newTab().setCustomView(
            R.layout.tab_item
        ).apply {
            customView?.findViewById<TextView>(R.id.tv_tab_item)?.text = "Terlaris"
        })

        binding.tlBannerProduct.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                if (tab.position == 1) {
                    isClicked = null
                    tab.customView?.findViewById<ImageView>(R.id.iv_tab_item_up)
                        ?.setImageResource(R.drawable.arrow_up_tab_item)
                    tab.customView?.findViewById<ImageView>(R.id.iv_tab_item_down)
                        ?.setImageResource(R.drawable.arrow_down_tab_item)

                }
            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                setupFragment(tab.position)

                if (tab.position == 1) {
                    isClicked = true
                    tab.customView?.findViewById<ImageView>(R.id.iv_tab_item_up)
                        ?.setImageResource(R.drawable.arrow_up_tab_item_blue)

                }
            }
        })
    }

    private fun setupFragment(position: Int) {
        val fragment = when (position) {
            0 -> {
                FragmentBannerProductPromosi()
            }
            1 -> {
                FragmentBannerProductNamaProduk()
            }
            else -> {
                FragmentBannerProductTerlaris()
            }
        }

        val tag = when (position) {
            0 -> FragmentBannerProductPromosi::class.java.simpleName
            1 -> FragmentBannerProductNamaProduk::class.java.simpleName
            else -> FragmentBannerProductTerlaris::class.java.simpleName
        }
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().apply {
            replace(R.id.container, fragment, tag)
            commit()
        }
    }

    fun getTab(): TabLayout {
        return binding.tlBannerProduct
    }

    fun getProductViewModel(): ProductListViewModel {
        return productListViewModel
    }

    fun getBannerIdValue(): Int {
        return bannerId
    }

    private fun setToolbar() {
//        binding.allBannerListToolbar.tvPromoToolbarTitle.text = getString(R.string.banner_list_title)
//        binding.allBannerListToolbar.btnBannerBack.setOnClickListener {
//            finish()
//        }
    }
}