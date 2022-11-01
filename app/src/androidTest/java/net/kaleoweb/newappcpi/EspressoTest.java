package net.kaleoweb.newappcpi;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.content.Context;
import androidx.test.espresso.Espresso.*;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import net.kaleoweb.newappcpi.ui.home.HomeFragment;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class EspressoTest {
    Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    @Rule
     ActivityScenarioRule activityScenarioRule = new ActivityScenarioRule(HomeFragment.class);
    
    @Test
    public void testButton(){
        onView(withId(R.id.fab)).perform(click());
    }
    
}
