package com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.penawaranterbaik

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.FragmentPenawaranTerbaikPromosiBinding
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.homepage.activity.MainActivity
import com.academy.alfagiftmini.presentation.homepage.components.activity.productlist.ProductListPenawaranTerbaikActivity
import com.academy.alfagiftmini.presentation.homepage.components.adapter.productlist.ProductListGratisProductPagingAdapter
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductListViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class FragmentPenawaranTerbaikPromosi : Fragment() {
    private lateinit var binding: FragmentPenawaranTerbaikPromosiBinding
    private lateinit var adapter: ProductListGratisProductPagingAdapter
    private lateinit var viewModel: ProductListViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentPenawaranTerbaikPromosiBinding.inflate(inflater, container, false)
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
            viewModel.getProductGratisProduct(PresentationUtils.TYPE_PENAWARAN_TERBAIK_PROMOSI)
                .collectLatest {
                    adapter.submitData(it)
                }
        }
    }

    private fun setAdapter() {
        adapter = ProductListGratisProductPagingAdapter()
        binding.rvProductListPromosi.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvProductListPromosi.adapter = adapter
    }


    private fun setViewModel() {
        viewModel =
            (requireActivity() as ProductListPenawaranTerbaikActivity).getViewModelProductList()
    }


}