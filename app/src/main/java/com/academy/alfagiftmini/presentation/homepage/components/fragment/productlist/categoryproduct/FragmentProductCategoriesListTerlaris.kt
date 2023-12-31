package com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.categoryproduct

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.academy.alfagiftmini.databinding.FragmentProductCategoryListBinding
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.homepage.components.adapter.productlist.ProductListGratisProductPagingAdapter
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductListViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FragmentProductCategoriesListTerlaris(
    private val viewModel: ProductListViewModel,
    private val subCategory: String,
    private val category: String
) : Fragment() {
    private lateinit var binding: FragmentProductCategoryListBinding
    private lateinit var adapter: ProductListGratisProductPagingAdapter
    private lateinit var dialog: Dialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setProgress()
        setRv()
        setLifeCycleOwner()
        setObserver()
        setHide()
    }

    private fun setHide() {
        viewModel.itemCount.observe(requireActivity()) {
            if (it == 0) {
                binding.clProdukGaAda.visibility = View.VISIBLE
                binding.rvProductListNamaProduk.visibility = View.GONE
            } else {
                binding.clProdukGaAda.visibility = View.GONE
                binding.rvProductListNamaProduk.visibility = View.VISIBLE
            }
        }
    }

    private fun setLifeCycleOwner() {
        viewLifecycleOwner.lifecycleScope.launch {
            adapter.loadStateFlow.collect { combinedLoadStates ->
                viewModel.setItemAmount(adapter.itemCount)
            }
        }
    }

    private fun setProgress() {
        dialog = PresentationUtils.loadingAlertDialog(requireContext())
    }

    private fun setObserver() {
        lifecycleScope.launch {
            viewModel.getProductByCategory(
                this,
                subCategory,
                category,
                PresentationUtils.ORDER_BY_ASCENDING,
                "sales_quantity",
                PresentationUtils.TYPE_BUKAN_PROMOSI
            ).collectLatest {
                adapter.submitData(lifecycle, it)
                setItemCount()
            }
        }

    }

    private fun setItemCount() {
        adapter.addLoadStateListener { combinedLoadStates ->
            viewModel.setItemAmount(adapter.itemCount)
        }
    }

    private fun setRv() {
        binding.apply {
            rvProductListNamaProduk.layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = ProductListGratisProductPagingAdapter()
            rvProductListNamaProduk.adapter = adapter
        }
        PresentationUtils.adapterAddLoadStateListenerProduct(
            adapter, dialog, requireContext(), ::setObserver, false, requireActivity()
        )


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductCategoryListBinding.inflate(inflater)
        return binding.root
    }
}