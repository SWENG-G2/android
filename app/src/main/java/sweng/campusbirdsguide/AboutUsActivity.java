package sweng.campusbirdsguide;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.oss.licenses.OssLicensesMenuActivity;

import sweng.campusbirdsguide.utils.ListItemClickAction;

public class AboutUsActivity extends AppCompatActivity {

    private ConstraintLayout aboutUsActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutus_view);

        // Setup toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Toggle hide title + display back arrow
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Button licensingBtn = findViewById(R.id.licensing_btn);
        licensingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When the user selects an option to see the licenses:
                startActivity(new Intent(view.getContext(), OssLicensesMenuActivity.class));
            }
        });

        Button usageBtn = findViewById(R.id.usage_btn);
        usageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When the user selects an option to see the licenses:
                startActivity(new Intent(view.getContext(), AboutUsUsageActivity.class));
            }
        });

        Button creatorsBtn = findViewById(R.id.creators_btn);
        creatorsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When the user selects an option to see the licenses:
                startActivity(new Intent(view.getContext(), AboutUsCreatorsActivity.class));
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
