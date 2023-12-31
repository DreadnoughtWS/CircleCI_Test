package com.academy.alfagiftmini.presentation.homepage.components.fragment.officialstore.detail

import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.academy.alfagiftmini.R.id
import com.academy.alfagiftmini.presentation.homepage.components.activity.officialstore.DetailOfficialStoreActivity
import com.academy.alfagiftmini.presentation.homepage.components.util.EspressoIdlingResource
import com.academy.alfagiftmini.presentation.homepage.components.util.EspressoIdlingResource.checkIsDisplayed
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FragmentDetailofficialStoreNamaProductTest{
  @get:Rule
  val scenario = activityScenarioRule<DetailOfficialStoreActivity>()

  @Before
  fun setup(){
    IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingresource)
  }

  @After
  fun tearDown() {
    IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingresource)
  }

  @Test
  fun testVisibility(){
    Thread.sleep(5000)
    scenario.scenario.onActivity {
      it.positionTab = 1
      it.setupFragment(it.positionTab?:1)
    }
    Thread.sleep(5000)
    EspressoIdlingResource.onViewId(id.cl_container_detail_official_store_nama_product).checkIsDisplayed()
    EspressoIdlingResource.onViewId(id.rv_product_list_nama_produk).checkIsDisplayed()
  }
}