package com.academy.alfagiftmini.presentation.homepage.components.util

import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.idling.CountingIdlingResource
import androidx.test.espresso.matcher.ViewMatchers


object EspressoIdlingResource {
  private val RESOURCE = "GLOBAL"
  private val countingIdlingResource = CountingIdlingResource(RESOURCE)

  val idlingresource: IdlingResource
    get() = countingIdlingResource

  fun increment() {
    countingIdlingResource.increment()
  }

  fun decrement() {
    countingIdlingResource.decrement()
  }

  fun onViewId(id:Int): ViewInteraction {
    return Espresso.onView(ViewMatchers.withId(id))
  }

  fun ViewInteraction.checkText(text:String): ViewInteraction? {
    return this.check(ViewAssertions.matches(ViewMatchers.withText(text)))
  }

  fun ViewInteraction.checkIsDisplayed(): ViewInteraction?{
    return this.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
  }
}