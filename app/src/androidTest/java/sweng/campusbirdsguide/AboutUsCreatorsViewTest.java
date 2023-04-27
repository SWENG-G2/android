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
        onView(withText("Penelope was founded in the UK at the University of York in 2022 by a group of students who were passionate about creating high-quality android applications. They started the organisation from the ground up, focusing on building a team that was dedicated to innovation, collaboration, and customer satisfaction. Today, Penelope is a thriving company that continues to embody these values. We believe that effective project management is crucial to our success, and we use a variety of strategies to facilitate innovation and communication in the work environment. Our goal is to provide our customers with a product that is not only functional but also visually appealing and easy to use. We also strive to create a work environment where our employees can grow and develop their skills, and where they feel proud to be part of our team. Our current ongoing goal has been the development and sale an educational Android application to provide students of campus-based universities, and local members of the public, a means of identifying and learning about the local wildlife. By providing potential future students with information on the wildlife that exists on campus, we are giving them the opportunity to fall in love with the university even prior to beginning their studies.")).check(matches(isDisplayed()));
        onView(withText("Penelope was founded in the UK at the University of York in 2022 by a group of students who were passionate about creating high-quality android applications. They started the organisation from the ground up, focusing on building a team that was dedicated to innovation, collaboration, and customer satisfaction. Today, Penelope is a thriving company that continues to embody these values. We believe that effective project management is crucial to our success, and we use a variety of strategies to facilitate innovation and communication in the work environment. Our goal is to provide our customers with a product that is not only functional but also visually appealing and easy to use. We also strive to create a work environment where our employees can grow and develop their skills, and where they feel proud to be part of our team. Our current ongoing goal has been the development and sale an educational Android application to provide students of campus-based universities, and local members of the public, a means of identifying and learning about the local wildlife. By providing potential future students with information on the wildlife that exists on campus, we are giving them the opportunity to fall in love with the university even prior to beginning their studies.")).check(isCompletelyBelow(withId(R.id.penelopeLogo)));
        onView(withText("Penelope was founded in the UK at the University of York in 2022 by a group of students who were passionate about creating high-quality android applications. They started the organisation from the ground up, focusing on building a team that was dedicated to innovation, collaboration, and customer satisfaction. Today, Penelope is a thriving company that continues to embody these values. We believe that effective project management is crucial to our success, and we use a variety of strategies to facilitate innovation and communication in the work environment. Our goal is to provide our customers with a product that is not only functional but also visually appealing and easy to use. We also strive to create a work environment where our employees can grow and develop their skills, and where they feel proud to be part of our team. Our current ongoing goal has been the development and sale an educational Android application to provide students of campus-based universities, and local members of the public, a means of identifying and learning about the local wildlife. By providing potential future students with information on the wildlife that exists on campus, we are giving them the opportunity to fall in love with the university even prior to beginning their studies.")).check(isCompletelyAbove(withText("© 2023 Penelope LTD")));
    }
    @Test
    public void copyrightDisplayedAtBottomOfPage()
    {
        onView(withText("© 2023 Penelope LTD")).check(matches(isDisplayed()));
        onView(withText("© 2023 Penelope LTD")).check(isCompletelyBelow(withText("Penelope was founded in the UK at the University of York in 2022 by a group of students who were passionate about creating high-quality android applications. They started the organisation from the ground up, focusing on building a team that was dedicated to innovation, collaboration, and customer satisfaction. Today, Penelope is a thriving company that continues to embody these values. We believe that effective project management is crucial to our success, and we use a variety of strategies to facilitate innovation and communication in the work environment. Our goal is to provide our customers with a product that is not only functional but also visually appealing and easy to use. We also strive to create a work environment where our employees can grow and develop their skills, and where they feel proud to be part of our team. Our current ongoing goal has been the development and sale an educational Android application to provide students of campus-based universities, and local members of the public, a means of identifying and learning about the local wildlife. By providing potential future students with information on the wildlife that exists on campus, we are giving them the opportunity to fall in love with the university even prior to beginning their studies.")));
    }
}
