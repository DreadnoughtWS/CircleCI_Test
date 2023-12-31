package com.academy.alfagiftmini.presentation.homepage.fragment

import android.content.Intent
import android.icu.text.Normalizer.NO
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.academy.alfagiftmini.MyApplication
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.FragmentBerandaBinding
import com.academy.alfagiftmini.presentation.PresentationUtils
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
        checkInternet()

    }

    private fun checkInternet() {
        if (!PresentationUtils.isNetworkAvailable(requireContext())) {
            val dialogg = PresentationUtils.noInternetDialog(requireContext())
            dialogg.setPositiveButton(requireContext().getString(R.string.retry)) { _, _ ->
                checkInternet()
            }
            dialogg.setNegativeButton(requireContext().getString(R.string.close)) { dialog, _ ->
                dialog.cancel()
                requireActivity().finish()
            }
            PresentationUtils.shownoInternetDialog(dialogg)
        } else {
            setViewModel()
            setFragment(binding.flBannerSlider.id, FragmentBannerBeranda())
            setFragment(binding.flProductCategories.id, FragmentProductCategories())
            setFragment(binding.flOfficialStore.id, FragmentOfficialStore())
            setFragment(binding.flProductListPenawaranTerbaik.id, FragmentPenawaranTerbaik())
            setToolbar()
            setBtnSearch()
        }
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
            binding.scrollView.overScrollMode = View.OVER_SCROLL_NEVER
        }
        binding.berandaToolbar.btnSearch.setOnClickListener {
            startActivity(Intent(requireContext(), ProductListSearchProdukActivity::class.java))
        }
        mainViewModel._getUserData.observe(requireActivity()) {
            binding.berandaToolbar.tvToolbar.text =
                activity?.getString(R.string.toolbar_greeting, it.getFullName())
        }
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


    fun provideProductCategoryViewModel(): ProductCategoriesViewModel {
        return viewModel
    }

    fun provideOfficialStoreViewModel(): OfficialStoreViewModel {
        return officialStoreViewModel
    }

    fun provideBannerViewModel(): BannerListViewModel {
        return bannerViewModel
    }

    fun provideProductListViewModel(): ProductListViewModel {
        return productListViewModel
    }
}