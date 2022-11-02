package sweng.campusbirdsguide;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link BottomSheetDialogFragment} subclass.
 */
public class MainContentSheet extends BottomSheetDialogFragment {
    protected String TAG = "CBGBottomSheetDialogFragment";

    private ViewPager2 viewPager;
    private FragmentStateAdapter fragmentStateAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Apply rounded corner style
        setStyle(STYLE_NORMAL, R.style.Theme_CampusBirdsGuide_BottomSheetDialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_content_sheet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager = view.findViewById(R.id.bottom_sheet_pager);
        TabLayout tabLayout = view.findViewById(R.id.bottom_sheet_tab_layout);

    }
}