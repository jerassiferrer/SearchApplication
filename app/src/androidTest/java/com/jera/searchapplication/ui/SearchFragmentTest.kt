package com.jera.searchapplication.ui

import android.view.KeyEvent
import android.widget.SearchView
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jera.searchapplication.R
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class SearchFragmentTest{

    @Before
    fun setUp(){
        launchFragmentInContainer<SearchFragment>()
    }

    @Test
    fun isEditTextDisplayed() {
        onView(ViewMatchers.withId(R.id.enter_artist_et)).check(matches(isDisplayed()))
    }

    @Test
    fun isButtonDisplayed() {
        onView(ViewMatchers.withId(R.id.enter_artist_btn)).check(matches(isDisplayed()))
    }

    @Test
    fun isSpinnerNotDisplayed() {
        onView(ViewMatchers.withId(R.id.search_progress_bar)).check(matches(not(isDisplayed())))
    }

    @Test
    fun showSpinnerWittInputSearchTextAndClickButtonSearch(){
        onView(isAssignableFrom(SearchView::class.java)).perform(click())
        onView(isAssignableFrom(SearchView::class.java)).perform(typeText("test"), pressKey(KeyEvent.KEYCODE_ENTER))
        onView(ViewMatchers.withId(R.id.enter_artist_btn)).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.search_progress_bar)).check(matches(isDisplayed()))
    }

    @Test
    fun clickedSearchWithEmptyTextShowNotShowSpinner(){
        onView(ViewMatchers.withId(R.id.enter_artist_btn)).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.search_progress_bar)).check(matches(not(isDisplayed())))
    }


}