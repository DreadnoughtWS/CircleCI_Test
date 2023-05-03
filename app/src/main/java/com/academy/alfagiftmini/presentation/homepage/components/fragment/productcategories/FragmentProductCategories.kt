package com.academy.alfagiftmini.presentation.homepage.components.fragment.productcategories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.academy.alfagiftmini.databinding.FragmentProductCategoriesBinding
import com.academy.alfagiftmini.presentation.homepage.components.adapter.productcategories.CategoriesAdapter

class FragmentProductCategories: Fragment() {
    private lateinit var binding: FragmentProductCategoriesBinding
    private lateinit var categoriesAdapter: CategoriesAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRvCategories()
        setObserver()
    }

    private fun setObserver() {

    }

    private fun setRvCategories() {
        binding.apply{
            rvListCategories.layoutManager = GridLayoutManager(requireActivity(), 2)
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