package com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.searchview

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.FragmentProductListSearchProductNamaProdukBinding
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.homepage.components.activity.productlist.ProductListSearchProdukActivity
import com.academy.alfagiftmini.presentation.homepage.components.adapter.productlist.ProductListGratisProductPagingAdapter
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductListViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class FragmentProductListSearchProductNamaProduk(private val tlGratisProduk: TabLayout) :
    Fragment(), TabLayout.OnTabSelectedListener {
    private lateinit var binding: FragmentProductListSearchProductNamaProdukBinding
    private lateinit var viewModel: ProductListViewModel
    private lateinit var adapter: ProductListGratisProductPagingAdapter
    private var dataName: String = ""
    var type: String = ""
    private var isClicked = true
    private lateinit var dialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding =
            FragmentProductListSearchProductNamaProdukBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setProgress()
        setViewModelandData()
        setAdapter()
        setLifeCycleOwner()
        getData(PresentationUtils.ORDER_BY_ASCENDING)
        setHide()
    }

    private fun setLifeCycleOwner() {
        viewLifecycleOwner.lifecycleScope.launch {
            adapter.loadStateFlow.collect { combinedLoadStates ->
                viewModel.setItemAmount(adapter.itemCount)
            }
        }
    }

    private fun setHide() {
        viewModel.itemCount.observe(requireActivity()) {
            if (it == 0) {
                binding.apply {
                    clProdukGaAda.visibility = View.VISIBLE
                    rvProductListPromosi.visibility = View.GONE
                }
            } else {
                binding.apply {
                    clProdukGaAda.visibility = View.GONE
                    rvProductListPromosi.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setProgress() {
        dialog = PresentationUtils.loadingAlertDialog(requireContext())
    }

    private fun getData(order: String = "asc") {
        lifecycleScope.launch {
            viewModel.getProductSearchProductOrder(
                dataName, order, "product_name", type
            ).collectLatest {
                adapter.submitData(it)
                setItemCount()
            }
        }
    }

    private fun setItemCount() {
        adapter.addLoadStateListener { combinedLoadStates ->
            viewModel.setItemAmount(adapter.itemCount)
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

    private fun setViewModelandData() {
        viewModel = (requireActivity() as ProductListSearchProdukActivity).getProductViewModel()
        dataName = (requireActivity() as ProductListSearchProdukActivity).getNameSearch()
        tlGratisProduk.addOnTabSelectedListener(this)
        type = (requireActivity() as ProductListSearchProdukActivity).getDataType()
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {}

    override fun onTabUnselected(tab: TabLayout.Tab?) {}

    override fun onTabReselected(tab: TabLayout.Tab?) {
        if (tab?.position == 1) {
            if (isClicked) {
                isClicked = false

                tab.customView?.findViewById<ImageView>(R.id.iv_tab_item_up)
                    ?.setImageResource(R.drawable.arrow_up_tab_item)
                tab.customView?.findViewById<ImageView>(R.id.iv_tab_item_down)
                    ?.setImageResource(R.drawable.arrow_down_tab_item_blue)
                getData(PresentationUtils.ORDER_BY_DESCENDING)

            } else {
                isClicked = true

                tab.customView?.findViewById<ImageView>(R.id.iv_tab_item_up)
                    ?.setImageResource(R.drawable.arrow_up_tab_item_blue)
                tab.customView?.findViewById<ImageView>(R.id.iv_tab_item_down)
                    ?.setImageResource(R.drawable.arrow_down_tab_item)
                getData(PresentationUtils.ORDER_BY_ASCENDING)

            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dialog.dismiss()
    }
}