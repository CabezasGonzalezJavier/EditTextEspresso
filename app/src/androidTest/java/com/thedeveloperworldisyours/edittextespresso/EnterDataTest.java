package com.thedeveloperworldisyours.edittextespresso;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;

/**
 * Created by javierg on 15/06/16.
 */
public class EnterDataTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void checkWithRawText() {

                onView(ViewMatchers.withId(R.id.email_sign_in_button))
                .perform(ViewActions.click());

                onView(ViewMatchers.withId((R.id.email)))
                .check(ViewAssertions.matches(ErrorTextMatchers.withErrorText("This field is required")));
    }

    @Test
    public void checkWithStringId() {

                onView(ViewMatchers.withId((R.id.email)))
                .perform(ViewActions.typeText("invalid"));

                onView(ViewMatchers.withId(R.id.email_sign_in_button))
                .perform(ViewActions.click());

                onView(ViewMatchers.withId((R.id.email)))
                .check(ViewAssertions.matches(ErrorTextMatchers.withErrorText(R.string.error_invalid_email)));
    }

    @Test
    public void checkWithMatcher() {

                onView(ViewMatchers.withId((R.id.email)))
                .perform(ViewActions.typeText("user@example.com"));

                onView(ViewMatchers.withId((R.id.password)))
                .perform(ViewActions.typeText("foo"));

                onView(ViewMatchers.withId(R.id.email_sign_in_button))
                .perform(ViewActions.click());

                onView(ViewMatchers.withId((R.id.password)))
                .check(ViewAssertions.matches(ErrorTextMatchers.withErrorText(Matchers.containsString("too short"))));
    }

}
