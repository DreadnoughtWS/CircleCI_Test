package com.academy.alfagiftmini.presentation.homepage.components.activity.productlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.academy.alfagiftmini.MyApplication
import com.academy.alfagiftmini.databinding.ActivityProductListGratisProductBinding
import com.academy.alfagiftmini.presentation.factory.PresentationFactory
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductListViewModel
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
//        initTabs()
//        setupFragment(0)
    }

//    private fun initTabs() {
//
//        binding.apply {
//            tlGratisProduct.addTab(tlGratisProduct.newTab().setCustomView(
//                R.layout.tab_item
//            ).apply {
//                customView?.findViewById<TextView>(R.id.tv_tab_item)?.text =
//                    getString(R.string.promosi)
//            })
//
//            tlGratisProduct.addTab(tlGratisProduct.newTab().setCustomView(
//                R.layout.tab_item
//            ).apply {
//                customView?.findViewById<TextView>(R.id.tv_tab_item)?.text =
//                    getString(R.string.nama_product)
//                customView?.findViewById<ImageView>(R.id.iv_tab_item_up)
//                    ?.setImageResource(R.drawable.arrow_up_tab_item)
//                customView?.findViewById<ImageView>(R.id.iv_tab_item_down)
//                    ?.setImageResource(R.drawable.arrow_down_tab_item)
//            })
//
//
//
//            tlGratisProduct.addTab(tlGratisProduct.newTab().setCustomView(
//                R.layout.tab_item
//            ).apply {
//                customView?.findViewById<TextView>(R.id.tv_tab_item)?.text =
//                    getString(R.string.terlaris)
//            })
//
//            tlGratisProduct.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//                override fun onTabReselected(tab: TabLayout.Tab) {
//                }
//
//                override fun onTabUnselected(tab: TabLayout.Tab) {
//                    if (tab.position == 1) {
//                        with(tab.customView) {
//                            this?.findViewById<ImageView>(R.id.iv_tab_item_up)
//                                ?.setImageResource(R.drawable.arrow_up_tab_item)
//                            this?.findViewById<ImageView>(R.id.iv_tab_item_down)
//                                ?.setImageResource(R.drawable.arrow_down_tab_item)
//                        }
//
//
//                    }
//                }
//
//                override fun onTabSelected(tab: TabLayout.Tab) {
//                    setupFragment(tab.position)
//
//                    if (tab.position == 1) {
//                        tab.customView?.findViewById<ImageView>(R.id.iv_tab_item_up)
//                            ?.setImageResource(R.drawable.arrow_up_tab_item_blue)
//
//                    }
//                }
//            })
//        }
//
//
//    }
//
//    private fun setupFragment(position: Int) {
//        val fragment = when (position) {
//            0 -> {
//                FragmentProductListGratisProductPromosi()
//            }
//            1 -> {
//                FragmentProductListGratisProductNamaProduk()
//            }
//            else -> {
//                FragmentProductListGratisProductTerlaris()
//            }
//        }
//
//        val tag = when (position) {
//            0 -> FragmentProductListGratisProductPromosi::class.java.simpleName
//            1 -> FragmentProductListGratisProductNamaProduk::class.java.simpleName
//            else -> FragmentProductListGratisProductTerlaris::class.java.simpleName
//        }
//        val fragmentManager = supportFragmentManager
//        fragmentManager.beginTransaction().apply {
//            replace(R.id.container, fragment, tag)
//            commit()
//        }
//    }
//
//    fun getProductViewModel(): ProductListViewModel {
//        return viewModel
//    }
//
//    fun getTab(): TabLayout {
//        return binding.tlGratisProduct
//    }
}