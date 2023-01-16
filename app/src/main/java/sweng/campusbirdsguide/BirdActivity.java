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

import sweng.campusbirdsguide.network.RequestMaker;
import sweng.campusbirdsguide.network.Result;
import sweng.campusbirdsguide.presentation.SlidesRecyclerViewAdapter;
import sweng.campusbirdsguide.utils.UIUtils;
import sweng.campusbirdsguide.xml.slide.SlideFactory;

public class BirdActivity extends AppCompatActivity {
    private ConstraintLayout birdActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bird);

        int birdId = getIntent().getIntExtra("birdId", -1);

        if (birdId == -1)
            finish();

        birdActivity = findViewById(R.id.bird_activity);

        // Set App bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RequestMaker requestMaker = new RequestMaker(getApplicationContext());

        String birdUrl = getString(R.string.serverURL) + String.format(Locale.UK, "bird/%d", birdId);
        requestMaker.query(birdUrl, new Result() {
            @Override
            public void onSuccess(String response) {
                UIUtils.populateList(response, birdActivity, SlideFactory.DETAIL_SLIDE, null, 0);
                ActionBar actionBar = getSupportActionBar();
                if (actionBar != null) {
                    // Display back button
                    actionBar.setDisplayHomeAsUpEnabled(true);
                    // Hide title
                    actionBar.setDisplayShowTitleEnabled(false);
                }
            }

            @Override
            public void onError(VolleyError volleyError) {
                System.out.println(volleyError.getMessage());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}