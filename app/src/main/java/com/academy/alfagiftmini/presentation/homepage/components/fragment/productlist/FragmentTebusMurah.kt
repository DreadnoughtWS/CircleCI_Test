package com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductListViewModel


class FragmentTebusMurah(private val productListViewModel: ProductListViewModel) : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tebus_murah, container, false)
    }


}