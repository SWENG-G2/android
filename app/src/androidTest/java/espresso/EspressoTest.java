package espresso;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.assertion.PositionAssertions.*;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import sweng.campusbirdsguide.AboutUsActivity;
import sweng.campusbirdsguide.AboutUsCreatorsActivity;
import sweng.campusbirdsguide.AboutUsUsageActivity;

import sweng.campusbirdsguide.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class EspressoTest {
    @Rule
    public ActivityScenarioRule<AboutUsActivity> activityRule =
            new ActivityScenarioRule<AboutUsActivity>(AboutUsActivity.class);

    @Test
    public void toolbarDisplayedAtTopOfScreen()
    {
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
        onView(withId(R.id.toolbar)).check(isTopAlignedWith(withId(R.id.aboutus_view_activity)));
    }
    @Test
    public void appNameDisplayedTopOfToolbar()
    {
        onView(withText("Fauna Finder")).check(matches(isDisplayed()));
        onView(withText("Fauna Finder")).check(isTopAlignedWith(withId(R.id.toolbar)));
    }
    @Test
    public void appVersionDisplayedBelowAppName()
    {
        onView(withText("Version 1.0.0")).check(matches(isDisplayed()));
        onView(withText("Version 1.0.0")).check(isCompletelyBelow(withText("Fauna Finder")));
    }
    @Test
    public void appLogoDisplayedBelowToolbar()
    {
        onView(withId(R.id.appLogo)).check(matches(isDisplayed()));
        onView(withId(R.id.appLogo)).check(isCompletelyBelow(withId(R.id.toolbar)));
    }
    @Test
    public void creatorsButtonDisplayedBetweenLogoAndUsageButton()
    {
        onView(withText("About the creators")).check(matches(isDisplayed()));
        onView(withText("About the creators")).check(isCompletelyBelow(withId(R.id.appLogo)));
        onView(withText("About the creators")).check(isCompletelyAbove(withText("How to use the app")));
    }
    @Test
    public void usageButtonDisplayedBetweenCreatorsButtonAndLicensingButton()
    {
        onView(withText("How to use the app")).check(matches(isDisplayed()));
        onView(withText("How to use the app")).check(isCompletelyBelow(withText("About the creators")));
        onView(withText("How to use the app")).check(isCompletelyAbove(withText("Licensing")));
    }
    @Test
    public void licensingButtonDisplayedBelowUsageButton()
    {
        onView(withText("Licensing")).check(matches(isDisplayed()));
        onView(withText("Licensing")).check(isCompletelyBelow(withText("How to use the app")));
    }

    @Test
    public void copyrightDisplayedAtBottomOfPage()
    {
        onView(withText("© 2023 Penelope LTD")).check(matches(isDisplayed()));
        onView(withText("© 2023 Penelope LTD")).check(isCompletelyBelow(withText("Licensing")));
    }
    @Test
    public void creatorsButtonTakesYouToCreatorsPage()
    {
        onView(withId(R.id.creators_btn)).perform(ViewActions.click());
        onView(withId(R.id.aboutus_creators_activity)).check(matches(isDisplayed()));
    }
    @Test
    public void usageButtonTakesYouToUsagePage()
    {
        onView(withId(R.id.usage_btn)).perform(ViewActions.click());
        onView(withId(R.id.aboutus_usage_activity)).check(matches(isDisplayed()));
    }
}

