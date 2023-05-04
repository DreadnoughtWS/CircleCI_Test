package com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.bannerproduct

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.academy.alfagiftmini.databinding.FragmentBannerProductTerlarisBinding
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.homepage.components.activity.banner.BannerPromoItemListActivity
import com.academy.alfagiftmini.presentation.homepage.components.adapter.productlist.ProductListGratisProductPagingAdapter
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductListViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class FragmentBannerProductTerlaris : Fragment() {
    private lateinit var binding: FragmentBannerProductTerlarisBinding
    private lateinit var viewModel: ProductListViewModel
    private lateinit var adapter: ProductListGratisProductPagingAdapter
    private var bannerId: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentBannerProductTerlarisBinding.inflate(inflater, container, false)
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
            viewModel.getBannerProduct(
                bannerId,
                PresentationUtils.ORDER_BY_ASCENDING,
                "sales_quantity",
                PresentationUtils.TYPE_BUKAN_PROMOSI
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
        viewModel = (requireActivity() as BannerPromoItemListActivity).getProductViewModel()
        bannerId = (requireActivity() as BannerPromoItemListActivity).getBannerIdValue()
    }


}