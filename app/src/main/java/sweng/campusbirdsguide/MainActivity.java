package sweng.campusbirdsguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final Long NO_CAMPUS_SELECTED = -1L;

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

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.campusConfiguration), Context.MODE_PRIVATE);
        Long campusId = sharedPreferences.getLong(getString(R.string.campusId), NO_CAMPUS_SELECTED);

        if (!campusId.equals(NO_CAMPUS_SELECTED)) {
            // Create ducks list adapter
            DuckListRecyclerViewAdapter duckListRecyclerViewAdapter = new DuckListRecyclerViewAdapter(generateDummyDucksList());

            RecyclerView ducksList = findViewById(R.id.recycler_view);
            ducksList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            ducksList.setAdapter(duckListRecyclerViewAdapter);
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