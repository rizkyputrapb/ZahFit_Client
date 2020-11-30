package com.example.zahfitclient;

import android.os.IBinder;
import android.view.WindowManager;

import org.hamcrest.TypeSafeMatcher;
import org.junit.runner.Description;

import androidx.test.espresso.Root;

public class ToastMatcher extends TypeSafeMatcher<Root> {

    @Override public boolean matchesSafely(Root root) {
        int type = root.getWindowLayoutParams().get().type;
        if ((type == WindowManager.LayoutParams.TYPE_TOAST)) {
            IBinder windowToken = root.getDecorView().getWindowToken();
            IBinder appToken = root.getDecorView().getApplicationWindowToken();
            if (windowToken == appToken) {
                //means this window isn't contained by any other windows.
            }
        }
        return false;
    }

    @Override
    public void describeTo(org.hamcrest.Description description) {
        description.appendText("is toast");
    }
}
