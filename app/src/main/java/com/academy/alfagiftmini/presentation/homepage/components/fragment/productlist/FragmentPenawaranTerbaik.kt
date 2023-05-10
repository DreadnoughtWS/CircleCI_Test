package com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.FragmentPenawaranTerbaikBinding
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.homepage.activity.MainActivity
import com.academy.alfagiftmini.presentation.homepage.components.adapter.productlist.ProductListGratisProductPagingAdapter
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductListViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FragmentPenawaranTerbaik(private val viewModel: ProductListViewModel) : Fragment() {
    private lateinit var binding: FragmentPenawaranTerbaikBinding
    private lateinit var adapter: ProductListGratisProductPagingAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentPenawaranTerbaikBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        getData()
    }

    private fun getData() {
        lifecycleScope.launch {
            viewModel.getProductGratisProduct(PresentationUtils.TYPE_PENAWARAN_TERBAIK)
                .collectLatest {
                    adapter.submitData(it)
                }
        }
    }

    private fun setAdapter() {
        adapter = ProductListGratisProductPagingAdapter()
        binding.apply {
            rvPenawaranTerbaik.layoutManager = GridLayoutManager(
                requireContext(), 1, GridLayoutManager.HORIZONTAL, false
            )

            rvPenawaranTerbaik.adapter = adapter
        }
    }
}