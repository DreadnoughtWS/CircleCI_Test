package com.academy.alfagiftmini.presentation.homepage.components.fragment.productcategories

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.testing.EmptyFragmentActivity
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.rules.activityScenarioRule
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.ActivityProductCategoriesBinding
import com.academy.alfagiftmini.presentation.homepage.components.activity.productcategories.ProductCategoriesActivity
import com.academy.alfagiftmini.presentation.homepage.components.activity.productcategories.ProductCategoriesActivityTest
import com.academy.alfagiftmini.presentation.homepage.components.util.EspressoIdlingResource.checkIsDisplayed
import com.google.android.material.tabs.TabLayout
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FragmentProductCategoriesDetailTest{

    @Test
    fun checkTabs() {

        onView(withId(R.id.tl_sub_category_product)).check { view,_ ->
            print(view.id.toString())
        }
    }
}