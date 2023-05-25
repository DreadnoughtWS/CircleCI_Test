package com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.FragmentTebusMurahBinding
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.homepage.activity.MainActivity
import com.academy.alfagiftmini.presentation.homepage.components.adapter.productlist.ProductListTebusMurahAdapter
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductListViewModel
import kotlinx.coroutines.launch


class FragmentTebusMurah : Fragment() {
    private lateinit var binding: FragmentTebusMurahBinding
    private lateinit var tebusMurahAdapter: ProductListTebusMurahAdapter
    private lateinit var productListViewModel: ProductListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentTebusMurahBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModel()
        setAdapter()
        getData()
    }

    private fun getData() {
        lifecycleScope.launch {
            productListViewModel.getProductListTebusMurah().collect {
                tebusMurahAdapter.updateDaata(it)
                if (it.isEmpty()) {
                    val dialogg = PresentationUtils.noInternetDialog(requireContext())
                    dialogg.setPositiveButton(getString(R.string.retry)) { _, _ ->
                        getData()
                    }
                    dialogg.setNegativeButton(getString(R.string.close)) { dialog, _ ->
                        dialog.cancel()
                        requireActivity().finish()
                    }
                    PresentationUtils.shownoInternetDialog(dialogg)
                }
            }
        }
    }

    private fun setAdapter() {
        tebusMurahAdapter = ProductListTebusMurahAdapter()
        binding.apply {
            rvProductListTebusMurah.layoutManager = LinearLayoutManager(requireContext())
            rvProductListTebusMurah.adapter = tebusMurahAdapter
        }
    }

    private fun setViewModel() {
        productListViewModel = (requireActivity() as MainActivity).getViewModelProductList()
    }


}