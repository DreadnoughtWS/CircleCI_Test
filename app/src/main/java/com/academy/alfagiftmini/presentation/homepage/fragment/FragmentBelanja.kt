package com.academy.alfagiftmini.presentation.homepage.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.FragmentBelanjaBinding
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.factory.PresentationFactory
import com.academy.alfagiftmini.presentation.homepage.activity.MainActivity
import com.academy.alfagiftmini.presentation.homepage.components.adapter.productlist.ProductListGratisProductPagingAdapter
import com.academy.alfagiftmini.presentation.homepage.components.adapter.productlist.belanja.ProductListBelanjaPagingAdapter
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductListViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class FragmentBelanja() : Fragment() {
    private lateinit var binding: FragmentBelanjaBinding
    private lateinit var productListViewModel: ProductListViewModel
    private lateinit var shoppingListAdapter: ProductListBelanjaPagingAdapter
    private lateinit var rekomendasiListAdapter: ProductListGratisProductPagingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentBelanjaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar()
        setViewModel()
        setAdapter()
        getData()
    }

    private fun getData() {
        lifecycleScope.launch {
            productListViewModel.getProductGratisProduct(PresentationUtils.TYPE_SHOPPING_LIST_BELANJA)
                .collectLatest {
                    shoppingListAdapter.submitData(it)
                }
        }
        lifecycleScope.launch {
            productListViewModel.getProductGratisProduct(PresentationUtils.TYPE_REKOMENDASI_BELANJA)
                .collectLatest {
                    rekomendasiListAdapter.submitData(it)
                }
        }
    }

    private fun setAdapter() {
        rekomendasiListAdapter = ProductListGratisProductPagingAdapter()
        binding.rvRekomendasiProduct.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvRekomendasiProduct.isNestedScrollingEnabled = false
        binding.rvRekomendasiProduct.adapter = rekomendasiListAdapter

        shoppingListAdapter = ProductListBelanjaPagingAdapter()
        binding.rvShoppingList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvShoppingList.adapter = shoppingListAdapter
    }

    private fun setViewModel() {
        productListViewModel = (requireActivity() as MainActivity).getViewModelProductList()
    }


    private fun setToolbar() {
        binding.tvToolbar.text = "Belanja"

        binding.scrollView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY >= 4750) {
                with(binding) {
                    tvShoppingList.visibility = View.GONE
                    cbShoppingList.visibility = View.GONE
                    tvPilihSemuaProduk.visibility = View.GONE
                    tvSilahkanCekListProduk.visibility = View.GONE
                    clShoppingList.setBackgroundColor(getResources().getColor(R.color.transparant))
                    btnLihatShoppingList.visibility = View.VISIBLE
                    btnProdukRekomendasi.visibility = View.GONE
                }
            } else {
                with(binding) {
                    btnLihatShoppingList.visibility = View.GONE
                    btnProdukRekomendasi.visibility = View.VISIBLE
                    tvShoppingList.visibility = View.VISIBLE
                    cbShoppingList.visibility = View.VISIBLE
                    tvPilihSemuaProduk.visibility = View.VISIBLE
                    tvSilahkanCekListProduk.visibility = View.VISIBLE
                    clShoppingList.setBackgroundColor(getResources().getColor(R.color.very_light_gray))
                }
            }
        }
    }


}