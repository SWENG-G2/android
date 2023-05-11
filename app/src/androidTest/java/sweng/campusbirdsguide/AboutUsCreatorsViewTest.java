package sweng.campusbirdsguide;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.assertion.PositionAssertions.*;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.google.android.gms.oss.licenses.OssLicensesMenuActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AboutUsCreatorsViewTest {
    @Rule
    public ActivityScenarioRule<AboutUsCreatorsActivity> activityRule =
            new ActivityScenarioRule<AboutUsCreatorsActivity>(AboutUsCreatorsActivity.class);

    @Before
    public void init() {
        Intents.init();
    }
    @After
    public void cleanUp() {
        Intents.release();
    }

    @Test
    public void toolbarDisplayedAtTopOfScreen()
    {
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
        onView(withId(R.id.toolbar)).check(isTopAlignedWith(withId(R.id.aboutus_creators_activity)));
    }
    @Test
    public void screenTitleDisplayedInToolbar()
    {
        onView(withText("About the creators")).check(matches(isDisplayed()));
    }
    @Test
    public void penelopeLogoDisplayedBelowToolbar()
    {
        onView(withId(R.id.penelopeLogo)).check(matches(isDisplayed()));
        onView(withId(R.id.penelopeLogo)).check(isCompletelyBelow(withId(R.id.toolbar)));
    }
    @Test
    public void descriptionDisplayedBelowLogoAboveCopyright()
    {
        onView(withText(R.string.about_penelope)).check(matches(isDisplayed()));
        onView(withText(R.string.about_penelope)).check(isCompletelyBelow(withId(R.id.penelopeLogo)));
        onView(withText(R.string.about_penelope)).check(isCompletelyAbove(withText(R.string.penelope_copyright)));
    }
    @Test
    public void copyrightDisplayedAtBottomOfPage()
    {
        onView(withText(R.string.penelope_copyright)).check(matches(isDisplayed()));
        onView(withText(R.string.penelope_copyright)).check(isCompletelyBelow(withText(R.string.about_penelope)));
    }
}
