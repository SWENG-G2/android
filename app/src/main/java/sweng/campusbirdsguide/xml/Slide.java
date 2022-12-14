package sweng.campusbirdsguide.xml;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import java.util.ArrayList;

import lombok.Getter;
import sweng.campusbirdsguide.presentation.elements.PresentationElement;

@Getter
public class Slide {
    private final int width, height, calculatedWidth, calculatedHeight;
    private final String title;

    private final ArrayList<PresentationElement> elements;

    public Slide(int width, int height, String title) {
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();

        this.height = height;
        this.width = width;
        this.calculatedWidth = displayMetrics.widthPixels;
        // Assume height in SP, since slides need to contain text
        this.calculatedHeight = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, height, displayMetrics));
        this.title = title;

        elements = new ArrayList<>();
    }

    public void addElement(PresentationElement presentationElement) {
        elements.add(presentationElement);
    }
}
