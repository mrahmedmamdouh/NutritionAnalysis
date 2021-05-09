package com.example.nutritionanalysis.navigationTest

import android.os.Bundle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.nutritionanalysis.R
import com.example.nutritionanalysis.data.model.NutritionResponse
import com.example.nutritionanalysis.launchFragmentInHiltContainer
import com.example.nutritionanalysis.ui.MainActivity
import com.example.nutritionanalysis.ui.listFragment.ListFragment
import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class NavigationTest {

    @Test
    fun test_analyzeFargment_clickButton_ListFragment_inView_back() {
        ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.ingredient_text))
            .perform(ViewActions.typeText("1 cup rice \n10 oz chickpeas\n1 bottle wine\n1 cup tea"))
        closeSoftKeyboard()
        onView(withId(R.id.analyze_bttn))
            .check(matches(isEnabled()))
        onView(withId(R.id.analyze_bttn)).perform(click())
        onView(withId(R.id.progress_bar))
            .check(matches(isDisplayed()))
        Thread.sleep(4000) // waiting for fetching data
        onView(withId(R.id.show_total_bttn))
            .check(matches(isDisplayed()))
        onView(withId(R.id.list))
            .check(matches(isDisplayed()))
        pressBack()
        onView(withId(R.id.ingredient_text))
            .check(matches(isDisplayed()))
        onView(withId(R.id.analyze_bttn))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_listFragment_clickButton_totalAnalysisFragment_inView_pressBack() {
        ActivityScenario.launch(MainActivity::class.java)


        onView(withId(R.id.ingredient_text))
            .perform(ViewActions.typeText("1 cup rice \n10 oz chickpeas\n1 bottle wine\n1 cup tea"))
        closeSoftKeyboard()
        onView(withId(R.id.analyze_bttn)).perform(click())

        Thread.sleep(4000) // waiting for fetching data

        onView(withId(R.id.show_total_bttn)).check(matches(isDisplayed()))
        onView(withId(R.id.list)).check(matches(isDisplayed()))
        onView(withId(R.id.show_total_bttn)).perform(click())
        onView(withId(R.id.textView)).check(matches(isDisplayed()))

        pressBack()

        onView(withId(R.id.show_total_bttn))
            .check(matches(isDisplayed()))
        onView(withId(R.id.list))
            .check(matches(isDisplayed()))

    }
}