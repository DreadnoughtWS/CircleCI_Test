package com.academy.alfagiftmini.presentation.homepage.components.activity.officialstore

import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.academy.alfagiftmini.presentation.homepage.components.util.EspressoIdlingResource
import com.academy.alfagiftmini.presentation.homepage.components.util.EspressoIdlingResource.checkIsDisplayed
import com.academy.alfagiftmini.presentation.homepage.components.util.EspressoIdlingResource.onViewId
import org.junit.After
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.R.id
import com.academy.alfagiftmini.presentation.homepage.components.adapter.officialstore.AllOfficialStorePagingAdapter
import com.academy.alfagiftmini.presentation.homepage.components.util.EspressoIdlingResource.checkText
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OfficialStoreSearchActivityTest {
  @get:Rule
  val scenario = activityScenarioRule<OfficialStoreSearchActivity>()

  @Before
  fun setUp() {

    IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingresource)
  }

  @After
  fun tearDown() {
    IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingresource)
  }

  @Test
  fun testCheckVisibility() {
    onViewId(R.id.official_store_seach_toolbar).checkIsDisplayed()
    onViewId(R.id.til_search_view).checkIsDisplayed()
    onViewId(R.id.tiet_search_view).checkIsDisplayed()
  }

  @Test
  fun testSearchSuccess() {
    onViewId(R.id.tiet_search_view).perform(typeText("Alfa"))
    onViewId(R.id.tiet_search_view).perform(ViewActions.pressImeActionButton())
    Thread.sleep(2000)
    onViewId(R.id.rv_official_store_search).checkIsDisplayed()
    Thread.sleep(500)
    onViewId(R.id.rv_official_store_search).perform(
      RecyclerViewActions.actionOnItemAtPosition<AllOfficialStorePagingAdapter.DetailOfficialStoreViewHolder>(
        0, click()
      )
    )
    Thread.sleep(2000)
    onViewId(R.id.cl_container_detail_official_store).checkIsDisplayed()
  }

  @Test
  fun testSearchEmpty() {
    onViewId(R.id.tiet_search_view).perform(typeText("test test"))
    onViewId(R.id.tiet_search_view).perform(ViewActions.pressImeActionButton())
    Thread.sleep(2000)
    onViewId(R.id.cl_produk_ga_ada).checkIsDisplayed()
  }

  @Test
  fun testCheckTest() {
    onViewId(R.id.tv_promo_toolbar_title).checkText("Cari Official Store")
  }
}