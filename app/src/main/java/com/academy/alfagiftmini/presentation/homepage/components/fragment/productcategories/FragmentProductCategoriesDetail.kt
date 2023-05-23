package com.academy.alfagiftmini.presentation.homepage.components.fragment.productcategories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.FragmentProductCategoriesDetailBinding
import com.academy.alfagiftmini.presentation.homepage.components.activity.productcategories.ProductCategoriesActivity
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.categoryproduct.FragmentProductCategoriesListNamaProduk
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.categoryproduct.FragmentProductCategoriesListPromosi
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.categoryproduct.FragmentProductCategoriesListTerlaris

import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductListViewModel
import com.google.android.material.tabs.TabLayout

class FragmentProductCategoriesDetail(
    private val subCategory: String, private val category: String
) : Fragment() {
    private lateinit var binding: FragmentProductCategoriesDetailBinding
    private lateinit var productListViewModel: ProductListViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductCategoriesDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initTabs()
        setupFragment(0)
    }

    private fun initViewModel() {
        productListViewModel =
            (requireActivity() as ProductCategoriesActivity).getProductListCategoryViewModel()
    }

    private fun initTabs() {
        binding.apply {
            tlSubCategoryProduct.addTab(tlSubCategoryProduct.newTab().setCustomView(
                R.layout.tab_item
            ).apply {
                customView?.findViewById<TextView>(R.id.tv_tab_item)?.text =
                    getString(R.string.promosi)
            })

            tlSubCategoryProduct.addTab(tlSubCategoryProduct.newTab().setCustomView(
                R.layout.tab_item
            ).apply {
                customView?.findViewById<TextView>(R.id.tv_tab_item)?.text =
                    getString(R.string.nama_product)
                customView?.findViewById<ImageView>(R.id.iv_tab_item_up)
                    ?.setImageResource(R.drawable.arrow_up_tab_item)
                customView?.findViewById<ImageView>(R.id.iv_tab_item_down)
                    ?.setImageResource(R.drawable.arrow_down_tab_item)
            })

            tlSubCategoryProduct.addTab(tlSubCategoryProduct.newTab().setCustomView(
                R.layout.tab_item
            ).apply {
                customView?.findViewById<TextView>(R.id.tv_tab_item)?.text =
                    getString(R.string.terlaris)
            })

            tlSubCategoryProduct.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabReselected(tab: TabLayout.Tab) {
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {
                    if (tab.position == 1) {
                        with(tab.customView) {
                            this?.findViewById<ImageView>(R.id.iv_tab_item_up)
                                ?.setImageResource(R.drawable.arrow_up_tab_item)
                            this?.findViewById<ImageView>(R.id.iv_tab_item_down)
                                ?.setImageResource(R.drawable.arrow_down_tab_item)
                        }
                    }
                }

                override fun onTabSelected(tab: TabLayout.Tab) {
                    setupFragment(tab.position)

                    if (tab.position == 1) {
                        tab.customView?.findViewById<ImageView>(R.id.iv_tab_item_up)
                            ?.setImageResource(R.drawable.arrow_up_tab_item_blue)

                    }
                }
            })
        }


    }

    private fun setupFragment(position: Int) {
        val fragment = when (position) {
            0 -> {
                FragmentProductCategoriesListPromosi(
                    productListViewModel, subCategory, category
                ) //promosi
            }
            1 -> {
                FragmentProductCategoriesListNamaProduk(
                    productListViewModel, subCategory, category
                ) //nama produk
            }
            else -> {
                FragmentProductCategoriesListTerlaris(
                    productListViewModel, subCategory, category
                ) //terlaris
            }
        }

        val tag = FragmentProductCategoriesListNamaProduk::class.java.simpleName
        val fragmentManager = parentFragmentManager
        fragmentManager.beginTransaction().apply {
            replace(binding.subContainer.id, fragment, tag)
            commit()
        }
    }

    fun getTab(): TabLayout {
        return binding.tlSubCategoryProduct
    }
}