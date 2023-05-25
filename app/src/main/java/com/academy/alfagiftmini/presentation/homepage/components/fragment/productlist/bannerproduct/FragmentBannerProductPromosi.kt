package com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.bannerproduct

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.academy.alfagiftmini.databinding.FragmentBannerProductPromosiBinding
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.homepage.components.activity.banner.BannerPromoItemListActivity
import com.academy.alfagiftmini.presentation.homepage.components.adapter.productlist.ProductListGratisProductPagingAdapter
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductListViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class FragmentBannerProductPromosi : Fragment() {
    private lateinit var binding: FragmentBannerProductPromosiBinding
    private lateinit var viewModel: ProductListViewModel
    private lateinit var adapter: ProductListGratisProductPagingAdapter
    private var bannerId: Int = -1
    private lateinit var dialog: Dialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentBannerProductPromosiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setProgress()
        setViewModel()
        setAdapter()
        getData()
    }

    private fun setProgress() {
        dialog = PresentationUtils.loadingAlertDialog(requireContext())
    }

    private fun getData() {
        lifecycleScope.launch {
            viewModel.getBannerProduct(
                bannerId,
                "product_name",
                PresentationUtils.ORDER_BY_ASCENDING,
                PresentationUtils.TYPE_PROMOSI
            ).collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun setAdapter() {
        adapter = ProductListGratisProductPagingAdapter()
        binding.apply {
            rvProductListPromosi.layoutManager = GridLayoutManager(requireContext(), 2)
            rvProductListPromosi.adapter = adapter
        }
        PresentationUtils.adapterAddLoadStateListenerProduct(
            adapter, dialog, requireContext(), ::getData, false, requireActivity()
        )

    }

    private fun setViewModel() {
        viewModel = (requireActivity() as BannerPromoItemListActivity).getProductViewModel()
        bannerId = (requireActivity() as BannerPromoItemListActivity).getBannerIdValue()
    }


}