package sweng.campusbirdsguide;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import sweng.campusbirdsguide.network.RequestMaker;
import sweng.campusbirdsguide.network.Result;
import sweng.campusbirdsguide.presentation.ListItemClickListener;
import sweng.campusbirdsguide.presentation.SlidesRecyclerViewAdapter;
import sweng.campusbirdsguide.xml.PresentationParser;
import sweng.campusbirdsguide.xml.slide.Slide;

public class MainActivity extends AppCompatActivity {

    private static final long NO_CAMPUS_SELECTED = -1L;

    private long campusId;
    private SharedPreferences sharedPreferences;

    private void populateList(String xml) {
        PresentationParser parser = new PresentationParser();

        try {
            List<Slide> slides = parser.parse(xml, "bird");

            ListItemClickListener listItemClickListener = position -> {
                long birdId = Long.parseLong(slides.get(position).getTitle());

                Intent birdIntent = new Intent(this, BirdActivity.class);
                birdIntent.putExtra("birdId", birdId);
                startActivity(birdIntent);
            };

            SlidesRecyclerViewAdapter slidesRecyclerViewAdapter = new SlidesRecyclerViewAdapter(slides, listItemClickListener, 5);
            RecyclerView recyclerView = findViewById(R.id.recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            recyclerView.setAdapter(slidesRecyclerViewAdapter);

            // Prevents views from being "written on top of". Perhaps not the best way to do this
            recyclerView.setItemViewCacheSize(slides.size());
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
    }

    private void fetchBirds() {
        RequestMaker requestMaker = new RequestMaker(getApplicationContext());

        String birdsUrl = getString(R.string.serverURL) + String.format(Locale.UK, getString(R.string.birdsList), campusId);
        requestMaker.query(birdsUrl, new Result() {
            @Override
            public void onSuccess(String string) {
                populateList(string);
                // Hide progress loading spinner
                findViewById(R.id.loading).setVisibility(View.GONE);
            }

            @Override
            public void onError(VolleyError volleyError) {
                System.out.println(volleyError.getMessage());
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set app bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Hide default title and use change location icon as home
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_twotone_edit_location_alt_24);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        sharedPreferences = getSharedPreferences(getString(R.string.campusConfiguration), Context.MODE_PRIVATE);
        campusId = sharedPreferences.getLong(getString(R.string.campusId), NO_CAMPUS_SELECTED);

        if (campusId != NO_CAMPUS_SELECTED) {
            // Show loading spinner
            findViewById(R.id.loading).setVisibility(View.VISIBLE);
            fetchBirds();
        }
        // Show select location hint
        else findViewById(R.id.select_location_tv).setVisibility(View.VISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        long newCampusId = sharedPreferences.getLong(getString(R.string.campusId), NO_CAMPUS_SELECTED);
        if (newCampusId != campusId) {
            campusId = newCampusId;
            // Hide select location hint
            findViewById(R.id.select_location_tv).setVisibility(View.GONE);
            // Show loading spinner
            findViewById(R.id.loading).setVisibility(View.VISIBLE);
            fetchBirds();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Load menu in app bar
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_items, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent chooseLocationIntent = new Intent(this, CampusSelectionActivity.class);
            startActivity(chooseLocationIntent);
        } else { // Other possibility can only be the about button
            Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * This method generates an array of 20 ducks names.
     *
     * @return An array containing 20 example ducks name.
     */
    private String[] generateDummyDucksList() {
        List<String> ducks = new ArrayList<>();

        for (int i = 0; i < 21; i++) {
            ducks.add(String.format(Locale.getDefault(), "Duck #%d", i + 1));
        }

        return ducks.toArray(new String[20]);
    }
}