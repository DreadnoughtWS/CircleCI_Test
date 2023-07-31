package com.academy.alfagiftmini.presentation.homepage.components.fragment.productlist

import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.presentation.homepage.activity.MainActivity
import com.academy.alfagiftmini.presentation.homepage.components.adapter.productlist.ProductListGratisProductPagingAdapter
import com.academy.alfagiftmini.presentation.homepage.components.util.EspressoIdlingResource.checkIsDisplayed
import com.academy.alfagiftmini.presentation.homepage.components.util.EspressoIdlingResource.checkText
import com.academy.alfagiftmini.presentation.homepage.components.util.EspressoIdlingResource.onViewId
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FragmentPenawaranTerbaikTest{
  @get:Rule
  val scenario = activityScenarioRule<MainActivity>()

  @Test fun testCheckVisibility(){
    Thread.sleep(2000)
    onViewId(R.id.cl_container_penawaran_terbaik).checkIsDisplayed()
    onViewId(R.id.tv_penawaran_terbaik).checkIsDisplayed()
    onViewId(R.id.tv_lihat_semua_penawaran_terbaik).checkIsDisplayed()
    onViewId(R.id.rv_penawaran_terbaik).checkIsDisplayed()
  }

  @Test fun testCheckText(){
    Thread.sleep(2000)
    onViewId(R.id.tv_penawaran_terbaik).checkText("Penawaran Terbaik")
    onViewId(R.id.tv_lihat_semua_penawaran_terbaik).checkText("Lihat Semua")
  }

  @Test fun testCheckRecycleView(){
    Thread.sleep(2000)
    onViewId(R.id.rv_penawaran_terbaik).perform(
      RecyclerViewActions.actionOnItemAtPosition<ProductListGratisProductPagingAdapter.ProductListViewHolder>(
        0, click()
      )
    )
    Thread.sleep(2000)
    onViewId(R.id.cl_container_detail_product_list).checkIsDisplayed()
  }

  @Test fun testLihatSemuaOnClick(){
    Thread.sleep(2000)
    onViewId(R.id.tv_lihat_semua_penawaran_terbaik).perform(click())
    Thread.sleep(2000)
    onViewId(R.id.cl_container_all_penawaran_terbaik).checkIsDisplayed()
  }


}