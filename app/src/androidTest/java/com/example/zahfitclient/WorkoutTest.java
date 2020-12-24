package com.example.zahfitclient;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.startsWith;

public class WorkoutTest {
    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void WorkoutTest() throws InterruptedException {
        onView(withText(startsWith("Login"))).perform(click());
        onView(withId(R.id.txt_Email)).perform(typeText("yazeedarifin@gmail.com"));
        onView(withId(R.id.txt_Password)).perform(typeText("101010"));
        closeSoftKeyboard();
        onView(withText(startsWith("Login"))).perform(click());
        Thread.sleep(3000);
//        onView(withContentDescription("Open navigation drawer")).perform(click());
//        Thread.sleep(1000);
//        onView(withId(R.id.txt_usernameNAV)).check(matches(withText(containsString("yz"))));
//        onView(withId(R.id.txt_useremailNAV)).check(matches(withText(containsString("yz@gmail.com"))));
        onView(withText("Abs Fat Reduction")).perform(click());
        Thread.sleep(1000);
        onView(withText("START WORKOUT")).perform(click());
        Thread.sleep(1500);
        onView(withText("FINISH WORKOUT")).perform(click());
        onView(withText("Beginner")).check(matches(withText(containsString("Beginner"))));
    }
}
