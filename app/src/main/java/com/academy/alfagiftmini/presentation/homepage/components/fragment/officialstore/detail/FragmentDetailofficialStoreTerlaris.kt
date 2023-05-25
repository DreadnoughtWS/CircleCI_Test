package com.academy.alfagiftmini.presentation.homepage.components.fragment.officialstore.detail

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.academy.alfagiftmini.databinding.FragmentDetailofficialStoreTerlarisBinding
import com.academy.alfagiftmini.domain.officialstore.model.OfficialStoreDomainItemModel
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.homepage.components.activity.officialstore.DetailOfficialStoreActivity
import com.academy.alfagiftmini.presentation.homepage.components.adapter.productlist.ProductListGratisProductPagingAdapter
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductListViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class FragmentDetailofficialStoreTerlaris : Fragment() {
    private lateinit var binding: FragmentDetailofficialStoreTerlarisBinding
    private lateinit var viewModel: ProductListViewModel
    private lateinit var adapter: ProductListGratisProductPagingAdapter
    private var data: OfficialStoreDomainItemModel? = null
    private lateinit var dialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailofficialStoreTerlarisBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setProgress()
        setViewModelandData()
        setAdapter()
        getData()
    }

    private fun setProgress() {
        dialog = PresentationUtils.loadingAlertDialog(requireContext())
    }

    private fun getData() {
        lifecycleScope.launch {
            data?.let { data ->
                viewModel.getDetailOffiialStoreOrder(
                    PresentationUtils.ORDER_BY_DESCENDING, "sales_quantity", data.id
                ).collectLatest {
                    adapter.submitData(it)
                }
            }

        }
    }

    private fun setAdapter() {
        adapter = ProductListGratisProductPagingAdapter()
        binding.apply {
            rvProductListTerlaris.layoutManager = GridLayoutManager(requireContext(), 2)
            rvProductListTerlaris.adapter = adapter
        }
        PresentationUtils.adapterAddLoadStateListenerProduct(
            adapter, dialog, requireContext(), ::getData, false, requireActivity()
        )

    }

    private fun setViewModelandData() {
        viewModel = (requireActivity() as DetailOfficialStoreActivity).getProductViewModel()
        data = (requireActivity() as DetailOfficialStoreActivity).getDataModel()

    }


}