package sweng.campusbirdsguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import com.android.volley.VolleyError;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import sweng.campusbirdsguide.network.RequestMaker;
import sweng.campusbirdsguide.network.Result;
import sweng.campusbirdsguide.presentation.CanvasView;
import sweng.campusbirdsguide.presentation.elements.PresentationElement;
import sweng.campusbirdsguide.presentation.elv.SlideELVAdapter;
import sweng.campusbirdsguide.xml.PresentationParser;
import sweng.campusbirdsguide.xml.slide.Slide;

public class BirdActivity extends AppCompatActivity {

    private boolean displayBird(String xml) {
        PresentationParser presentationParser = new PresentationParser();

        try {
            List<Slide> slides = presentationParser.parse(xml, "detail");

            for(Slide slide : slides) {
                if (slide.getTitle().equals("heroSlide")) {
                    LinearLayout heroView = findViewById(R.id.hero_slide);

                    ViewGroup.LayoutParams heroLayoutParams = heroView.getLayoutParams();
                    heroLayoutParams.width = slide.getCalculatedWidth();
                    heroLayoutParams.height = slide.getCalculatedHeight();

                    heroView.setLayoutParams(heroLayoutParams);

                    ConstraintLayout constraintLayout = heroView.findViewById(R.id.slide);
                    CanvasView canvas = heroView.findViewById(R.id.canvas);

                    ArrayList<PresentationElement> shapes = new ArrayList<>();
                    for (PresentationElement element : slide.getElements()) {
                        if (element.isShape()) shapes.add(element);
                        else constraintLayout.addView(element.getView(constraintLayout, slide));
                    }

                    canvas.setSlide(slide);
                    canvas.setShapes(shapes);
                    canvas.invalidate();
                }
            }

            ExpandableListView expandableListView = findViewById(R.id.expandable_list_view);
            SlideELVAdapter slideELVAdapter = new SlideELVAdapter(slides, getApplicationContext());
            expandableListView.setAdapter(slideELVAdapter);

//            SlidesRecyclerViewAdapter slidesRecyclerViewAdapter = new SlidesRecyclerViewAdapter(slides, null, 0);
//
//            RecyclerView recyclerView = findViewById(R.id.recycler_view);
//            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//            recyclerView.setAdapter(slidesRecyclerViewAdapter);
//
//            // Prevents views from being "written on top of". Perhaps not the best way to do this
//            recyclerView.setItemViewCacheSize(slides.size());
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bird);

        long birdId = getIntent().getLongExtra("birdId", -1);

        if (birdId == -1)
            finish();

        // Set App bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RequestMaker requestMaker = new RequestMaker(getApplicationContext());

        String birdUrl = getString(R.string.serverURL) + String.format(Locale.UK, "bird/%d", birdId);
        requestMaker.query(birdUrl, new Result() {
            @Override
            public void onSuccess(String string) {
                if (displayBird(string)) {
                    ActionBar actionBar = getSupportActionBar();
                    if (actionBar != null) {
                        // Display back button
                        actionBar.setDisplayHomeAsUpEnabled(true);
                        // Hide title
                        actionBar.setDisplayShowTitleEnabled(false);
                    }
                }
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