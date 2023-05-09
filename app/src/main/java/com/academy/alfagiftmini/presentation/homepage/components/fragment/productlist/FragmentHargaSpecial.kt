package com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.FragmentHargaSpecialBinding
import com.academy.alfagiftmini.domain.produklist.ProductListDomainUseCase
import com.academy.alfagiftmini.presentation.factory.PresentationFactory
import com.academy.alfagiftmini.presentation.homepage.activity.MainActivity
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.hargaspesial.FragmentProductListHargaSpesialNamaProduk
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.hargaspesial.FragmentProductListHargaSpesialPromosi
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.hargaspesial.FragmentProductListHargaSpesialTerlaris
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductListViewModel
import com.academy.alfagiftmini.presentation.homepage.fragment.FragmentPromo
import com.google.android.material.tabs.TabLayout
import javax.inject.Inject


class FragmentHargaSpecial : Fragment() {
    private lateinit var binding: FragmentHargaSpecialBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHargaSpecialBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTabs()
        setupFragment(0)
    }


    private fun initTabs() {

        binding.apply {
            tlHargaSpecial.addTab(tlHargaSpecial.newTab().setCustomView(
                R.layout.tab_item
            ).apply {
                customView?.findViewById<TextView>(R.id.tv_tab_item)?.text =
                    getString(R.string.promosi)
            })

            tlHargaSpecial.addTab(tlHargaSpecial.newTab().setCustomView(
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



            tlHargaSpecial.addTab(tlHargaSpecial.newTab().setCustomView(
                R.layout.tab_item
            ).apply {
                customView?.findViewById<TextView>(R.id.tv_tab_item)?.text =
                    getString(R.string.terlaris)
            })

            tlHargaSpecial.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
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
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.beginTransaction().apply {
            replace(R.id.container_fragment_special, fragment, tag)
            commit()
        }
    }


    fun getTab(): TabLayout {
        return binding.tlHargaSpecial
    }


}