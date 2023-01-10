package sweng.campusbirdsguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.android.volley.VolleyError;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import sweng.campusbirdsguide.network.RequestMaker;
import sweng.campusbirdsguide.network.Result;
import sweng.campusbirdsguide.presentation.SlidesRecyclerViewAdapter;
import sweng.campusbirdsguide.xml.PresentationParser;
import sweng.campusbirdsguide.xml.slide.Slide;

public class BirdActivity extends AppCompatActivity {

    private boolean displayBird(String xml) {
        PresentationParser presentationParser = new PresentationParser();

        try {
            List<Slide> slides = presentationParser.parse(xml, "detail");

            SlidesRecyclerViewAdapter slidesRecyclerViewAdapter = new SlidesRecyclerViewAdapter(slides, null, 0);

            RecyclerView recyclerView = findViewById(R.id.recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            recyclerView.setAdapter(slidesRecyclerViewAdapter);

            // Prevents views from being "written on top of". Perhaps not the best way to do this
            recyclerView.setItemViewCacheSize(slides.size());
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bird);

        long birdId = getIntent().getLongExtra("birdId", -1);

        if (birdId == -1)
            finish();

        // Set App bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RequestMaker requestMaker = new RequestMaker(getApplicationContext());

        String birdUrl = getString(R.string.serverURL) + String.format(Locale.UK, "bird/%d", birdId);
        requestMaker.query(birdUrl, new Result() {
            @Override
            public void onSuccess(String string) {
                if (displayBird(string)) {
                    ActionBar actionBar = getSupportActionBar();
                    if (actionBar != null) {
                        // Display back button
                        actionBar.setDisplayHomeAsUpEnabled(true);
                        // Hide title
                        actionBar.setDisplayShowTitleEnabled(false);
                    }
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