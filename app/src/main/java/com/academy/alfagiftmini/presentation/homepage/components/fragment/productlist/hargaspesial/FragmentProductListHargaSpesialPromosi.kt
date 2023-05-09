package com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.hargaspesial

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.academy.alfagiftmini.databinding.FragmentProductListPromosiHargaSpesialBinding
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.homepage.components.activity.productlist.ProductListHargaSpesialActivity
import com.academy.alfagiftmini.presentation.homepage.components.adapter.productlist.ProductListGratisProductPagingAdapter
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductListViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class FragmentProductListHargaSpesialPromosi(private val viewModel:ProductListViewModel) : Fragment() {
    private lateinit var binding: FragmentProductListPromosiHargaSpesialBinding
    private lateinit var adapter: ProductListGratisProductPagingAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProductListPromosiHargaSpesialBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        getData()

    }

    private fun getData() {
        lifecycleScope.launch {
            viewModel.getProductGratisProduct(PresentationUtils.TYPE_HARGA_SPESIAL).collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun setAdapter() {
        adapter = ProductListGratisProductPagingAdapter()
        binding.rvProductListPromosi.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvProductListPromosi.adapter = adapter
    }




}