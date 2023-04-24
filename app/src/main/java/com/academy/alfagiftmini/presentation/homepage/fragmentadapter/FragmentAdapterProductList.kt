package com.academy.alfagiftmini.presentation.homepage.fragmentadapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.academy.alfagiftmini.presentation.homepage.fragment.FragmentProductListNamaProduk
import com.academy.alfagiftmini.presentation.homepage.fragment.FragmentProductListPromosi
import com.academy.alfagiftmini.presentation.homepage.fragment.FragmentProductListTerlaris

class FragmentAdapterProductList(
    activity: AppCompatActivity,
) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> FragmentProductListPromosi()
            1 -> FragmentProductListNamaProduk()
            2 -> FragmentProductListTerlaris()
            else -> FragmentProductListPromosi()
        }
    }
}