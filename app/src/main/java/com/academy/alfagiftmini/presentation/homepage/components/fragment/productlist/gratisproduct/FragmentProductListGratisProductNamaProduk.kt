package com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.gratisproduct

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
import com.academy.alfagiftmini.databinding.FragmentProductListGratisProductNamaProdukBinding
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.homepage.activity.MainActivity
import com.academy.alfagiftmini.presentation.homepage.components.adapter.productlist.ProductListGratisProductPagingAdapter
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductListViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class FragmentProductListGratisProductNamaProduk(private val tlGratisProduk: TabLayout) :
    Fragment(), TabLayout.OnTabSelectedListener {
    private lateinit var binding: FragmentProductListGratisProductNamaProdukBinding
    private lateinit var dialog: Dialog
    private lateinit var viewModel: ProductListViewModel
    private lateinit var adapter: ProductListGratisProductPagingAdapter

    private var isClicked = true
    var order: String = "asc"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding =
            FragmentProductListGratisProductNamaProdukBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setProgress()
        setTab()
        setAdapter()
        getData()
    }

    private fun setProgress() {
        dialog = PresentationUtils.loadingAlertDialog(requireActivity())
    }

    fun getData() {
        lifecycleScope.launch {
            viewModel.getProductGratisProductOrder(
                type = PresentationUtils.TYPE_GRATIS_PRODUK, order = order, sort = "product_name"
            ).collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun setAdapter() {
        adapter = ProductListGratisProductPagingAdapter()
        binding.apply {
            rvProductListNamaProduk.layoutManager = GridLayoutManager(requireActivity(), 2)
            rvProductListNamaProduk.adapter = adapter
        }
        PresentationUtils.adapterAddLoadStateListenerProduct(
            adapter,
            dialog,
            requireContext(),
            ::getData,
            true,
            requireActivity()
        )

    }

    private fun setTab() {
        tlGratisProduk.addOnTabSelectedListener(this)
        viewModel = (requireActivity() as MainActivity).getViewModelProductList()
    }


    override fun onTabSelected(tab: TabLayout.Tab?) {
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {

    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
        if (tab?.position == 1) {
            if (isClicked) {
                isClicked = false
                order = "desc"
                println("TAB DESC")
                tab.customView?.findViewById<ImageView>(R.id.iv_tab_item_up)
                    ?.setImageResource(R.drawable.arrow_up_tab_item)
                tab.customView?.findViewById<ImageView>(R.id.iv_tab_item_down)
                    ?.setImageResource(R.drawable.arrow_down_tab_item_blue)
                getData()


            } else {
                isClicked = true
                order = "asc"
                println("TAB ASC")
                tab.customView?.findViewById<ImageView>(R.id.iv_tab_item_up)
                    ?.setImageResource(R.drawable.arrow_up_tab_item_blue)
                tab.customView?.findViewById<ImageView>(R.id.iv_tab_item_down)
                    ?.setImageResource(R.drawable.arrow_down_tab_item)
                getData()

            }

        }
    }


}