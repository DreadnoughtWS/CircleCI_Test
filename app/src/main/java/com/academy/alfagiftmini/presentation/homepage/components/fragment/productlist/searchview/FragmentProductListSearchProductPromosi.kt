package com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.searchview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.academy.alfagiftmini.databinding.FragmentProductListSearchProductPromosiBinding
import com.academy.alfagiftmini.presentation.homepage.components.activity.productlist.ProductListSearchProdukActivity
import com.academy.alfagiftmini.presentation.homepage.components.adapter.productlist.ProductListGratisProductPagingAdapter
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductListViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FragmentProductListSearchProductPromosi : Fragment() {
    private lateinit var binding: FragmentProductListSearchProductPromosiBinding
    private lateinit var viewModel: ProductListViewModel
    private lateinit var adapter: ProductListGratisProductPagingAdapter
    var dataName: String = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductListSearchProductPromosiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModelandData()
        setAdapter()
        getDataFromApi()
    }

    private fun getDataFromApi() {
        println("daata nama = $dataName")
        lifecycleScope.launch {
            viewModel.getProductSearchProduct(name = dataName).collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun setAdapter() {
        adapter = ProductListGratisProductPagingAdapter()
        binding.rvProductListPromosi.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvProductListPromosi.adapter = adapter
    }

    private fun setViewModelandData() {
        viewModel = (requireActivity() as ProductListSearchProdukActivity).getProductViewModel()
        dataName = (requireActivity() as ProductListSearchProdukActivity).getNameSearch()
    }


}