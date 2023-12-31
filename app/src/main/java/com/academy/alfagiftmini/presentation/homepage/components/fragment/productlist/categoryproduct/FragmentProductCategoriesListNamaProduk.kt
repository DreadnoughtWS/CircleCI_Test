package com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.categoryproduct

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.FragmentProductCategoryListBinding
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.homepage.components.adapter.productlist.ProductListGratisProductPagingAdapter
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productcategories.FragmentProductCategoriesDetail
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductListViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FragmentProductCategoriesListNamaProduk(
    private val viewModel: ProductListViewModel,
    private val subCategory: String,
    private val category: String
) : Fragment(), TabLayout.OnTabSelectedListener {
    private lateinit var binding: FragmentProductCategoryListBinding
    private lateinit var adapter: ProductListGratisProductPagingAdapter
    private var isClicked = true
    private lateinit var dialog: Dialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setProgress()
        setTab()
        setRv()
        setLifeCycleOwner()
        getData(PresentationUtils.ORDER_BY_ASCENDING)
        setHide()
    }

    private fun setHide() {
        viewModel.itemCount.observe(requireActivity()){
            if (it == 0) {
                binding.clProdukGaAda.visibility = View.VISIBLE
                binding.rvProductListNamaProduk.visibility = View.GONE
            } else {
                binding.clProdukGaAda.visibility = View.GONE
                binding.rvProductListNamaProduk.visibility = View.VISIBLE
            }
        }
    }

    private fun setLifeCycleOwner() {
        viewLifecycleOwner.lifecycleScope.launch {
            adapter.loadStateFlow.collect { combinedLoadStates ->
                viewModel.setItemAmount(adapter.itemCount)
            }
        }
    }

    private fun setProgress() {
        dialog = PresentationUtils.loadingAlertDialog(requireContext())
    }

    private fun setTab() {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentCategoriesDetail =
            fragmentManager.findFragmentByTag(FragmentProductCategoriesDetail::class.java.simpleName) as FragmentProductCategoriesDetail
        fragmentCategoriesDetail.getTab().addOnTabSelectedListener(this)
    }

    private fun getData(order: String = "asc") {
        lifecycleScope.launch {
            viewModel.getProductByCategory(
                this,
                subCategory,
                category,
                "product_name",
                order,
                PresentationUtils.TYPE_BUKAN_PROMOSI
            ).collectLatest {
                adapter.submitData(lifecycle, it)
                setItemCount()
            }
        }

    }

    private fun setItemCount() {
        adapter.addLoadStateListener { combinedLoadStates ->
            viewModel.setItemAmount(adapter.itemCount)
        }
    }

    private fun setRv() {
        binding.apply {
            rvProductListNamaProduk.layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = ProductListGratisProductPagingAdapter()
            rvProductListNamaProduk.adapter = adapter
        }
        PresentationUtils.adapterAddLoadStateListenerProduct(
            adapter, dialog, requireContext(), ::getData, false, requireActivity()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductCategoryListBinding.inflate(inflater)
        return binding.root
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
}