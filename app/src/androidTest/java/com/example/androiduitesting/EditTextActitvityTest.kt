package com.example.androiduitesting

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class EditTextActitvityTest {


    @get: Rule
    val activityTestRule = ActivityScenarioRule(EditTextActitvity::class.java)

    @Test
    fun text_activityLaunch() {
        onView(withId(R.id.button_main)).perform(click())
        onView(withId(R.id.text_header)).check(matches(isDisplayed()))
        onView(withId(R.id.button_second)).perform(click())
        onView(withId(R.id.text_header_reply)).check(matches(isDisplayed()))
    }


    @Test
    fun test_inputOutputText() {
        onView(withId(R.id.editText_main))
            .perform(typeText("This is testing."))
        onView(withId(R.id.button_main))
            .perform(click())

        onView(withId(R.id.text_message)).check(
            matches(withText("This is testing."))
        )
    }
}