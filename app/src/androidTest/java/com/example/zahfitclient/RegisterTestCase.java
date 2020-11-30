package com.example.zahfitclient;

import org.junit.Rule;
import org.junit.Test;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

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

public class RegisterTestCase {
    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void registertest() throws InterruptedException {
        onView(withText(startsWith("Register"))).perform(click());
        onView(withId(R.id.txt_nameregister)).perform(typeText("Tester 69"));
        closeSoftKeyboard();
        onView(withId(R.id.txt_emailregister)).perform(typeText("tester69@gmail.com"));
        closeSoftKeyboard();
        onView(withId(R.id.txt_passwordregister)).perform(typeText("12345678"));
        closeSoftKeyboard();
        onView(withId(R.id.txt_usernameregister)).perform(typeText("Tester69"));
        closeSoftKeyboard();
        onView(withId(R.id.txt_ageregister)).perform(typeText("29"));
        onView(withId(R.id.txt_heightregister)).perform(typeText("190"));
        onView(withId(R.id.txt_weightregister)).perform(typeText("69"));
        closeSoftKeyboard();
        onView(withText(startsWith("Register"))).perform(click());
        Thread.sleep(3000);
        onView(withContentDescription("Open navigation drawer")).perform(click());
        Thread.sleep(1000);
        onView(withId(R.id.txt_usernameNAV)).check(matches(withText(containsString("Tester69"))));
        onView(withId(R.id.txt_useremailNAV)).check(matches(withText(containsString("tester69@gmail.com"))));
    }
}
