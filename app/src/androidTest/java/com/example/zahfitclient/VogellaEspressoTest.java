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

public class VogellaEspressoTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void VogellaEspressoTest() throws InterruptedException {
        onView(withText(startsWith("Login"))).perform(click());
        onView(withId(R.id.txt_Email)).perform(typeText("rizkyputra@gmail.com"));
        onView(withId(R.id.txt_Password)).perform(typeText("12345678"));
        closeSoftKeyboard();
        onView(withText(startsWith("Login"))).perform(click());
        Thread.sleep(3000);
        onView(withContentDescription("Open navigation drawer")).perform(click());
        Thread.sleep(1000);
        onView(withId(R.id.txt_usernameNAV)).check(matches(withText(containsString("sinanju_ID"))));
        onView(withId(R.id.txt_useremailNAV)).check(matches(withText(containsString("rizkyputra@gmail.com"))));
    }

//    @Test
//    public void bademail() throws InterruptedException {
//        onView(withText(startsWith("Login"))).perform(click());
//        onView(withId(R.id.txt_Email)).perform(typeText("rizkyputra"));
//        onView(withId(R.id.txt_Password)).perform(typeText("12345678"));
//        closeSoftKeyboard();
//        onView(withText(startsWith("Login"))).perform(click());
//        Thread.sleep(1000);
//        onView(withText("Login Failure: The email address is badly formatted")).inRoot(new ToastMatcher())
//                .check(matches(isDisplayed()));
//    }
}
