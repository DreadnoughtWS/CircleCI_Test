package com.academy.alfagiftmini.presentation.homepage.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.FragmentBerandaBinding
import com.academy.alfagiftmini.domain.register.RegisterDataDomain
import com.academy.alfagiftmini.presentation.authentication.viewmodel.LoginViewModel
import com.academy.alfagiftmini.presentation.factory.PresentationFactory
import com.academy.alfagiftmini.presentation.homepage.activity.MainActivity
import com.academy.alfagiftmini.presentation.homepage.components.activity.productlist.ProductListSearchProdukActivity
import com.academy.alfagiftmini.presentation.homepage.components.fragment.officialstore.FragmentOfficialStore
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productcategories.FragmentProductCategories
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.OfficialStoreViewModel
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductCategoriesViewModel
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductListViewModel


class FragmentBeranda() : Fragment() {
    private lateinit var binding: FragmentBerandaBinding

    private lateinit var mainViewModel: LoginViewModel
    private lateinit var viewModel: ProductCategoriesViewModel
    private lateinit var officialStoreViewModel: OfficialStoreViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = (requireActivity() as MainActivity).getMainViewModel()
        setViewModel()
        setObserver()
        setFragment(binding.flProductCategories.id, FragmentProductCategories(viewModel))
        setFragment(binding.flOfficialStore.id, FragmentOfficialStore(officialStoreViewModel))
        setToolbar(null)
        setBtnSearch()
    }

    private fun setObserver() {
        mainViewModel.getUserLiveData().observe(requireActivity()) {
            setToolbarName(it.getFullName())
        }
    }

    private fun setViewModel() {
        viewModel = (requireActivity() as MainActivity).getViewModelProductCategories()
        officialStoreViewModel = (requireActivity() as MainActivity).getViewModelOfficialStore()
    }

    private fun setBtnSearch() {
        binding.btnSearchProduct.setOnClickListener {
            startActivity(Intent(requireContext(), ProductListSearchProdukActivity::class.java))
        }
    }

    private fun setToolbar(fullName: String?) {
        binding.scrollView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
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
        //TODO Query userdata dari activity
        setToolbarName(null)
    }

    private fun setToolbarName(fullname: String?) {
        binding.berandaToolbar.tvToolbar.text = getString(R.string.toolbar_greeting, fullname)
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