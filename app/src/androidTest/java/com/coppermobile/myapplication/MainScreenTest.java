package com.coppermobile.myapplication;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.coppermobile.myapplication.activity.MainActivity;
import com.coppermobile.myapplication.utils.EspressoIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;

@RunWith(AndroidJUnit4.class)
public class MainScreenTest {

    String searchedText = "android";

    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        // Register your Idling Resource before any tests regarding this component
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource());
    }

    //The test will only pass if the ediText contains any text.
    @Test
    public void checkEditText_isDisplayed_and_notEmpty() {

        onView(withId(R.id.et_searchRepo)).check(matches(isDisplayed())).perform(click());

//        onView(withId(R.id.et_searchRepo)).perform(typeText(searchedText), closeSoftKeyboard());

        onView(withId(R.id.et_searchRepo)).perform(pressImeActionButton());

        onView(withId(R.id.et_searchRepo)).check(matches(not(withText(""))));
    }

    @Test
    public void setUpData_whenDataFetchedSuccessfully() {

        onView(withId(R.id.et_searchRepo)).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.et_searchRepo)).perform(typeText(searchedText), closeSoftKeyboard());

        onView(withId(R.id.et_searchRepo)).perform(pressImeActionButton());

        onView(withId(R.id.tv_availableRepo)).check(matches(withText("Number of repos found: 3")));
    }

    // Unregister your Idling Resource so it can be garbage collected and does not leak any memory
    @After
    public void afterAllMethods() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getIdlingResource());
    }
}