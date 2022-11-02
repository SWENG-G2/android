package sweng.campusbirdsguide;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class MainActivity extends AppCompatActivity {
    private final MainContentSheet mainContentSheet = new MainContentSheet();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // User must interact through bottom sheet
        mainContentSheet.setCancelable(false);
        mainContentSheet.show(getSupportFragmentManager(), mainContentSheet.TAG);

    }

    @Override
    protected void onStart() {
        super.onStart();

        BottomSheetDialog bottomSheetDialog = ((BottomSheetDialog) mainContentSheet.getDialog());
        if (bottomSheetDialog != null) {
            BottomSheetBehavior<FrameLayout> bottomSheetBehavior = bottomSheetDialog.getBehavior();
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else {
            System.out.println("I didn't think this could happen");
        }
    }
}