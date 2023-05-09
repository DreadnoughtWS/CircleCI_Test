package com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist

import android.os.Bundle
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.FragmentPaketBinding
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.paket.FragmentProductListPaketNamaProduk
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.paket.FragmentProductListPaketPromosi
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.paket.FragmentProductListPaketTerlaris
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductListViewModel
import com.google.android.material.tabs.TabLayout

class FragmentPaket : Fragment() {
    private lateinit var binding:FragmentPaketBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPaketBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTabs()
        setupFragment(0)
    }

    private fun initTabs() {
        binding.apply{
            tlPaket.addTab(tlPaket.newTab().setCustomView(
                R.layout.tab_item
            ).apply {
                customView?.findViewById<TextView>(R.id.tv_tab_item)?.text = getString(R.string.promosi)
            })

            tlPaket.addTab(tlPaket.newTab().setCustomView(
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

            tlPaket.addTab(tlPaket.newTab().setCustomView(
                R.layout.tab_item
            ).apply {
                customView?.findViewById<TextView>(R.id.tv_tab_item)?.text = getString(R.string.terlaris)
            })

            tlPaket.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
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
                FragmentProductListPaketPromosi()
            }
            1 -> {
                FragmentProductListPaketNamaProduk()
            }
            else -> {
                FragmentProductListPaketTerlaris()
            }
        }

        val tag = when (position) {
            0 -> FragmentProductListPaketPromosi::class.java.simpleName
            1 -> FragmentProductListPaketNamaProduk::class.java.simpleName
            else -> FragmentProductListPaketTerlaris::class.java.simpleName
        }
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.beginTransaction().apply {
            replace(R.id.container_fragment_paket, fragment, tag)
            commit()
        }
    }

    fun getTab(): TabLayout = binding.tlPaket


}