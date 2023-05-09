package sweng.campusbirdsguide;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.android.volley.VolleyError;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Locale;

import sweng.campusbirdsguide.mock.PresentationMock;
import sweng.campusbirdsguide.network.RequestMaker;
import sweng.campusbirdsguide.network.Result;
import sweng.campusbirdsguide.presentation.SlidesRecyclerViewAdapter;
import sweng.campusbirdsguide.utils.ListItemClickAction;
import sweng.campusbirdsguide.utils.UIUtils;
import sweng.campusbirdsguide.xml.slide.SlideFactory;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, View.OnClickListener {

    private ConstraintLayout mainActivity;
    private SlidesRecyclerViewAdapter slidesRecyclerViewAdapter;
    private MainActivityLifecycleObserver mainActivityLifecycleObserver;

    /**
     * Fetches the birds for the selected campus.
     */
    private void fetchBirds() {
        PresentationMock birdsMock = new PresentationMock();
        birdsMock.addBird("Chicken", "Chickens are domesticated birds that are kept...", PresentationMock.MOCK_IMAGE, "1");
        birdsMock.addBird("Another chicken", "Chickens are domesticated birds that are kept...", PresentationMock.MOCK_IMAGE, "2");

        ListItemClickAction listItemClickAction = id -> {
            Intent birdIntent = new Intent(getApplicationContext(), BirdActivity.class);
            startActivity(birdIntent);
        };

        slidesRecyclerViewAdapter = PresentationMock.mockPopulateUI(mainActivity, listItemClickAction, 5, birdsMock.getPresentation());
        RequestMaker requestMaker = new RequestMaker(getApplicationContext());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivity = findViewById(R.id.main_activity);

        // Register lifecycle observer
        mainActivityLifecycleObserver = new MainActivityLifecycleObserver(getActivityResultRegistry(), this);
        getLifecycle().addObserver(mainActivityLifecycleObserver);

        // Set up FAB
        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(this);

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

        fetchBirds();

        // Search functionality
        SearchView searchView = findViewById(R.id.search);
        searchView.setOnQueryTextListener(this);
        searchView.setOnClickListener(v -> searchView.setIconified(false));
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchBirds();
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

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (slidesRecyclerViewAdapter != null)
            slidesRecyclerViewAdapter.getFilter().filter(newText);

        return false;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab) {
            mainActivityLifecycleObserver.loadBirdFromStorage();
        }
    }
}