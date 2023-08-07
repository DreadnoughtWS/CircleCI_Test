package com.academy.alfagiftmini.presentation.authentication.activity

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.presentation.homepage.components.util.EspressoIdlingResource
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import tools.fastlane.screengrab.DecorViewScreenshotStrategy
import tools.fastlane.screengrab.FalconScreenshotStrategy
import tools.fastlane.screengrab.Screengrab

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {
    @get:Rule
    var activityRule= ActivityScenarioRule(LoginActivity::class.java)

    @Before
    fun setUp() {
        activityRule.scenario.onActivity {
            Screengrab.setDefaultScreenshotStrategy(FalconScreenshotStrategy(it))
        }
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingresource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingresource)
    }

    @Test
    fun invalidEmailTest() {
        Screengrab.screenshot("before_button_click")
        Thread.sleep(4000)
        //onView(withId(R.id.tv_title)).check(matches())
        onView(withId(R.id.et_email)).perform(replaceText(""))
        onView(withId(R.id.et_password)).perform(replaceText(""))
        onView(withId(R.id.btn_submit_user_data)).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.tv_email_err)).check(matches(withEffectiveVisibility(Visibility.INVISIBLE)))
        Screengrab.screenshot("after_button_click")
    }

    @Test
    fun invalidPasswordTest() {
        Thread.sleep(4000)
        val emailValid = "aku@gmail.com"
        onView(withId(R.id.et_email)).perform(replaceText(emailValid))
        onView(withId(R.id.et_password)).perform(replaceText("h"))
        onView(withId(R.id.btn_submit_user_data)).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.tv_email_err)).check(matches(withEffectiveVisibility(Visibility.INVISIBLE)))
        onView(withId(R.id.tv_pass_err)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

//    @Test
//    fun validinputs() {
//        Thread.sleep(4000)
//        val emailValid = "made@gmail.com"
//        val passValid = "12345678"
//        onView(withId(R.id.et_email)).perform(replaceText(emailValid))
//        onView(withId(R.id.et_password)).perform(replaceText(passValid))
//        onView(withId(R.id.btn_submit_user_data)).perform(click())
//
//        onView(withId(R.id.tv_email_err)).check(matches(not(isDisplayed())))
//        onView(withId(R.id.tv_pass_err)).check(matches(not(isDisplayed())))
//    }
}