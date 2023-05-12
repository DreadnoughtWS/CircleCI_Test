package com.academy.alfagiftmini.presentation.homepage.components.activity.productlist

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat.setNestedScrollingEnabled
import com.academy.alfagiftmini.MyApplication
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.ActivityProductListPenawaranTerbaikBinding
import com.academy.alfagiftmini.presentation.factory.PresentationFactory
import com.academy.alfagiftmini.presentation.homepage.components.adapter.productlist.ProductListGratisProductPagingAdapter
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.penawaranterbaik.FragmentPenawaranTerbaikNamaProduk
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.penawaranterbaik.FragmentPenawaranTerbaikPromosi
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.penawaranterbaik.FragmentPenawaranTerbaikTerlaris
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductListViewModel
import com.google.android.material.tabs.TabLayout
import javax.inject.Inject

class ProductListPenawaranTerbaikActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductListPenawaranTerbaikBinding

    @Inject
    lateinit var presentationFactory: PresentationFactory
    private val viewModel: ProductListViewModel by viewModels {
        presentationFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.productListPenawaranTerbaikActivityInject(this)
        binding = ActivityProductListPenawaranTerbaikBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initTabs()
        setupFragment(0)
        setBtn()
    }


    private fun setBtn() {
        binding.ivBackToolbar.setOnClickListener {
            finish()
        }
    }


    private fun initTabs() {
        binding.apply {
            tlPenawaran.addTab(tlPenawaran.newTab().setCustomView(
                R.layout.tab_item
            ).apply {
                customView?.findViewById<TextView>(R.id.tv_tab_item)?.text =
                    getString(R.string.promosi)
            })

            tlPenawaran.addTab(tlPenawaran.newTab().setCustomView(
                R.layout.tab_item
            ).apply {
                with(customView) {
                    this?.findViewById<TextView>(R.id.tv_tab_item)?.text = getString(
                        R.string.nama_product
                    )
                    this?.findViewById<ImageView>(R.id.iv_tab_item_up)
                        ?.setImageResource(R.drawable.arrow_up_tab_item)
                    this?.findViewById<ImageView>(R.id.iv_tab_item_down)
                        ?.setImageResource(R.drawable.arrow_down_tab_item)
                }

            })



            tlPenawaran.addTab(tlPenawaran.newTab().setCustomView(
                R.layout.tab_item
            ).apply {
                customView?.findViewById<TextView>(R.id.tv_tab_item)?.text =
                    getString(R.string.terlaris)
            })

            tlPenawaran.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
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
                FragmentPenawaranTerbaikPromosi()
            }
            1 -> {
                FragmentPenawaranTerbaikNamaProduk()
            }
            else -> {
                FragmentPenawaranTerbaikTerlaris()
            }
        }

        val tag = when (position) {
            0 -> FragmentPenawaranTerbaikPromosi::class.java.simpleName
            1 -> FragmentPenawaranTerbaikNamaProduk::class.java.simpleName
            else -> FragmentPenawaranTerbaikTerlaris::class.java.simpleName
        }
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().apply {
            replace(R.id.container_fragment_penawaran, fragment, tag)
            commit()
        }
    }


    fun getTab(): TabLayout {
        return binding.tlPenawaran
    }

    fun getViewModelProductList(): ProductListViewModel {
        return viewModel
    }

}