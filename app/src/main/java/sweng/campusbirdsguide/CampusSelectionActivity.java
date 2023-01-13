package sweng.campusbirdsguide;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.android.volley.VolleyError;

import sweng.campusbirdsguide.network.RequestMaker;
import sweng.campusbirdsguide.network.Result;
import sweng.campusbirdsguide.utils.ListItemClickAction;
import sweng.campusbirdsguide.utils.UIUtils;
import sweng.campusbirdsguide.xml.slide.SlideFactory;

public class CampusSelectionActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private ConstraintLayout campusSelectionActivity;

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

        RequestMaker requestMaker = new RequestMaker(getApplicationContext());

        String campusListUrl = getString(R.string.serverURL) + getString(R.string.campusesList);
        requestMaker.query(campusListUrl, new Result() {
            @Override
            public void onSuccess(String string) {
                ListItemClickAction listItemClickAction = id -> {
                    sharedPreferences.edit().putInt(getString(R.string.campusId), id).apply();
                    finish();
                };
                UIUtils.populateList(string, campusSelectionActivity, SlideFactory.BASIC_SLIDE, listItemClickAction, 0);
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