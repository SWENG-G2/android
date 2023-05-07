package sweng.campusbirdsguide;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.android.volley.VolleyError;

import java.util.Locale;

import sweng.campusbirdsguide.mock.PresentationMock;
import sweng.campusbirdsguide.network.RequestMaker;
import sweng.campusbirdsguide.network.Result;
import sweng.campusbirdsguide.presentation.SlidesRecyclerViewAdapter;
import sweng.campusbirdsguide.utils.UIUtils;
import sweng.campusbirdsguide.xml.slide.SlideFactory;

public class BirdActivity extends AppCompatActivity {
    private ConstraintLayout birdActivity;

    private static final String AUDIO_URL = "https://download.samplelib.com/mp3/sample-6s.mp3";
    private static final String VIDEO_URL = "https://download.samplelib.com/mp4/sample-5s.mp4";

    private void setUpAppBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Display back button
            actionBar.setDisplayHomeAsUpEnabled(true);
            // Hide title
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    private void populateUIAndContent() {
        PresentationMock birdDetailMock = new PresentationMock();
        birdDetailMock.addBirdDetails("#FFE89266", "Test 1", AUDIO_URL, PresentationMock.MOCK_IMAGE, "#FF8A8178", VIDEO_URL, "About this bird", PresentationMock.MOCK_IMAGE, "Diet this bird", PresentationMock.MOCK_IMAGE, "Location this bird");

        PresentationMock.mockPopulateUI(birdActivity, null, 0, birdDetailMock.getPresentation());
        setUpAppBar();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bird);

        birdActivity = findViewById(R.id.bird_activity);

        // Set App bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        populateUIAndContent();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}