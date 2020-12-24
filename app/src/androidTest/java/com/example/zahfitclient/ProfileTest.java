package com.example.zahfitclient;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.startsWith;

public class ProfileTest {
    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void profileTest() throws InterruptedException {
        onView(withText(startsWith("Login"))).perform(click());
        onView(withId(R.id.txt_Email)).perform(typeText("yz@gmail.com"));
        onView(withId(R.id.txt_Password)).perform(typeText("101010"));
        closeSoftKeyboard();
        onView(withText(startsWith("Login"))).perform(click());
        Thread.sleep(3000);
        onView(withContentDescription("Open navigation drawer")).perform(click());
        Thread.sleep(1000);
        onView(withText(startsWith("Settings"))).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.textView34)).perform(click());
        Thread.sleep(1000);
        onView(withId(R.id.txt_EditName)).perform(replaceText("yz"));
        Thread.sleep(500);
        onView(withId(R.id.txt_EditEmail)).perform(replaceText("yz@gmail.com"));
        Thread.sleep(500);
        onView(withId(R.id.txt_EditUsername)).perform(replaceText("yazeedx"));
        Thread.sleep(500);
        onView(withId(R.id.txt_EditAge)).perform(replaceText("99"));
        Thread.sleep(500);
        onView(withId(R.id.txt_EditHeight)).perform(replaceText("199"));
        Thread.sleep(500);
        onView(withId(R.id.txt_EditWeight)).perform(replaceText("80"));
        Thread.sleep(500);
        onView(withId(R.id.btn_ConfirmEdit)).perform(click());
        Thread.sleep(500);
        onView(withContentDescription("Open navigation drawer")).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.txt_usernameNAV)).check(matches(withText(containsString("yazeedx"))));
        Thread.sleep(500);
        onView(withId(R.id.txt_useremailNAV)).check(matches(withText(containsString("yz@gmail.com"))));
        Thread.sleep(500);
        System.out.println("Edit Profile succeed");
//        onData(withClassName((Matcher<String>) new HistoryAdapter())).atPosition(0).check(matches(isDisplayed()));
//        onData(hasEntry(equalTo("plan_name"), is("Abs Fat Reduction"))).check(matches(isDisplayed()));
    }
}
