package net.kaleoweb.newappcpi;


import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.Espresso.onView;
import net.kaleoweb.newappcpi.forms.StoreConstantes;

import static androidx.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(AndroidJUnit4.class)
public class StoreConstantesTest {
    
    @Rule
    public ActivityScenarioRule mActiviTyRule = new ActivityScenarioRule(StoreConstantes.class);
    
    @Test
    public void testButtonStore(){
        onView(withId(R.id.nomvictime)).perform(replaceText("dédé la morille"));
        onView(withId(R.id.diastole)).perform(replaceText("12"));
        onView(withId(R.id.systole)).perform(replaceText("7"));
        onView(withId(R.id.poulsradial)).perform(replaceText("66"));
        onView(withId(R.id.laglycemie)).perform(replaceText("0.75"));
        onView(withId(R.id.temperature)).perform(replaceText("40.3"));
        onView(withId(R.id.respi)).perform(replaceText("27"));
        
        onView(withId(R.id.gotostore)).perform(click());
    }
    
}
