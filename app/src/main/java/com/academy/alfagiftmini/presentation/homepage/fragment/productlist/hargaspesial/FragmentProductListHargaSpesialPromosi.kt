package com.academy.alfagiftmini.presentation.homepage.fragment.productlist.hargaspesial

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.academy.alfagiftmini.databinding.FragmentProductListPromosiBinding
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.homepage.activity.productlist.ProductListHargaSpesialActivity
import com.academy.alfagiftmini.presentation.homepage.adapter.ProductListPagingAdapter
import com.academy.alfagiftmini.presentation.homepage.viewmodel.ProductListViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class FragmentProductListHargaSpesialPromosi : Fragment() {
    private lateinit var binding: FragmentProductListPromosiBinding
    private lateinit var viewModel: ProductListViewModel
    private lateinit var adapter: ProductListPagingAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProductListPromosiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModel()
        setAdapter()
        getData()

    }

    private fun getData() {
        lifecycleScope.launch {
            viewModel.getAllProductList(PresentationUtils.TYPE_HARGA_SPESIAL).collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun setAdapter() {
        adapter = ProductListPagingAdapter()
        binding.rvProductListPromosi.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvProductListPromosi.adapter = adapter
    }

    private fun setViewModel() {
        viewModel = (requireActivity() as ProductListHargaSpesialActivity).getProductListViewModel()
    }


}