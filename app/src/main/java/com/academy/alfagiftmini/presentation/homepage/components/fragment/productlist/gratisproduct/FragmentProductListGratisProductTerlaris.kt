package com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.gratisproduct

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.FragmentProductListGratisProductTerlarisBinding
import com.academy.alfagiftmini.databinding.FragmentProductListTerlarisHargaSpesialBinding
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.homepage.components.activity.productlist.ProductListGratisProductActivity
import com.academy.alfagiftmini.presentation.homepage.components.activity.productlist.ProductListHargaSpesialActivity
import com.academy.alfagiftmini.presentation.homepage.components.adapter.ProductListGratisProductPagingAdapter
import com.academy.alfagiftmini.presentation.homepage.components.adapter.ProductListPagingAdapter
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductListViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class FragmentProductListGratisProductTerlaris : Fragment() {
    private lateinit var binding: FragmentProductListGratisProductTerlarisBinding
    private lateinit var viewModel: ProductListViewModel
    private lateinit var adapter: ProductListGratisProductPagingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductListGratisProductTerlarisBinding.inflate(inflater,container,false)
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
                PresentationUtils.TYPE_GRATIS_PRODUK,
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
        viewModel = (requireActivity() as ProductListGratisProductActivity).getProductViewModel()

    }
}