package com.academy.alfagiftmini.presentation.homepage.components.fragment.productcategories

import android.app.Presentation
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.FragmentProductCategoriesDetailBinding
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.gratisproduct.FragmentProductListGratisProductNamaProduk
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.gratisproduct.FragmentProductListGratisProductPromosi
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.gratisproduct.FragmentProductListGratisProductTerlaris
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductCategoriesViewModel
import com.google.android.material.tabs.TabLayout

class FragmentProductCategoriesDetail(private val viewModel: ProductCategoriesViewModel, private val subCategory: String, private val category: String): Fragment() {
    private lateinit var binding: FragmentProductCategoriesDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductCategoriesDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTabs()
    }

    private fun initTabs() {
        binding.apply {
            tlSubCategoryProduct.addTab(tlSubCategoryProduct.newTab().setCustomView(
                R.layout.tab_item
            ).apply {
                customView?.findViewById<TextView>(R.id.tv_tab_item)?.text = getString(R.string.promosi)
            })

            tlSubCategoryProduct.addTab(tlSubCategoryProduct.newTab().setCustomView(
                R.layout.tab_item
            ).apply {
                customView?.findViewById<TextView>(R.id.tv_tab_item)?.text = getString(R.string.nama_product)
                customView?.findViewById<ImageView>(R.id.iv_tab_item_up)
                    ?.setImageResource(R.drawable.arrow_up_tab_item)
                customView?.findViewById<ImageView>(R.id.iv_tab_item_down)
                    ?.setImageResource(R.drawable.arrow_down_tab_item)
            })



            tlSubCategoryProduct.addTab(tlSubCategoryProduct.newTab().setCustomView(
                R.layout.tab_item
            ).apply {
                customView?.findViewById<TextView>(R.id.tv_tab_item)?.text = getString(R.string.terlaris)
            })

            tlSubCategoryProduct.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabReselected(tab: TabLayout.Tab) {
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {
                    if (tab.position == 1) {
                        with(tab.customView){
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
                FragmentProductCategoriesList() //promosi
            }
            1 -> {
                FragmentProductCategoriesList() //nama produk
            }
            else -> {
                FragmentProductCategoriesList() //terlaris
            }
        }

        val tag = FragmentProductCategoriesList::class.java.simpleName
        val fragmentManager = parentFragmentManager
        fragmentManager.beginTransaction().apply {
            replace(binding.subContainer.id, fragment, tag)
            commit()
        }
    }
}