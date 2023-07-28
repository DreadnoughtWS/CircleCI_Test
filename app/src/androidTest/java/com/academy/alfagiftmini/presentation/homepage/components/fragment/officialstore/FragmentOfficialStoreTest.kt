package com.academy.alfagiftmini.presentation.homepage.components.fragment.officialstore

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle.State.INITIALIZED
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isDisplayingAtLeast
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.presentation.homepage.activity.MainActivity
import com.academy.alfagiftmini.presentation.homepage.components.adapter.officialstore.AllOfficialStorePagingAdapter
import com.academy.alfagiftmini.presentation.homepage.components.adapter.officialstore.OfficialStore14Adapter
import com.academy.alfagiftmini.presentation.homepage.components.util.EspressoIdlingResource.checkIsDisplayed
import com.academy.alfagiftmini.presentation.homepage.components.util.EspressoIdlingResource.onViewId
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FragmentOfficialStoreTest{
  @get:Rule
  val scenario = activityScenarioRule<MainActivity>()


  @Test fun testCheckText(){
    Espresso.onView(withId(R.id.tv_official_store)).check(matches(withText("Official Store")))
    Espresso.onView(withId(R.id.tv_lihat_semua_official_beranda)).check(matches(withText("Lihat Semua")))
  }

  @Test fun testTvLihatSemuaOnClick(){
    Thread.sleep(4000)
    Espresso.onView(withId(R.id.tv_lihat_semua_official_beranda)).perform(scrollTo()).perform(click())
    Thread.sleep(2000)
    Espresso.onView(withId(R.id.cl_container_all_official_store)).check(matches(isDisplayed()))
  }

  @Test fun testCheckVisibility(){
    Thread.sleep(3000)
    onViewId(R.id.cl_container_official_store).perform(scrollTo()).checkIsDisplayed()
    onViewId(R.id.cl_container_official_store).checkIsDisplayed()
    onViewId(R.id.tv_official_store).checkIsDisplayed()
    onViewId(R.id.tv_lihat_semua_official_beranda).checkIsDisplayed()
    onViewId(R.id.rv_offical_store_beranda).checkIsDisplayed()
  }

  @Test fun testCheckRecycleView(){
    Thread.sleep(3000)
    onViewId(R.id.rv_offical_store_beranda).perform(scrollTo()).perform(
      RecyclerViewActions.actionOnItemAtPosition<OfficialStore14Adapter.OfficialStoreViewHolder>(
        0, click()
      )
    )

    Thread.sleep(2000)
    onViewId(R.id.cl_container_detail_official_store).checkIsDisplayed()
  }

}