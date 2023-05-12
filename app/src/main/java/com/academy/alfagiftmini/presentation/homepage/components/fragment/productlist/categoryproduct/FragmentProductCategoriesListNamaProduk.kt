package com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.categoryproduct

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
import com.academy.alfagiftmini.presentation.homepage.activity.MainActivity
import com.academy.alfagiftmini.presentation.homepage.components.adapter.productlist.ProductListGratisProductPagingAdapter
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productcategories.FragmentProductCategoriesDetail
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.FragmentHargaSpecial
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductListViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FragmentProductCategoriesListNamaProduk (private val viewModel: ProductListViewModel, private val subCategory: String, private val category: String): Fragment(), TabLayout.OnTabSelectedListener {
    private lateinit var binding: FragmentProductCategoryListBinding
    private lateinit var adapter: ProductListGratisProductPagingAdapter
    var isClicked = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTab()
        setRv()
        getData(PresentationUtils.ORDER_BY_ASCENDING)
    }

    private fun setTab() {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentCategoriesDetail =
            fragmentManager.findFragmentByTag(FragmentProductCategoriesDetail::class.java.simpleName) as FragmentProductCategoriesDetail
        fragmentCategoriesDetail.getTab().addOnTabSelectedListener(this)
    }

    private fun getData(order: String = "asc") {
        lifecycleScope.launch {
            viewModel.getProductByCategory(this, subCategory, category, "product_name", order, PresentationUtils.TYPE_BUKAN_PROMOSI).collectLatest {
                adapter.submitData(lifecycle, it)
            }
        }

    }

    private fun setRv() {
        binding.apply {
            rvProductListNamaProduk.layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = ProductListGratisProductPagingAdapter()
            rvProductListNamaProduk.adapter = adapter
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
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