package com.example.zahfitclient;

import android.service.autofill.Validator;
import android.view.View;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.rule.ActivityTestRule;

import static android.app.PendingIntent.getActivity;
import static android.service.autofill.Validators.not;
import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;

public class LogoutTest {
    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<MainActivity>(MainActivity.class);
//    Test Passed
    @Test
    public void LogoutTest() throws InterruptedException {
        onView(withText(startsWith("Login"))).perform(click());
        onView(withId(R.id.txt_Email)).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.txt_Email)).perform(typeText("yz@gmail.com"));
        Thread.sleep(500);
        onView(withId(R.id.txt_Password)).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.txt_Password)).perform(typeText("101010"));
        closeSoftKeyboard();
        onView(withText(startsWith("Login"))).perform(click());
        Thread.sleep(3000);
        onView(withContentDescription("Open navigation drawer")).perform(click());
        Thread.sleep(500);
        onView(withText(startsWith("Settings"))).perform(click());
        Thread.sleep(500);
        onView(withText(startsWith("Log Out"))).perform(click());
        onView(withId(R.id.btnLogin)).check(matches(withText(containsString("Login"))));
        onView(withId(R.id.btnRegister)).check(matches(withText(containsString("Register"))));
    }
}
