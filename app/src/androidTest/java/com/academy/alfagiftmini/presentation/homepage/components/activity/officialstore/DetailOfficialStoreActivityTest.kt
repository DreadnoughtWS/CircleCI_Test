package com.academy.alfagiftmini.presentation.homepage.components.activity.officialstore

import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.domain.officialstore.model.OfficialStoreDomainItemModel
import com.academy.alfagiftmini.domain.officialstore.model.OfficialStorebrandsDomainItemModel
import com.academy.alfagiftmini.presentation.homepage.components.util.EspressoIdlingResource
import com.academy.alfagiftmini.presentation.homepage.components.util.EspressoIdlingResource.checkIsDisplayed
import com.academy.alfagiftmini.presentation.homepage.components.util.EspressoIdlingResource.checkText
import com.academy.alfagiftmini.presentation.homepage.components.util.EspressoIdlingResource.onViewId
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailOfficialStoreActivityTest {
  @get:Rule
  val scenario = activityScenarioRule<DetailOfficialStoreActivity>()

  @Before
  fun setUp() {

    IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingresource)
  }

  @After
  fun tearDown() {
    IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingresource)
  }

  @Test
  fun testCheckValidity() {
    Thread.sleep(5000)
    onViewId(R.id.cl_container_detail_official_store).checkIsDisplayed()
    onViewId(R.id.detail_official_store_toolbar).checkIsDisplayed()
    onViewId(R.id.view_bg_red_detail_official_store).checkIsDisplayed()
    onViewId(R.id.cl_detail_official_store).checkIsDisplayed()
    onViewId(R.id.iv_store_logo).checkIsDisplayed()
    onViewId(R.id.tv_title_official_store).checkIsDisplayed()
    onViewId(R.id.btn_ikuti_detail_official_store).checkIsDisplayed()
    onViewId(R.id.sv_slider_banner).checkIsDisplayed()
    onViewId(R.id.tv_brands_detail_official_store).checkIsDisplayed()
    onViewId(R.id.cl_sticky_official_store).checkIsDisplayed()
    onViewId(R.id.tl_official_store).checkIsDisplayed()
    onViewId(R.id.container).checkIsDisplayed()
  }

  @Test
  fun testCheckText() {
    Thread.sleep(2000)
    onViewId(R.id.tv_semua_produk).checkText("Semua Produk")
   onViewId(R.id.tv_brands_detail_official_store).checkText("Belanja berdasarkan brand")
  }

  @Test
  fun testCheckRecycleView() {
    scenario.scenario.onActivity {
      it.brandId = "&brandid=11588"
      it.getDataFromApi()
    }
    Thread.sleep(2000)
    Espresso.onView(withId(R.id.rv_brands_detail_official_store)).check(matches(isDisplayed()))
  }


}