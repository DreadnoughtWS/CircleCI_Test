package com.academy.alfagiftmini.presentation.homepage.components.activity.productcategories

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.academy.alfagiftmini.MyApplication
import com.academy.alfagiftmini.databinding.ActivityProductCategoriesBinding
import com.academy.alfagiftmini.presentation.factory.PresentationFactory
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductCategoriesViewModel
import javax.inject.Inject

class ProductCategoriesActivity: AppCompatActivity() {
    private lateinit var binding: ActivityProductCategoriesBinding

    @Inject
    lateinit var viewModelFactory: PresentationFactory
    private val viewModel: ProductCategoriesViewModel by viewModels{
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        (application as MyApplication).appComponent.productCategoriesActivityInject(this)
        super.onCreate(savedInstanceState, persistentState)
        binding = ActivityProductCategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}