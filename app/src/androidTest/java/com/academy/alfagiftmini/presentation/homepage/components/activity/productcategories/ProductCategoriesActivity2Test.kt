package com.academy.alfagiftmini.presentation.homepage.components.activity.productcategories

import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.ViewModelInitializer
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.domain.productcategories.model.ProductCategoriesDomainModel
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.homepage.components.adapter.productcategories.CategoriesAdapter
import com.academy.alfagiftmini.presentation.homepage.components.util.EspressoIdlingResource.checkIsDisplayed
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductCategoriesViewModel
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductListViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.regex.Matcher

class ProductCategoriesActivity2Test {
    @get:Rule
    var activityRule: ActivityScenarioRule<ProductCategoriesActivity> = ActivityScenarioRule(
        Intent(ApplicationProvider.getApplicationContext(), ProductCategoriesActivity::class.java)
            .putExtra(PresentationUtils.CATEGORIES_KEY, ProductCategoriesDomainModel(id=1, text="Kebutuhan Dapur", image="https://cdn.alfagift.id/media/bo/product/ama/category/pm_category_190116_Qqxu.png", subcategories=listOf("Perlengkapan Dapur & Ruang Makan", "Bahan Masakan", "Bahan Roti & Kue", "Bahan Puding & Agar-Agar")))
    )

    private lateinit var viewModelList: ProductListViewModel
    private lateinit var viewModelProd: ProductCategoriesViewModel

    @Before
    fun setup() {
        activityRule.scenario.onActivity {
            viewModelList = it.getProductListCategoryViewModel()
            viewModelProd = ViewModelProvider(it, it.viewModelFactory)[ProductCategoriesViewModel::class.java]
        }
    }

    @Test
    fun testone() {
        activityRule.scenario.onActivity {
            viewModelProd.liveData.observe(it) {
                onView(withId(R.id.container)).check(
                    ViewAssertions.matches(
                        ViewMatchers.withEffectiveVisibility(
                            ViewMatchers.Visibility.VISIBLE
                        )
                    )
                )
            }
        }
    }

    @Test
    fun testTwo() {
        activityRule.scenario.onActivity {
            viewModelList.itemCount.observe(it) {
                Log.d("TEST", "${it}")
            }
        }
    }
}