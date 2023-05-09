package com.academy.alfagiftmini.presentation.homepage.components.activity.productlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import com.academy.alfagiftmini.MyApplication
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.ActivityProductListHargaSpesialBinding
import com.academy.alfagiftmini.presentation.factory.PresentationFactory
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.hargaspesial.FragmentProductListHargaSpesialNamaProduk
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.hargaspesial.FragmentProductListHargaSpesialPromosi
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.hargaspesial.FragmentProductListHargaSpesialTerlaris
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductListViewModel
import com.google.android.material.tabs.TabLayout
import javax.inject.Inject

class ProductListHargaSpesialActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductListHargaSpesialBinding

    @Inject
    lateinit var presentationFactory: PresentationFactory
    private val viewModel: ProductListViewModel by viewModels {
        presentationFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.productListHargaSepsialActivityInject(this)
        binding = ActivityProductListHargaSpesialBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initTabs()
        setupFragment(0)
    }

    fun getProductListViewModel(): ProductListViewModel {
        return viewModel
    }


    private fun initTabs() {

        binding.apply {
            tlHargaSpecial.addTab(tlHargaSpecial.newTab().setCustomView(
                R.layout.tab_item
            ).apply {
                customView?.findViewById<TextView>(R.id.tv_tab_item)?.text = getString(R.string.promosi)
            })

            tlHargaSpecial.addTab(tlHargaSpecial.newTab().setCustomView(
                R.layout.tab_item
            ).apply {
                with(customView){
                    this?.findViewById<TextView>(R.id.tv_tab_item)?.text = getString(R.string.nama_product)
                    this?.findViewById<ImageView>(R.id.iv_tab_item_up)
                        ?.setImageResource(R.drawable.arrow_up_tab_item)
                    this?.findViewById<ImageView>(R.id.iv_tab_item_down)
                        ?.setImageResource(R.drawable.arrow_down_tab_item)
                }

            })



            tlHargaSpecial.addTab(tlHargaSpecial.newTab().setCustomView(
                R.layout.tab_item
            ).apply {
                customView?.findViewById<TextView>(R.id.tv_tab_item)?.text = getString(R.string.terlaris)
            })

            tlHargaSpecial.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
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
                FragmentProductListHargaSpesialPromosi()
            }
            1 -> {
                FragmentProductListHargaSpesialNamaProduk()
            }
            else -> {
                FragmentProductListHargaSpesialTerlaris()
            }
        }

        val tag = when (position) {
            0 -> FragmentProductListHargaSpesialPromosi::class.java.simpleName
            1 -> FragmentProductListHargaSpesialNamaProduk::class.java.simpleName
            else -> FragmentProductListHargaSpesialTerlaris::class.java.simpleName
        }
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().apply {
            replace(R.id.container, fragment, tag)
            commit()
        }
    }


    fun getTab(): TabLayout {
        return binding.tlHargaSpecial
    }

}