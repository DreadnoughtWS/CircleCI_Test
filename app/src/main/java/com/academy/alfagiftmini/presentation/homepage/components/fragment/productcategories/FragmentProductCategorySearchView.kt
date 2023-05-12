package com.academy.alfagiftmini.presentation.homepage.components.fragment.productcategories

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.academy.alfagiftmini.databinding.FragmentRvListKategoriBinding
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.homepage.components.activity.productcategories.ProductCategoriesActivity
import com.academy.alfagiftmini.presentation.homepage.components.activity.productlist.ProductListSearchProdukActivity
import com.academy.alfagiftmini.presentation.homepage.components.adapter.productcategories.CategoriesSearchAdapter
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductCategoriesViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FragmentProductCategorySearchView: Fragment(), CategoriesSearchAdapter.SetOnCategoryClick {
    private lateinit var binding: FragmentRvListKategoriBinding
    private lateinit var viewModel: ProductCategoriesViewModel
    private lateinit var adapter: CategoriesSearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRvListKategoriBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getViewModel()
        setRv()
        getData()
    }

    private fun getData() {
        lifecycleScope.launch{
            viewModel.getAllCategories(this, 5).collectLatest {
                adapter.submitData(lifecycle, it)
            }
        }
    }

    private fun getViewModel() {
        viewModel = (requireActivity() as ProductListSearchProdukActivity).getCategoryProductViewModel()
    }

    private fun setRv() {
        binding.apply {
            rvKategoriPilihan.layoutManager = LinearLayoutManager(requireContext())
            adapter = CategoriesSearchAdapter()
            adapter.listener = this@FragmentProductCategorySearchView
            rvKategoriPilihan.adapter = adapter
        }
    }

    override fun onCategoryClicked(position: Int) {
        val intent = Intent(requireContext(), ProductCategoriesActivity::class.java)
        intent.putExtra(PresentationUtils.CATEGORIES_KEY, adapter.getItemObject(position))
        startActivity(intent)
    }
}