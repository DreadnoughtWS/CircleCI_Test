package com.academy.alfagiftmini.presentation.homepage.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.FragmentBerandaBinding
import com.academy.alfagiftmini.presentation.factory.PresentationFactory
import com.academy.alfagiftmini.presentation.homepage.activity.MainActivity
import com.academy.alfagiftmini.presentation.homepage.components.activity.productlist.ProductListSearchProdukActivity
import com.academy.alfagiftmini.presentation.homepage.components.fragment.banner.FragmentBannerBeranda
import com.academy.alfagiftmini.presentation.homepage.components.fragment.officialstore.FragmentOfficialStore
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productcategories.FragmentProductCategories
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist.FragmentPenawaranTerbaik
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.MainActivityViewModel
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.BannerListViewModel
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.OfficialStoreViewModel
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductCategoriesViewModel
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductListViewModel


class FragmentBeranda() : Fragment() {
    private lateinit var binding: FragmentBerandaBinding


    private lateinit var viewModel: ProductCategoriesViewModel
    private lateinit var officialStoreViewModel: OfficialStoreViewModel
    private lateinit var mainViewModel: MainActivityViewModel
    private lateinit var bannerViewModel: BannerListViewModel
    private lateinit var productListViewModel: ProductListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModel()
        setFragment(binding.flBannerSlider.id, FragmentBannerBeranda(bannerViewModel))
        setFragment(binding.flProductCategories.id, FragmentProductCategories(viewModel))
        setFragment(binding.flOfficialStore.id, FragmentOfficialStore(officialStoreViewModel))
        setFragment(binding.flProductListPenawaranTerbaik.id, FragmentPenawaranTerbaik(productListViewModel))
        setToolbar()
        setBtnSearch()
    }

    private fun setViewModel() {
        viewModel = (requireActivity() as MainActivity).getViewModelProductCategories()
        officialStoreViewModel = (requireActivity() as MainActivity).getViewModelOfficialStore()
        mainViewModel = (requireActivity() as MainActivity).getViewModelMain()
        bannerViewModel = (requireActivity() as MainActivity).getBannerListsViewModel()
        productListViewModel = (requireActivity() as MainActivity).getViewModelProductList()
    }

    private fun setBtnSearch() {
        binding.btnSearchProduct.setOnClickListener {
            startActivity(Intent(requireContext(), ProductListSearchProdukActivity::class.java))
        }
    }

    private fun setToolbar() {
        binding.scrollView.setOnScrollChangeListener { _, _, scrollY, _, _ ->
            if (scrollY > 215) {
                binding.berandaToolbar.btnSearch.visibility = View.VISIBLE
                binding.berandaToolbar.tvToolbar.visibility = View.GONE
            } else {
                binding.berandaToolbar.btnSearch.visibility = View.GONE
                binding.berandaToolbar.tvToolbar.visibility = View.VISIBLE
            }
        }
        binding.berandaToolbar.btnSearch.setOnClickListener {
            startActivity(Intent(requireContext(), ProductListSearchProdukActivity::class.java))
        }
        mainViewModel._getUserData.observe(requireActivity()) {
            binding.berandaToolbar.tvToolbar.text =
                getString(R.string.toolbar_greeting, it.getFullName())
        }
        mainViewModel.getData((requireActivity() as MainActivity))
    }


    private fun setFragment(layoutId: Int, fragment: Fragment) {
        val fragmentManager = parentFragmentManager
        fragmentManager.beginTransaction().replace(layoutId, fragment).commit()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentBerandaBinding.inflate(inflater)
        return binding.root
    }


}