package com.academy.alfagiftmini.presentation.homepage.fragment

import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.FragmentPromoBinding
import com.academy.alfagiftmini.presentation.homepage.activity.MainActivity
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.FragmentGratisProduk
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.FragmentHargaSpecial
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.FragmentPaket
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.FragmentTebusMurah
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.hargaspesial.FragmentProductListHargaSpesialNamaProduk
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.hargaspesial.FragmentProductListHargaSpesialPromosi
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.hargaspesial.FragmentProductListHargaSpesialTerlaris
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductListViewModel
import com.google.android.material.tabs.TabLayout

class FragmentPromo : Fragment() {
    private lateinit var binding: FragmentPromoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentPromoBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar()
        initTabs()
        setupFragment(0)
    }

    private fun setToolbar() {
        binding.tvToolbar.text = getString(R.string.promo)
    }


    private fun initTabs() {
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Harga Spesial"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Gratis Produk"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Paket"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Tebus Murah"))
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                setupFragment(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}

            override fun onTabReselected(tab: TabLayout.Tab) {}

        })
    }

    private fun setupFragment(position: Int) {
        val fragment = when (position) {
            0 -> {
                FragmentHargaSpecial()
            }
            1 -> {
                FragmentGratisProduk()
            }
            2 -> {
                FragmentPaket()
            }
            else -> {
                FragmentTebusMurah()
            }
        }

        val tag = when (position) {
            0 -> FragmentHargaSpecial::class.java.simpleName
            1 -> FragmentGratisProduk::class.java.simpleName
            2 -> FragmentPaket::class.java.simpleName
            else -> FragmentTebusMurah::class.java.simpleName
        }
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.beginTransaction().apply {
            replace(R.id.container_fragment_promo, fragment, tag)
            commit()
        }
    }

}