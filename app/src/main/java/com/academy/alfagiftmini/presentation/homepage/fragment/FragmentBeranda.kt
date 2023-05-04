package com.academy.alfagiftmini.presentation.homepage.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.academy.alfagiftmini.MyApplication
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.FragmentBelanjaBinding
import com.academy.alfagiftmini.databinding.FragmentBerandaBinding
import com.academy.alfagiftmini.databinding.FragmentProductCategoriesBinding
import com.academy.alfagiftmini.presentation.factory.PresentationFactory
import com.academy.alfagiftmini.presentation.homepage.activity.MainActivity
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productcategories.FragmentProductCategories
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductCategoriesViewModel
import javax.inject.Inject


class FragmentBeranda(private val viewModelFactory: PresentationFactory ): Fragment() {
    private lateinit var binding: FragmentBerandaBinding


    private val viewModel: ProductCategoriesViewModel by viewModels{
        viewModelFactory
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFragment(binding.flProductCategories.id, FragmentProductCategories(viewModel))
    }

    private fun setFragment(layoutId: Int, fragment: Fragment) {
        val fragmentManager = parentFragmentManager
        fragmentManager.beginTransaction().replace(layoutId, fragment ).commit()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBerandaBinding.inflate(inflater)
        return binding.root
    }


}