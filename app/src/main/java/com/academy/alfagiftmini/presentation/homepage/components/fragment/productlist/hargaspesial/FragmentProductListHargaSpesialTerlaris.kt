package com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.hargaspesial

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.academy.alfagiftmini.databinding.FragmentProductListTerlarisHargaSpesialBinding
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.homepage.activity.MainActivity
import com.academy.alfagiftmini.presentation.homepage.components.adapter.productlist.ProductListGratisProductPagingAdapter
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductListViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class FragmentProductListHargaSpesialTerlaris : Fragment() {
    private lateinit var binding: FragmentProductListTerlarisHargaSpesialBinding
    private lateinit var viewModel: ProductListViewModel
    private lateinit var adapter: ProductListGratisProductPagingAdapter
    private lateinit var dialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProductListTerlarisHargaSpesialBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setProgress()
        setViewModel()
        setAdapter()
        getData()
    }

    private fun setProgress() {
        dialog = PresentationUtils.loadingAlertDialog(requireActivity())
    }

    private fun getData() {
        lifecycleScope.launch {
            viewModel.getProductGratisProductOrder(
                PresentationUtils.TYPE_HARGA_SPESIAL,
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
        PresentationUtils.adapterAddLoadStateListenerProduct(
            adapter,
            dialog,
            requireContext(),
            ::getData,
            true,
            requireActivity()
        )

    }

    private fun setViewModel() {
        viewModel = (requireActivity() as MainActivity).getViewModelProductList()
    }


}