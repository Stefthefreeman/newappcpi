package net.kaleoweb.newappcpi;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class storeusertest {
    
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    
    @Test
    public void storeusertest() {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.nameagent),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.textfield.TextInputLayout")),
                                        0),
                                0)));
        appCompatEditText.perform(scrollTo(), click());
        
        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.nameagent),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.textfield.TextInputLayout")),
                                        0),
                                0)));
        appCompatEditText2.perform(scrollTo(), replaceText("choytard"), closeSoftKeyboard());
        
        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.nameagent), withText("choytard"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.textfield.TextInputLayout")),
                                        0),
                                0)));
        appCompatEditText3.perform(pressImeActionButton());
        
        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.surnameagent),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.textfield.TextInputLayout")),
                                        0),
                                0)));
        appCompatEditText4.perform(scrollTo(), replaceText("stef"), closeSoftKeyboard());
        
        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.surnameagent), withText("stef"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.textfield.TextInputLayout")),
                                        0),
                                0)));
        appCompatEditText5.perform(pressImeActionButton());
        
        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.centreaff),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.textfield.TextInputLayout")),
                                        0),
                                0)));
        appCompatEditText6.perform(scrollTo(), replaceText("fdre"), closeSoftKeyboard());
        
        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.centreaff), withText("fdre"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.textfield.TextInputLayout")),
                                        0),
                                0)));
        appCompatEditText7.perform(pressImeActionButton());
        
        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.grade),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.textfield.TextInputLayout")),
                                        0),
                                0)));
        appCompatEditText8.perform(scrollTo(), replaceText("eer_"), closeSoftKeyboard());
        
        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.grade), withText("eer_"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.textfield.TextInputLayout")),
                                        0),
                                0)));
        appCompatEditText9.perform(pressImeActionButton());
        
        ViewInteraction materialButton = onView(
                allOf(withId(R.id.button), withText("Valider"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        materialButton.perform(scrollTo(), click());
    }
    
    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {
        
        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }
            
            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
