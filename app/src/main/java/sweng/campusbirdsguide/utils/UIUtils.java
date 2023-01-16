package sweng.campusbirdsguide.utils;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

import sweng.campusbirdsguide.R;
import sweng.campusbirdsguide.presentation.SlidesRecyclerViewAdapter;
import sweng.campusbirdsguide.xml.PresentationParser;
import sweng.campusbirdsguide.xml.slide.Slide;

public class UIUtils {
    public static void populateList(String xml, View parent, String slideType, ListItemClickAction listItemClickAction, int horizontalMargin) {
        PresentationParser parser = new PresentationParser();

        try {
            List<Slide> slides = parser.parse(xml, slideType);

            ListItemClickListener listItemClickListener = position -> {
                try {
                    int id = Integer.parseInt(slides.get(position).getTitle());

                    if (listItemClickAction != null)
                        listItemClickAction.performAction(id);
                } catch (NumberFormatException ignored) {}
                // Exception can be ignored, it would happen only on detail slides
            };


            SlidesRecyclerViewAdapter slidesRecyclerViewAdapter = new SlidesRecyclerViewAdapter(slides, listItemClickListener, horizontalMargin);
            RecyclerView recyclerView = parent.findViewById(R.id.recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(parent.getContext()));
            recyclerView.setAdapter(slidesRecyclerViewAdapter);

            // Prevents views from being "written on top of". Perhaps not the best way to do this
            //recyclerView.setItemViewCacheSize(slides.size());
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
    }
}
