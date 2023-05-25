package com.academy.alfagiftmini.presentation.homepage.components.fragment.officialstore.detail

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.academy.alfagiftmini.databinding.FragmentDetailofficialStorePromosiBinding
import com.academy.alfagiftmini.domain.officialstore.model.OfficialStoreDomainItemModel
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.homepage.components.activity.officialstore.DetailOfficialStoreActivity
import com.academy.alfagiftmini.presentation.homepage.components.adapter.productlist.ProductListGratisProductPagingAdapter
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductListViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FragmentDetailofficialStorePromosi : Fragment() {
    private lateinit var binding: FragmentDetailofficialStorePromosiBinding
    private lateinit var viewModel: ProductListViewModel
    private lateinit var adapter: ProductListGratisProductPagingAdapter
    private lateinit var dialog: Dialog
    private var data: OfficialStoreDomainItemModel? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailofficialStorePromosiBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setProgress()
        setViewModel()
        setAdapter()
        getDataFromActivity()
        getDataFromApi()
    }

    private fun setProgress() {
        dialog = PresentationUtils.loadingAlertDialog(requireContext())
    }

    private fun getDataFromActivity() {
        data = (requireActivity() as DetailOfficialStoreActivity).getDataModel()
    }


    private fun getDataFromApi() {
        lifecycleScope.launch {
            data?.let {
                viewModel.getDetailOfficialStorePromosiProduct(officialStoreId = it.id)
                    .collectLatest {
                        adapter.submitData(it)
                    }
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
            adapter, dialog, requireContext(), ::getDataFromApi, false, requireActivity()
        )
    }

    private fun setViewModel() {
        viewModel = (requireActivity() as DetailOfficialStoreActivity).getProductViewModel()
    }

}