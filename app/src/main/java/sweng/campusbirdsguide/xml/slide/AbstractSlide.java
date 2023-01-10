package sweng.campusbirdsguide.xml.slide;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import java.util.ArrayList;

import sweng.campusbirdsguide.presentation.elements.PresentationElement;

public abstract class AbstractSlide implements Slide {
    protected int width, height, calculatedWidth, calculatedHeight;
    private final String title;
    private final ArrayList<PresentationElement> elements;
    protected final DisplayMetrics displayMetrics;

    public AbstractSlide(int width, int height, String title) {
        displayMetrics = Resources.getSystem().getDisplayMetrics();

        this.height = height;
        this.width = width;
        this.calculatedWidth = displayMetrics.widthPixels;
        // Assume height in SP, since slides need to contain text
        this.calculatedHeight = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, height, displayMetrics));
        this.title = title;

        this.elements = new ArrayList<>();
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getCalculatedWidth() {
        return calculatedWidth;
    }

    @Override
    public int getCalculatedHeight() {
        return calculatedHeight;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public ArrayList<PresentationElement> getElements() {
        return elements;
    }

    @Override
    public void addElement(PresentationElement presentationElement) {
        elements.add(presentationElement);
    }
}
