package com.academy.alfagiftmini.presentation.homepage.components.activity.productlist

import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.academy.alfagiftmini.presentation.homepage.components.activity.officialstore.OfficialStoreSearchActivity
import com.academy.alfagiftmini.presentation.homepage.components.util.EspressoIdlingResource.onViewId
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.presentation.homepage.components.util.EspressoIdlingResource.checkIsDisplayed
import com.academy.alfagiftmini.presentation.homepage.components.util.EspressoIdlingResource.checkText

@RunWith(AndroidJUnit4::class)
class ProductListPenawaranTerbaikActivityTest{
  @get:Rule
  val scenario = activityScenarioRule<ProductListPenawaranTerbaikActivity>()

  @Test
  fun testCheckVisibility(){
    Thread.sleep(2000)
    onViewId(R.id.cl_container_all_penawaran_terbaik).checkIsDisplayed()
    onViewId(R.id.al_productlist_penawaran_terbaik).checkIsDisplayed()
    onViewId(R.id.iv_banner_image).checkIsDisplayed()
    onViewId(R.id.ll_sticky_banner_product_list).checkIsDisplayed()
    onViewId(R.id.divider_banner_product_list).checkIsDisplayed()
    onViewId(R.id.tl_penawaran).checkIsDisplayed()
    onViewId(R.id.container_fragment_penawaran).checkIsDisplayed()
  }

  @Test fun testCheckText(){
    Thread.sleep(2000)
    onViewId(R.id.tv_toolbar).checkText("Penawaran Terbaik")
  }

  @Test fun testRVPromosi(){
    Thread.sleep(2000)
    onViewId(R.id.rv_product_list_promosi).checkIsDisplayed()
  }

  @Test fun testRVNamaProduct(){
    Thread.sleep(2000)
    scenario.scenario.onActivity {
      it.positionTab=1
      it.setupFragment(it.positionTab)
    }
    Thread.sleep(4000)
    onViewId(R.id.rv_product_list_nama_produk).checkIsDisplayed()
  }

  @Test fun testRVTerlaris(){
    Thread.sleep(2000)
    scenario.scenario.onActivity {
      it.positionTab=2
      it.setupFragment(it.positionTab)
    }
    Thread.sleep(4000)
    onViewId(R.id.rv_product_list_terlaris).checkIsDisplayed()
  }

}