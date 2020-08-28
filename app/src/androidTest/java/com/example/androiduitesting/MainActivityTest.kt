package com.example.androiduitesting


import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {


    @get: Rule
    val activityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_testSpinner() {
        val spinnerArray = arrayOf("Home", "Work", "Mobile", "Other")
        val size = spinnerArray.size

        for (i in 0 until size) {
            onView(withId(R.id.label_spinner)).perform(click())
            onData(`is`(spinnerArray[i])).perform(click())
            onView(withId(R.id.text_phonelabel))
                .check(matches(withText(containsString(spinnerArray[i]))))
        }
    }
}