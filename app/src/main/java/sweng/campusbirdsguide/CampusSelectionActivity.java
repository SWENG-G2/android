package sweng.campusbirdsguide;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.android.volley.VolleyError;

import sweng.campusbirdsguide.mock.PresentationMock;
import sweng.campusbirdsguide.network.RequestMaker;
import sweng.campusbirdsguide.network.Result;
import sweng.campusbirdsguide.presentation.SlidesRecyclerViewAdapter;
import sweng.campusbirdsguide.utils.ListItemClickAction;
import sweng.campusbirdsguide.utils.UIUtils;
import sweng.campusbirdsguide.xml.slide.SlideFactory;

public class CampusSelectionActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private SharedPreferences sharedPreferences;
    private ConstraintLayout campusSelectionActivity;
    private SlidesRecyclerViewAdapter slidesRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_selection);

        campusSelectionActivity = findViewById(R.id.campus_selection_activity);

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

        ListItemClickAction listItemClickAction = id -> {
            finish();
        };
        PresentationMock campusesListMock = new PresentationMock();
        campusesListMock.addCampus("Test campus 1", "1");
        campusesListMock.addCampus("Test campus 2", "2");

        slidesRecyclerViewAdapter = PresentationMock.mockPopulateUI(campusSelectionActivity, listItemClickAction, 0, campusesListMock.getPresentation());

        // Search functionality
        SearchView searchView = findViewById(R.id.search);
        searchView.setOnQueryTextListener(this);
        searchView.setOnClickListener(v -> searchView.setIconified(false));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
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
}