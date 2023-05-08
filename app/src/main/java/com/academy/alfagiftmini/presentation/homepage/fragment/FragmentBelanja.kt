package com.academy.alfagiftmini.presentation.homepage.fragment

import android.content.Intent
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
import com.academy.alfagiftmini.presentation.homepage.components.activity.productcategories.ProductCategoriesActivity
import com.academy.alfagiftmini.presentation.homepage.components.activity.productlist.ProductListSearchProdukActivity
import com.academy.alfagiftmini.presentation.homepage.components.adapter.productcategories.CategoriesAdapter
import com.academy.alfagiftmini.presentation.homepage.components.adapter.productlist.ProductListGratisProductPagingAdapter
import com.academy.alfagiftmini.presentation.homepage.components.adapter.productlist.belanja.ProductListBelanjaPagingAdapter
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductCategoriesViewModel
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductListViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class FragmentBelanja : Fragment(), CategoriesAdapter.setOnItemClicked {
    private lateinit var binding: FragmentBelanjaBinding
    private lateinit var productListViewModel: ProductListViewModel
    private lateinit var shoppingListAdapter: ProductListBelanjaPagingAdapter
    private lateinit var rekomendasiListAdapter: ProductListGratisProductPagingAdapter
    private lateinit var productCategoriesviewModel: ProductCategoriesViewModel
    private lateinit var categoriesAdapter: CategoriesAdapter


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
        setBtnScroll()
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
        lifecycleScope.launch {
            productCategoriesviewModel.getAllCategories(this).collectLatest {
                categoriesAdapter.submitData(lifecycle, it)
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

        categoriesAdapter = CategoriesAdapter(this@FragmentBelanja)
        binding.rvCategoryBelanja.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvCategoryBelanja.adapter = categoriesAdapter
    }

    private fun setViewModel() {
        productListViewModel = (requireActivity() as MainActivity).getViewModelProductList()
        productCategoriesviewModel =
            (requireActivity() as MainActivity).getViewModelProductCategories()
    }


    private fun setToolbar() {

        binding.tvToolbar.text = "Belanja"
        binding.btnSearchProduct.setOnClickListener {
            startActivity(Intent(requireContext(), ProductListSearchProdukActivity::class.java))
        }

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

        binding.ivWindowToolbar.setOnClickListener {
            if (binding.clCategoryBelanja.visibility == View.GONE) {
                binding.clCategoryBelanja.visibility = View.VISIBLE
            } else {
                binding.clCategoryBelanja.visibility = View.GONE
            }
        }
    }

    private fun setBtnScroll() {
        binding.btnLihatShoppingList.setOnClickListener {
            binding.scrollView.smoothScrollTo(0, 0)
        }
        binding.btnProdukRekomendasi.setOnClickListener {
            binding.scrollView.smoothScrollTo(0, 4750)
        }
    }

    override fun onCategoryClicked(position: Int) {
        val intent = Intent(requireContext(), ProductCategoriesActivity::class.java)
        intent.putExtra(PresentationUtils.CATEGORIES_KEY, categoriesAdapter.getItemObject(position))
        startActivity(intent)
    }


}