package com.academy.alfagiftmini.presentation.homepage.components.activity.officialstore

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.presentation.homepage.activity.MainActivity
import com.academy.alfagiftmini.presentation.homepage.components.adapter.officialstore.AllOfficialStorePagingAdapter
import com.academy.alfagiftmini.presentation.homepage.components.util.EspressoIdlingResource
import com.academy.alfagiftmini.presentation.homepage.components.util.MyViewAction
import org.junit.After
import org.junit.Before


@RunWith(AndroidJUnit4::class)
class AllOfficialStoreActivityTest {
  @get:Rule
  val scenario = activityScenarioRule<AllOfficialStoreActivity>()

  @Before
  fun setUp() {
    IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingresource)
  }

  @After
  fun tearDown() {
    IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingresource)
  }

  @Test
  fun testCheckDisplay() {
    Thread.sleep(5000)
    Espresso.onView(withId(R.id.cl_container_all_official_store)).check(matches(isDisplayed()))
    Espresso.onView(withId(R.id.tv_official_store_popular)).check(matches(isDisplayed()))
  }

  @Test
  fun testCheckText() {
    Thread.sleep(5000)
    Espresso.onView(withId(R.id.tv_official_store_popular))
      .check(matches(withText("Official Store Popular")))
  }

  @Test
  fun testRecycleView() {
    Thread.sleep(5000)
    Espresso.onView(withId(R.id.rv_all_official_store)).check(matches(isDisplayed()))
  }

  @Test
  fun testClickItemRecycleView() {
    Thread.sleep(5000)
    Espresso.onView(withId(R.id.rv_all_official_store))
      .perform(RecyclerViewActions.actionOnItemAtPosition<AllOfficialStorePagingAdapter.DetailOfficialStoreViewHolder>(0, click()));
    Thread.sleep(5000)
    Espresso.onView(withId(R.id.cl_container_detail_official_store)).check(matches(isDisplayed()))
  }

}