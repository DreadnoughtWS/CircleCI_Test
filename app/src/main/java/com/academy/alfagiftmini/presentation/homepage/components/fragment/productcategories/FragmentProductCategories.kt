package com.academy.alfagiftmini.presentation.homepage.components.fragment.productcategories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.academy.alfagiftmini.databinding.FragmentProductCategoriesBinding
import com.academy.alfagiftmini.presentation.homepage.components.adapter.productcategories.CategoriesAdapter
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductCategoriesViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FragmentProductCategories (private val viewModel: ProductCategoriesViewModel): Fragment() {
    private lateinit var binding: FragmentProductCategoriesBinding
    private lateinit var categoriesAdapter: CategoriesAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRvCategories()
        setObserver()
    }

    private fun setObserver() {
        lifecycleScope.launch {
            viewModel.getAllCategories(this).collectLatest {
                categoriesAdapter.submitData(lifecycle, it)
            }
        }
    }

    private fun setRvCategories() {
        binding.apply{
            rvListCategories.layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.HORIZONTAL, false)
            categoriesAdapter = CategoriesAdapter()
            rvListCategories.adapter = categoriesAdapter
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductCategoriesBinding.inflate(inflater)
        return binding.root
    }
}