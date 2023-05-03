package com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.searchview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.academy.alfagiftmini.R


class FragmentProductListSearchProductTerlaris : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(
            R.layout.fragment_product_list_search_product_terlaris,
            container,
            false
        )
    }

}