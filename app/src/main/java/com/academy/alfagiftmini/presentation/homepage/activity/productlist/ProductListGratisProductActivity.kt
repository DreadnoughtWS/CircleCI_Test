package com.academy.alfagiftmini.presentation.homepage.activity.productlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import com.academy.alfagiftmini.MyApplication
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.ActivityProductListGratisProductBinding
import com.academy.alfagiftmini.databinding.ActivityProductListHargaSpesialBinding
import com.academy.alfagiftmini.presentation.factory.PresentationFactory
import com.academy.alfagiftmini.presentation.homepage.fragment.productlist.gratisproduct.FragmentProductListGratisProductNamaProduk
import com.academy.alfagiftmini.presentation.homepage.fragment.productlist.gratisproduct.FragmentProductListGratisProductPromosi
import com.academy.alfagiftmini.presentation.homepage.fragment.productlist.gratisproduct.FragmentProductListGratisProductTerlaris
import com.academy.alfagiftmini.presentation.homepage.fragment.productlist.hargaspesial.FragmentProductListHargaSpesialNamaProduk
import com.academy.alfagiftmini.presentation.homepage.fragment.productlist.hargaspesial.FragmentProductListHargaSpesialPromosi
import com.academy.alfagiftmini.presentation.homepage.fragment.productlist.hargaspesial.FragmentProductListHargaSpesialTerlaris
import com.academy.alfagiftmini.presentation.homepage.viewmodel.ProductListViewModel
import com.google.android.material.tabs.TabLayout
import javax.inject.Inject

class ProductListGratisProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductListGratisProductBinding

    @Inject
    lateinit var presentationFactory: PresentationFactory
    private val viewModel: ProductListViewModel by viewModels {
        presentationFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.productListGratisProductActivityInject(this)
        binding = ActivityProductListGratisProductBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initTabs()
        setupFragment(0)
    }

    private fun initTabs() {
        var isClicked: Boolean? = null


        binding.tlGratisProduct.addTab(binding.tlGratisProduct.newTab().setCustomView(
            R.layout.tab_item
        ).apply {
            customView?.findViewById<TextView>(R.id.tv_tab_item)?.text = "Promosi"
        })

        binding.tlGratisProduct.addTab(binding.tlGratisProduct.newTab().setCustomView(
            R.layout.tab_item
        ).apply {
            customView?.findViewById<TextView>(R.id.tv_tab_item)?.text = "Nama Product"
            customView?.findViewById<ImageView>(R.id.iv_tab_item_up)
                ?.setImageResource(R.drawable.arrow_up_tab_item)
            customView?.findViewById<ImageView>(R.id.iv_tab_item_down)
                ?.setImageResource(R.drawable.arrow_down_tab_item)
        })



        binding.tlGratisProduct.addTab(binding.tlGratisProduct.newTab().setCustomView(
            R.layout.tab_item
        ).apply {
            customView?.findViewById<TextView>(R.id.tv_tab_item)?.text = "Terlaris"
        })

        binding.tlGratisProduct.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
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
                FragmentProductListGratisProductPromosi()
            }
            1 -> {
                FragmentProductListGratisProductNamaProduk()
            }
            else -> {
                FragmentProductListGratisProductTerlaris()
            }
        }

        val tag = when (position) {
            0 -> FragmentProductListGratisProductPromosi::class.java.simpleName
            1 -> FragmentProductListGratisProductNamaProduk::class.java.simpleName
            else -> FragmentProductListGratisProductTerlaris::class.java.simpleName
        }
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().apply {
            replace(R.id.container, fragment, tag)
            commit()
        }
    }

    fun getProductViewModel():ProductListViewModel{
        return viewModel
    }

    fun getTab(): TabLayout {
        return binding.tlGratisProduct
    }
}