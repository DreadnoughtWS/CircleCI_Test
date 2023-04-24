package com.academy.alfagiftmini.presentation.homepage.fragmentadapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.academy.alfagiftmini.presentation.homepage.fragment.productlist.hargaspesial.FragmentProductListHargaSpesialNamaProduk
import com.academy.alfagiftmini.presentation.homepage.fragment.productlist.hargaspesial.FragmentProductListHargaSpesialPromosi
import com.academy.alfagiftmini.presentation.homepage.fragment.productlist.hargaspesial.FragmentProductListHargaSpesialTerlaris

class FragmentAdapterProductListHargaSpesial(
    activity: AppCompatActivity,
) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> FragmentProductListHargaSpesialPromosi()
            1 -> FragmentProductListHargaSpesialNamaProduk()
            2 -> FragmentProductListHargaSpesialTerlaris()
            else -> FragmentProductListHargaSpesialPromosi()
        }
    }
}