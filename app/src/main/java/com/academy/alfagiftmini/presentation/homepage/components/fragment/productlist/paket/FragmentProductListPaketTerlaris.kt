package com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.paket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.FragmentProductListGratisProductTerlarisBinding
import com.academy.alfagiftmini.databinding.FragmentProductListPaketTerlarisBinding
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.homepage.components.activity.productlist.ProductListGratisProductActivity
import com.academy.alfagiftmini.presentation.homepage.components.activity.productlist.ProductListPaketActivity
import com.academy.alfagiftmini.presentation.homepage.components.adapter.productlist.ProductListGratisProductPagingAdapter
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductListViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class FragmentProductListPaketTerlaris : Fragment() {
    private lateinit var binding: FragmentProductListPaketTerlarisBinding
    private lateinit var viewModel: ProductListViewModel
    private lateinit var adapter: ProductListGratisProductPagingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductListPaketTerlarisBinding.inflate(inflater, container, false)
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
            viewModel.getProductGratisProductOrder(
                PresentationUtils.TYPE_PAKET,
                PresentationUtils.ORDER_BY_DESCENDING,
                "sales_quantity"
            ).collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun setAdapter() {
        adapter = ProductListGratisProductPagingAdapter()
        binding.rvProductListTerlaris.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvProductListTerlaris.adapter = adapter
    }

    private fun setViewModel() {
        viewModel = (requireActivity() as ProductListPaketActivity).getProductListViewModel()
    }


}