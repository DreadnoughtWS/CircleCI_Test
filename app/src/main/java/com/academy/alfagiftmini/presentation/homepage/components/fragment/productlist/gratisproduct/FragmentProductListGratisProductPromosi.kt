package com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.gratisproduct

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.FragmentProductListGratisProductPromosiBinding
import com.academy.alfagiftmini.databinding.FragmentProductListPromosiHargaSpesialBinding
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.homepage.components.activity.productlist.ProductListGratisProductActivity
import com.academy.alfagiftmini.presentation.homepage.components.activity.productlist.ProductListHargaSpesialActivity
import com.academy.alfagiftmini.presentation.homepage.components.adapter.ProductListGratisProductPagingAdapter
import com.academy.alfagiftmini.presentation.homepage.components.adapter.ProductListPagingAdapter
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductListViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FragmentProductListGratisProductPromosi : Fragment() {
    private lateinit var binding: FragmentProductListGratisProductPromosiBinding
    private lateinit var viewModel: ProductListViewModel
    private lateinit var adapter: ProductListGratisProductPagingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductListGratisProductPromosiBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setViewModel()
        setAdapter()
        getData()
    }

    private fun getData() {
        println("MASUK3")
        lifecycleScope.launch {
            viewModel.getProductGratisProduct(PresentationUtils.TYPE_GRATIS_PRODUK).collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun setAdapter() {
        println("MASUK2")
        adapter = ProductListGratisProductPagingAdapter()
        binding.rvProductListPromosi.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvProductListPromosi.adapter = adapter
    }

    private fun setViewModel() {
        println("MASUK 1")
        viewModel = (requireActivity() as ProductListGratisProductActivity).getProductViewModel()

    }


}