package com.academy.alfagiftmini.presentation.homepage.components.activity.productcategories

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.ViewModelInitializer
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.academy.alfagiftmini.domain.productcategories.model.ProductCategoriesDomainModel
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductCategoriesViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ProductCategoriesActivity2Test {
    @get:Rule
    lateinit var activityRule: ActivityScenarioRule<ProductCategoriesActivity>

    private lateinit var viewModel: ProductCategoriesViewModel

    @Before
    fun setup() {
        activityRule = ActivityScenarioRule(
            Intent().putExtra(PresentationUtils.CATEGORIES_KEY, ProductCategoriesDomainModel(id=1, text="Kebutuhan Dapur", image="https://cdn.alfagift.id/media/bo/product/ama/category/pm_category_190116_Qqxu.png", subcategories=listOf("Perlengkapan Dapur & Ruang Makan", "Bahan Masakan", "Bahan Roti & Kue", "Bahan Puding & Agar-Agar")))
        )
        activityRule.scenario.onActivity {
            viewModel = ViewModelProvider(it, it.viewModelFactory)[ProductCategoriesViewModel::class.java]
        }
    }

    @Test
    fun testone() {

    }
}