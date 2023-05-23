package com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.hargaspesial

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
import com.academy.alfagiftmini.databinding.FragmentProductListNamaProdukHargaSpesialBinding
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.homepage.activity.MainActivity
import com.academy.alfagiftmini.presentation.homepage.components.adapter.productlist.ProductListGratisProductPagingAdapter
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.FragmentHargaSpecial
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductListViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class FragmentProductListHargaSpesialNamaProduk : Fragment(), TabLayout.OnTabSelectedListener {
    private lateinit var binding: FragmentProductListNamaProdukHargaSpesialBinding
    private lateinit var viewModel: ProductListViewModel
    private lateinit var adapter: ProductListGratisProductPagingAdapter
    private lateinit var dialog: Dialog
    private var isClicked = true


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =
            FragmentProductListNamaProdukHargaSpesialBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setProgress()
        setTab()
        setAdapter()
        getData(PresentationUtils.ORDER_BY_ASCENDING)
    }

    private fun setProgress() {
        dialog = PresentationUtils.loadingAlertDialog(requireActivity())
    }

    private fun setTab() {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentHargaSpecial =
            fragmentManager.findFragmentByTag(FragmentHargaSpecial::class.java.simpleName) as FragmentHargaSpecial
        fragmentHargaSpecial.getTab().addOnTabSelectedListener(this)
        viewModel = (requireActivity() as MainActivity).getViewModelProductList()
    }

    fun getData(order: String = "asc") {
        lifecycleScope.launch {
            viewModel.getProductGratisProductOrder(
                PresentationUtils.TYPE_HARGA_SPESIAL, order, "product_name"
            ).collectLatest {
                adapter.submitData(it)
            }
        }
    }


    private fun setAdapter() {
        adapter = ProductListGratisProductPagingAdapter()
        binding.apply {
            rvProductListNamaProduk.layoutManager = GridLayoutManager(requireContext(), 2)
            rvProductListNamaProduk.adapter = adapter
        }

        PresentationUtils.adapterAddLoadStateListenerProduct(adapter, dialog, requireContext())

    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {

    }

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
