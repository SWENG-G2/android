package sweng.campusbirdsguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.android.volley.VolleyError;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import sweng.campusbirdsguide.presentation.ListItemClickListener;
import sweng.campusbirdsguide.presentation.SlidesRecyclerViewAdapter;
import sweng.campusbirdsguide.network.RequestMaker;
import sweng.campusbirdsguide.network.Result;
import sweng.campusbirdsguide.presentation.elements.PresentationElement;
import sweng.campusbirdsguide.xml.PresentationParser;
import sweng.campusbirdsguide.xml.Slide;

public class CampusSelectionActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private void populateList(String xml) {
        PresentationParser parser = new PresentationParser();

        try {
            List<Slide> slides = parser.parse(xml);

            ListItemClickListener listItemClickListener = position -> {
                long campusId = Long.parseLong(slides.get(position).getTitle());
                sharedPreferences.edit().putLong(getString(R.string.campusId), campusId).apply();
                finish();
            };

            SlidesRecyclerViewAdapter slidesRecyclerViewAdapter = new SlidesRecyclerViewAdapter(slides, listItemClickListener);
            RecyclerView recyclerView = findViewById(R.id.recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            recyclerView.setAdapter(slidesRecyclerViewAdapter);
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_selection);

        sharedPreferences = getSharedPreferences(getString(R.string.campusConfiguration), Context.MODE_PRIVATE);

        // Set app bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Hide default title and display back arrow
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        RequestMaker requestMaker = new RequestMaker(getApplicationContext());

        String campusListUrl = getString(R.string.serverURL) + getString(R.string.campusesList);
        requestMaker.query(campusListUrl, new Result() {
            @Override
            public void onSuccess(String string) {
                populateList(string);
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