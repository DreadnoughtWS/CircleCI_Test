package com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.penawaranterbaik

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.FragmentPenawaranTerbaikNamaProdukBinding
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.homepage.components.activity.productlist.ProductListPenawaranTerbaikActivity
import com.academy.alfagiftmini.presentation.homepage.components.adapter.productlist.ProductListGratisProductPagingAdapter
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductListViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class FragmentPenawaranTerbaikNamaProduk : Fragment(), TabLayout.OnTabSelectedListener {
    private lateinit var binding:FragmentPenawaranTerbaikNamaProdukBinding
    private lateinit var viewModel: ProductListViewModel
    private lateinit var adapter: ProductListGratisProductPagingAdapter

    var isClicked = true
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPenawaranTerbaikNamaProdukBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTab()
        setAdapter()
        getData(PresentationUtils.ORDER_BY_ASCENDING)
    }
    fun getData(order: String = "asc") {
        lifecycleScope.launch {
            viewModel.getProductGratisProductOrder(
                PresentationUtils.TYPE_PENAWARAN_TERBAIK, order, "product_name"
            ).collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun setAdapter() {
        adapter = ProductListGratisProductPagingAdapter()
        binding.rvProductListNamaProduk.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvProductListNamaProduk.adapter = adapter
    }

    private fun setTab() {
        (requireActivity() as ProductListPenawaranTerbaikActivity).getTab().addOnTabSelectedListener(this)
        viewModel = (requireActivity() as ProductListPenawaranTerbaikActivity).getViewModelProductList()
    }

    override fun onTabSelected(tab: TabLayout.Tab?) { }

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

}