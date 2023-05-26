package com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.searchview

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.academy.alfagiftmini.databinding.FragmentProductListSearchProductPromosiBinding
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.homepage.components.activity.productlist.ProductListSearchProdukActivity
import com.academy.alfagiftmini.presentation.homepage.components.adapter.productlist.ProductListGratisProductPagingAdapter
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductListViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FragmentProductListSearchProductPromosi : Fragment() {
    private lateinit var binding: FragmentProductListSearchProductPromosiBinding
    private lateinit var viewModel: ProductListViewModel
    private lateinit var adapter: ProductListGratisProductPagingAdapter
    var dataName: String = ""
    var type: String = ""
    private lateinit var dialog: Dialog


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductListSearchProductPromosiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setProgress()
        setViewModelandData()
        setAdapter()
        setLifeCycleOwner()
        getDataFromApi()
        setHide()
    }

    private fun setHide() {
        viewModel.itemCount.observe(requireActivity()){
            if(it == 0){
                binding.apply {
                    clProdukGaAda.visibility = View.VISIBLE
                    rvProductListPromosi.visibility = View.GONE
                }
            }else{
                binding.apply {
                    clProdukGaAda.visibility = View.GONE
                    rvProductListPromosi.visibility = View.VISIBLE
                }
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

    private fun setItemCount() {
        adapter.addLoadStateListener { combinedLoadStates ->
            viewModel.setItemAmount(adapter.itemCount)
        }
    }

    private fun setProgress() {
        dialog = PresentationUtils.loadingAlertDialog(requireContext())
    }


    private fun getDataFromApi() {
        lifecycleScope.launch {
            viewModel.getProductSearchProduct(name = dataName, type).collectLatest {
                PresentationUtils.setLoading(false, dialog)
                adapter.submitData(it)
                setItemCount()
            }
        }
    }

    private fun setAdapter() {
        adapter = ProductListGratisProductPagingAdapter()
        binding.apply {
            rvProductListPromosi.layoutManager = GridLayoutManager(requireContext(), 2)
            rvProductListPromosi.adapter = adapter
        }
        PresentationUtils.adapterAddLoadStateListenerProduct(
            adapter, dialog, requireContext(), ::getDataFromApi, false, requireActivity()
        )
    }

    private fun setViewModelandData() {
        viewModel = (requireActivity() as ProductListSearchProdukActivity).getProductViewModel()
        dataName = (requireActivity() as ProductListSearchProdukActivity).getNameSearch()
        type = (requireActivity() as ProductListSearchProdukActivity).getDataType()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dialog.dismiss()
    }

}