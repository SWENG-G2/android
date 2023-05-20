package sweng.campusbirdsguide.mock;

import android.graphics.Color;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import sweng.campusbirdsguide.R;
import sweng.campusbirdsguide.presentation.SlidesRecyclerViewAdapter;
import sweng.campusbirdsguide.presentation.elements.AudioElement;
import sweng.campusbirdsguide.presentation.elements.CircleElement;
import sweng.campusbirdsguide.presentation.elements.ImageElement;
import sweng.campusbirdsguide.presentation.elements.LineElement;
import sweng.campusbirdsguide.presentation.elements.PresentationElement;
import sweng.campusbirdsguide.presentation.elements.RectangleElement;
import sweng.campusbirdsguide.presentation.elements.TextElement;
import sweng.campusbirdsguide.presentation.elements.VideoElement;
import sweng.campusbirdsguide.utils.ListItemClickAction;
import sweng.campusbirdsguide.utils.ListItemClickListener;
import sweng.campusbirdsguide.xml.slide.Slide;
import sweng.campusbirdsguide.xml.slide.SlideFactory;
import sweng.campusbirdsguide.xml.utils.ElementParser;

public class PresentationMock {
    private static final int SLIDE_WIDTH = 1920;
    private static final String FONT = "mono";
    private static final int FONT_SIZE_TITLE = 32;
    private static final int FONT_SIZE_TITLE_SM = 22;
    private static final int FONT_SIZE_TITLE_MD = 28;
    private static final int FONT_SIZE_BODY = 18;
    private static final int MATCH_PARENT = -4;
    private static final int WRAP_CONTENT = -5;
    private static final int ALWAYS_ON_SCREEN = -1;
    private static final int CENTER_IN_PARENT = -2;
    private final List<Slide> presentation;

    public static final String MOCK_IMAGE = "https://media.istockphoto.com/id/93077566/photo/hen-isolated-on-a-white-background.jpg?s=612x612&w=0&k=20&c=SR_60pEl5azVFBcLow-vhxuuWmB9gtZB-eKMpVImDu0=";

    public PresentationMock() {
        presentation = new ArrayList<>();
    }

    public List<Slide> getPresentation() {
        return presentation;
    }

    public void addCampus(String name, String id) {
        Slide slide = SlideFactory.createSlide(SlideFactory.BASIC_SLIDE, SLIDE_WIDTH, 60, id);

        TextElement campusName = new TextElement(FONT, FONT_SIZE_TITLE_SM, Color.BLACK, 100, 5, MATCH_PARENT, WRAP_CONTENT, ALWAYS_ON_SCREEN);
        campusName.setContent(name);
        PresentationElement line = new LineElement(2, 100, 60, 1820, 60, Color.parseColor("#FF8A8178"));

        slide.addElement(campusName);
        slide.addElement(line);

        presentation.add(slide);
    }

    public void addBird(String name, String description, String listImageUrl, String id) {
        Slide slide = SlideFactory.createSlide(SlideFactory.BIRD_SLIDE, SLIDE_WIDTH, 110, id);

        TextElement birdName = new TextElement(FONT, FONT_SIZE_TITLE_SM, Color.BLACK, 560, 15, 1300, WRAP_CONTENT, ALWAYS_ON_SCREEN);
        birdName.setContent(name);

        TextElement birdDescription = new TextElement(FONT, FONT_SIZE_BODY, Color.BLACK, 560, 45, 1300, WRAP_CONTENT, ALWAYS_ON_SCREEN);
        birdDescription.setContent(description);

        PresentationElement image = new ImageElement(listImageUrl, 480, 100, 40, 5, 0, 0, ALWAYS_ON_SCREEN);

        slide.addElement(birdName);
        slide.addElement(birdDescription);
        slide.addElement(image);

        presentation.add(slide);
    }

    private Slide makeDetailSlide(String imageUrl, String videoUrl, String description, String title) {
        Slide detail = SlideFactory.createSlide(SlideFactory.DETAIL_SLIDE, SLIDE_WIDTH, -1, title);
        PresentationElement media = null;

        if (imageUrl != null)
            media = new ImageElement(imageUrl, 1700, 200, CENTER_IN_PARENT, 0, 0, 0, -1);
        else if (videoUrl != null)
            media = new VideoElement(videoUrl, 1820, 250, CENTER_IN_PARENT, 0, false);

        TextElement content = new TextElement(FONT, FONT_SIZE_BODY, Color.BLACK, 20, 250, 1880, WRAP_CONTENT, -1);
        content.setContent(description);

        if (media != null) detail.addElement(media);

        detail.addElement(content);

        return detail;
    }

    public void addBirdDetails(String rectangleColor, String name, String audioUrl, String imageUrl, String circleColor, String aboutMeVideoUrl, String aboutMe, String dietImageUrl, String diet, String locationImageUrl, String location) {
        // Hero section
        Slide hero = SlideFactory.createSlide(SlideFactory.BASIC_SLIDE, SLIDE_WIDTH, 485, "heroSlide");
        PresentationElement rectangle = new RectangleElement(SLIDE_WIDTH, 100, Color.parseColor(rectangleColor), 0, 0, 0, 0);
        TextElement birdName = new TextElement(FONT, FONT_SIZE_TITLE_MD, Color.BLACK, 115, 25, MATCH_PARENT, WRAP_CONTENT, ALWAYS_ON_SCREEN);
        birdName.setContent(name);
        PresentationElement audio = new AudioElement(audioUrl, false, -3, 0);
        PresentationElement image = new ImageElement(imageUrl, 1700, 360, CENTER_IN_PARENT, 115, 0, 0, ALWAYS_ON_SCREEN);
        PresentationElement circle = new CircleElement(175, Color.TRANSPARENT, 15, Color.parseColor(circleColor), CENTER_IN_PARENT, -120);
        hero.addElement(rectangle);
        hero.addElement(birdName);
        hero.addElement(audio);
        hero.addElement(image);
        hero.addElement(circle);

        // About me
        Slide aboutMeSlide = makeDetailSlide(null, aboutMeVideoUrl, aboutMe, "About me");

        // Diet
        Slide dietSlide = makeDetailSlide(dietImageUrl, null, diet, "Diet");

        // Location
        Slide locationSlide = makeDetailSlide(locationImageUrl, null, location, "Location");

        presentation.add(hero);
        presentation.add(aboutMeSlide);
        presentation.add(dietSlide);
        presentation.add(locationSlide);
    }

    public static SlidesRecyclerViewAdapter mockPopulateUI(View parent, ListItemClickAction listItemClickAction, int horizontalMargin, List<Slide> slides) {
        // Construct click listener callback
        ListItemClickListener listItemClickListener = id -> {
            try {
                if (listItemClickAction != null)
                    listItemClickAction.performAction(id);
            } catch (NumberFormatException ignored) {
            }
            // Exception can be ignored, it would happen only on detail slides
        };


        SlidesRecyclerViewAdapter slidesRecyclerViewAdapter = new SlidesRecyclerViewAdapter(slides, listItemClickListener, horizontalMargin);
        RecyclerView recyclerView = parent.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(parent.getContext()));
        recyclerView.setAdapter(slidesRecyclerViewAdapter);

        return slidesRecyclerViewAdapter;
    }
}
