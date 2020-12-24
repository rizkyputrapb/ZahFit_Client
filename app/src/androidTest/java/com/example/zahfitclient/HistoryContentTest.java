package com.example.zahfitclient;

import androidx.test.espresso.DataInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.zahfitclient.adapter.HistoryAdapter;
import com.example.zahfitclient.model.PlanHistory;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;

public class HistoryContentTest {
    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void historyContentTest() throws InterruptedException {
        onView(withText(startsWith("Login"))).perform(click());
        onView(withId(R.id.txt_Email)).perform(typeText("yazeedarifin@gmail.com"));
        onView(withId(R.id.txt_Password)).perform(typeText("101010"));
        closeSoftKeyboard();
        onView(withText(startsWith("Login"))).perform(click());
        Thread.sleep(3000);
        onView(withContentDescription("Open navigation drawer")).perform(click());
        Thread.sleep(1000);
        onView(withText(startsWith("History"))).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.txt_WorkoutName)).check(matches(isDisplayed()));
//        onData(withClassName((Matcher<String>) new HistoryAdapter())).atPosition(0).check(matches(isDisplayed()));
//        onData(hasEntry(equalTo("plan_name"), is("Abs Fat Reduction"))).check(matches(isDisplayed()));
    }

//    private static DataInteraction onRow(String str){
//        return onData(hasEntry(equalTo(PlanHistory)))
//    }
}
