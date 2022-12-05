package sweng.campusbirdsguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;

import com.android.volley.VolleyError;

import sweng.campusbirdsguide.utils.RequestMaker;
import sweng.campusbirdsguide.utils.Result;

public class CampusSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_selection);

        // Set app bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Hide default title and use change location icon as home
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
                System.out.println(string);
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