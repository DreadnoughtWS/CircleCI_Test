package com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.paket

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.academy.alfagiftmini.databinding.FragmentProductListPaketTerlarisBinding
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.homepage.activity.MainActivity
import com.academy.alfagiftmini.presentation.homepage.components.adapter.productlist.ProductListGratisProductPagingAdapter
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductListViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class FragmentProductListPaketTerlaris : Fragment() {
    private lateinit var dialog: Dialog
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
        setProgressbar()
        setViewModel()
        setAdapter()
        getData()
    }

    private fun setProgressbar() {
        dialog = PresentationUtils.loadingAlertDialog(requireActivity())
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
        binding.apply {
            rvProductListTerlaris.layoutManager = GridLayoutManager(requireContext(), 2)
            rvProductListTerlaris.adapter = adapter
        }
        PresentationUtils.adapterAddLoadStateListenerProduct(adapter,dialog,requireContext(),::getData)
    }

    private fun setViewModel() {
        viewModel = (requireActivity() as MainActivity).getViewModelProductList()
    }


}