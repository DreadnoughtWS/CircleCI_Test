package com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.categoryproduct

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
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductCategoriesViewModel
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductListViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FragmentProductCategoriesListTerlaris (private val viewModel: ProductListViewModel, private val subCategory: String, private val category: String): Fragment() {
    private lateinit var binding: FragmentProductCategoryListBinding
    private lateinit var adapter: ProductListGratisProductPagingAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRv()
        setObserver()
    }

    private fun setObserver() {
        lifecycleScope.launch {
            viewModel.getProductByCategory(this, subCategory, category, PresentationUtils.ORDER_BY_ASCENDING, "sales_quantity", PresentationUtils.TYPE_BUKAN_PROMOSI).collectLatest {
                adapter.submitData(lifecycle, it)
            }
        }

    }

    private fun setRv() {
        binding.apply {
            rvProductListNamaProduk.layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = ProductListGratisProductPagingAdapter()
            rvProductListNamaProduk.adapter = adapter
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductCategoryListBinding.inflate(inflater)
        return binding.root
    }
}