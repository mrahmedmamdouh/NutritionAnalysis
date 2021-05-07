package com.example.nutritionanalysis.ui

import android.content.Context.WIFI_SERVICE
import android.net.wifi.WifiManager
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.nutritionanalysis.App
import com.example.nutritionanalysis.R
import org.hamcrest.Matchers.contains
import org.hamcrest.Matchers.not
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep


@RunWith(AndroidJUnit4::class)
class AnalyzeFragmentTest {

    @Test
    fun test_analyzeFragment_inView() {
        launch(MainActivity::class.java)
        onView(withId(R.id.ingredient_text)).check(matches(isDisplayed()))
        onView(withId(R.id.progress_bar)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.analyze_bttn)).check(matches(isDisplayed()))
        onView(withId(R.id.analyze_bttn)).check(matches(not(isEnabled())))
    }

    @Test
    fun test_typing_enablingButton() {
        launch(MainActivity::class.java)
        onView(withId(R.id.ingredient_text)).check(matches(isDisplayed()))
        onView(withId(R.id.ingredient_text)).perform(typeText("1 cup rice \n10 oz chickpeas"))
        onView(withId(R.id.analyze_bttn)).check(matches(isDisplayed()))
        onView(withId(R.id.analyze_bttn)).check(matches(isEnabled()))
    }

    @Test
    fun test_typing_clickAnalyze_progress() {
        launch(MainActivity::class.java)
        onView(withId(R.id.ingredient_text)).check(matches(isDisplayed()))
        onView(withId(R.id.ingredient_text)).perform(typeText("1 cup rice \n10 oz chickpeas"))
        closeSoftKeyboard()
        onView(withId(R.id.analyze_bttn)).check(matches(isDisplayed()))
        onView(withId(R.id.analyze_bttn)).check(matches(isEnabled()))
        onView(withId(R.id.analyze_bttn)).perform(click())
        onView(withId(R.id.progress_bar)).check(matches(isDisplayed()))
        sleep(4000) // waiting for fetching data
        onView(withId(R.id.progress_bar)).check(matches(withEffectiveVisibility(Visibility.INVISIBLE)))
    }
}