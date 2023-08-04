package com.academy.alfagiftmini.presentation.homepage.components.activity.productcategories

import android.view.LayoutInflater
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.ActivityAction
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.rules.activityScenarioRule
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.ActivityMainBinding
import com.academy.alfagiftmini.databinding.ActivityProductCategoriesBinding
import com.academy.alfagiftmini.presentation.homepage.activity.MainActivity
import com.academy.alfagiftmini.presentation.homepage.components.adapter.productcategories.CategoriesAdapter
import com.academy.alfagiftmini.presentation.homepage.components.adapter.productlist.ProductListGratisProductPagingAdapter
import com.academy.alfagiftmini.presentation.homepage.components.fragment.productcategories.FragmentProductCategoriesDetail
import com.academy.alfagiftmini.presentation.homepage.components.util.EspressoIdlingResource
import com.academy.alfagiftmini.presentation.homepage.components.util.EspressoIdlingResource.checkIsDisplayed
import com.google.android.material.tabs.TabLayout
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ProductCategoriesActivityTest{

    @get:Rule
    val scenario = activityScenarioRule<MainActivity>()

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingresource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingresource)
    }

    @Test
    fun testActivity(){
        Thread.sleep(5000)
        onView(withId(R.id.fl_home_fragment_holder)).checkIsDisplayed()
        onView(withId(R.id.fl_product_categories)).perform(
            scrollTo()).checkIsDisplayed()
        onView(withId(R.id.rv_list_categories)).checkIsDisplayed()
        chooseCategory()
        checkFragmentDisplay()
        checkCategoriesTabs()
        checkProductListTabs()
    }

    fun chooseCategory() {
        Thread.sleep(5000)
        onView(withId(R.id.rv_list_categories)).perform(
            RecyclerViewActions.actionOnItemAtPosition<CategoriesAdapter.ProductCategoriesViewHolder>(
                0, ViewActions.click()
            )
        )
    }

    fun checkFragmentDisplay() {
        Thread.sleep(5000)
        onView(withId(R.id.container)).checkIsDisplayed()
    }

    fun checkCategoriesTabs() {
        Thread.sleep(5000)
        onView(withId(R.id.tl_sub_category_product)).checkIsDisplayed()
    }

    fun checkProductListTabs() {
        Thread.sleep(5000)
        onView(withId(R.id.tl_sub_category_product)).checkIsDisplayed()
    }
}