package com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.searchview

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.academy.alfagiftmini.databinding.FragmentProductListSearchProductTerlarisBinding
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.homepage.components.activity.productlist.ProductListSearchProdukActivity
import com.academy.alfagiftmini.presentation.homepage.components.adapter.productlist.ProductListGratisProductPagingAdapter
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductListViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class FragmentProductListSearchProductTerlaris : Fragment() {
    private lateinit var binding: FragmentProductListSearchProductTerlarisBinding
    private lateinit var viewModel: ProductListViewModel
    private lateinit var adapter: ProductListGratisProductPagingAdapter
    private var dataName: String = ""
    var type: String = ""
    private lateinit var dialog: Dialog


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding =
            FragmentProductListSearchProductTerlarisBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setProgress()
        setViewModelandData()
        setAdapter()
        setLifeCycleOwner()
        getData()
        setHide()
    }

    private fun setLifeCycleOwner() {
        viewLifecycleOwner.lifecycleScope.launch {
            adapter.loadStateFlow.collect { combinedLoadStates ->
                viewModel.setItemAmount(adapter.itemCount)
            }
        }
    }

    private fun setHide() {
        viewModel.itemCount.observe(requireActivity()) {
            if (it == 0) {
                binding.apply {
                    clProdukGaAda.visibility = View.VISIBLE
                    rvProductListTerlaris.visibility = View.GONE
                }
            } else {
                binding.apply {
                    clProdukGaAda.visibility = View.GONE
                    rvProductListTerlaris.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setItemCount() {
        adapter.addLoadStateListener { combinedLoadStates ->
            viewModel.setItemAmount(adapter.itemCount)
        }
    }

    private fun setProgress() {
        dialog = PresentationUtils.loadingAlertDialog(requireContext())
    }

    private fun getData() {
        lifecycleScope.launch {
            viewModel.getProductSearchProductOrder(
                dataName, PresentationUtils.ORDER_BY_DESCENDING, "sales_quantity", type
            ).collectLatest {
                adapter.submitData(it)
                setItemCount()
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
            adapter, dialog, requireContext(), ::getData, false, requireActivity()
        )

    }

    private fun setViewModelandData() {
        viewModel = (requireActivity() as ProductListSearchProdukActivity).getProductViewModel()
        dataName = (requireActivity() as ProductListSearchProdukActivity).getNameSearch()
        type = (requireActivity() as ProductListSearchProdukActivity).getDataType()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dialog.dismiss()
    }

}