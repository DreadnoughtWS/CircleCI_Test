package com.academy.alfagiftmini.presentation.homepage.components.fragment.productcategories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.academy.alfagiftmini.databinding.FragmentProductCategoryListBinding
import com.academy.alfagiftmini.presentation.homepage.components.adapter.productlist.ProductListGratisProductPagingAdapter
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductCategoriesViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FragmentProductCategoriesDetail (private val viewModel: ProductCategoriesViewModel, private val subCategory: String, private val category: String): Fragment() {
    private lateinit var binding: FragmentProductCategoryListBinding
    private lateinit var adapter: ProductListGratisProductPagingAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRv()
        setObserver()
    }

    private fun setObserver() {
        lifecycleScope.launch {
            viewModel.getProductByCategory(this, subCategory, category).collectLatest {
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